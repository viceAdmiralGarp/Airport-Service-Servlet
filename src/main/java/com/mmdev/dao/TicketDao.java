package com.mmdev.dao;


import com.mmdev.entity.Ticket;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements Dao<Ticket> {

	private static final TicketDao INSTANCE = new TicketDao();
	private static final String FIND_ALL_TICKETS_BY_ID_SQL = """
				SELECT t.id AS ticket_id,
				passenger_no,
				passenger_name,
				flight_id,
				seat_no,
				cost 
				FROM flight JOIN public.ticket t on flight.id = t.flight_id
			""";

	private static final String FIND_ALL_TICKETS = """
				SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost FROM ticket
			""";

	private TicketDao() {
	}

	public List<Ticket> findAllTicketsById(Long id) {
		List<Ticket> tickets = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_TICKETS_BY_ID_SQL)) {
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
		return null;
	}

	@Override
	public void create(Ticket entity) {

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
