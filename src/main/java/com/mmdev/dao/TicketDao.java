package com.mmdev.dao;


import com.mmdev.entity.Ticket;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

	public static final TicketDao INSTANCE = new TicketDao();
	private static final String FIND_ALL_TICKETS_BY_ID_SQL = """
						SELECT t.id AS ticket_id,
			passenger_no,
			passenger_name,
			flight_id,
			seat_no,
			cost 
			FROM flight JOIN public.ticket t on flight.id = t.flight_id
			""";

	private TicketDao() {
	}

	public List<Ticket> findAllFlightsById(Long id) {
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
			throw new DaoException("Error while executing SQL query (Ticket)", e);
		}
		return tickets;
	}

	private Ticket buildTicket(ResultSet resultSet) throws SQLException {
		return new Ticket(
				resultSet.getLong("ticket_id"),
				resultSet.getString("passenger_no"),
				resultSet.getString("passenger_name"),
				resultSet.getLong("flight_id"),
				resultSet.getString("seat_no"),
				resultSet.getLong("cost")
		);
	}

	public static TicketDao getInstance() {
		return INSTANCE;
	}
}
