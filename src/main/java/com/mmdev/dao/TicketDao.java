package com.mmdev.dao;


import com.mmdev.entity.Ticket;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

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

	private static final String UPDATE_SQL = """
							UPDATE ticket SET 
							passenger_no = ?, 
							passenger_name = ?, 
							flight_id = ?, 
							seat_no = ?, 
							cost = ?
							WHERE id = ?;
			""";

	private static final String DELETE_SQL = """
							DELETE FROM ticket 
							WHERE id = ?
							AND passenger_no = ?
							AND passenger_name = ?
							AND flight_id = ?
							AND seat_no = ?
							AND cost = ?
			""";


	private TicketDao() {
	}

	public List<Ticket> findAllTicketsById(Long id) {
		List<Ticket> tickets = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_TICKETS_BY_ID_EQUAL_FLIGHT_ID_SQL)) {
			var resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Ticket ticket = buildTicket(resultSet);//TODO: eto wache pizda drug, indexed columns in database works more faster then just iterating through all entities, add WHERE clause to your query and retrieve only one entity
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
	public Ticket create(Ticket entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(CREATE_SQL,RETURN_GENERATED_KEYS)) {
			prepareStatement.setString(1, entity.getPassengerNo());
			prepareStatement.setString(2, entity.getPassengerName());
			prepareStatement.setLong(3, entity.getFlightId());
			prepareStatement.setString(4, entity.getSeatNo());
			prepareStatement.setLong(5, entity.getCost());
			prepareStatement.setLong(6, entity.getId());
			prepareStatement.executeUpdate();
			var generatedKeys = prepareStatement.getGeneratedKeys();
			generatedKeys.next();//TODO why do you need this line?
			entity.setId(generatedKeys.getObject("id",Long.class));
			return entity;//TODO why are you returning the whole entity but service class returning only id
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket create)", e);
		}
	}

	@Override
	public void update(Ticket entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(UPDATE_SQL)) {
			prepareStatement.setString(1, entity.getPassengerNo());
			prepareStatement.setString(2, entity.getPassengerName());
			prepareStatement.setLong(3, entity.getFlightId());
			prepareStatement.setString(4, entity.getSeatNo());
			prepareStatement.setLong(5, entity.getCost());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket update)", e);
		}
	}

	@Override
	public void remove(Ticket entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(DELETE_SQL)) {
			prepareStatement.setLong(1, entity.getId());
			prepareStatement.setString(2, entity.getPassengerNo());
			prepareStatement.setString(3, entity.getPassengerName());
			prepareStatement.setLong(4, entity.getFlightId());
			prepareStatement.setString(5, entity.getSeatNo());
			prepareStatement.setLong(6, entity.getCost());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Ticket remove)", e);
		}
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
