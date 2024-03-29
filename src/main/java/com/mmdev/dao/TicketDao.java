package com.mmdev.dao;


import com.mmdev.entity.Ticket;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements Dao<Ticket> {

	private static final TicketDao INSTANCE = new TicketDao();
	private static final String FIND_ALL_TICKETS_BY_ID_EQUAL_FLIGHT_ID_SQL = """
				SELECT t.id AS ticket_id,
				passenger_no,
				passenger_name,
				flight_id,
				seat_no,
				cost 
				FROM flight JOIN public.ticket t on flight.id = t.flight_id;
			""";

	private static final String FIND_ALL_TICKETS = """
				SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket;
			""";

	private static final String FIND_BY_ID = """
				SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket WHERE id =?;
			""";

	private static final String CREATE_SQL = """
   				INSERT INTO ticket(passenger_no, passenger_name, flight_id, seat_no, cost) 
   				VALUES (?,?,?,?,?);
   				
			""";

	private TicketDao() {
	}

	public List<Ticket> findAllTicketsById(Long id) {
		List<Ticket> tickets = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_TICKETS_BY_ID_EQUAL_FLIGHT_ID_SQL)) {
			var resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Ticket ticket = buildTicket(resultSet);
				if (id.equals(ticket.getFlightId())) {
					tickets.add(ticket);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket findAllTicketsById)", e);
		}
		return tickets;
	}

	@Override
	public List<Ticket> findAll() {
		List<Ticket> tickets = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_TICKETS)) {
			var resultSet = prepareStatement.executeQuery();
			Ticket ticket;
			while (resultSet.next()) {
				ticket = buildTicket(resultSet);
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket findAll)", e);
		}
		return tickets;
	}

	@Override
	public Ticket findById(Long id) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_BY_ID)) {
			prepareStatement.setLong(1, id);
			var resultSet = prepareStatement.executeQuery();
			Ticket ticket = null;
			while (resultSet.next()) {
				ticket = buildTicket(resultSet);
			}
			return ticket;
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket findById)", e);
		}
	}

	@Override
	public void create(Ticket entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(CREATE_SQL)) {
			prepareStatement.setString(1,entity.getPassengerNo());
			prepareStatement.setString(2,entity.getPassengerName());
			prepareStatement.setLong(3,entity.getFlightId());
			prepareStatement.setString(4,entity.getSeatNo());
			prepareStatement.setLong(5,entity.getCost());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket create)", e);
		}
	}

	@Override
	public void update(Ticket entity) {

	}

	@Override
	public void remove(Ticket entity) {

	}

	private Ticket buildTicket(ResultSet resultSet) throws SQLException {
		return Ticket.builder()
				.id(resultSet.getLong("id"))
				.passengerNo(resultSet.getString("passenger_no"))
				.passengerName(resultSet.getString("passenger_name"))
				.flightId(resultSet.getLong("flight_id"))
				.seatNo(resultSet.getString("seat_no"))
				.cost(resultSet.getLong("cost"))
				.build();
	}

	public static TicketDao getInstance() {
		return INSTANCE;
	}
}
