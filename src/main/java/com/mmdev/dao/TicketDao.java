package com.mmdev.dao;


import com.mmdev.entity.Ticket;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
	private static final String FIND_ALL_TICKETS_SQL = """
   			SELECT t.id AS ticket_id,
			passenger_no,
			passenger_name,
			flight_id,
			seat_no,
			cost 
			FROM flight JOIN public.ticket t on flight.id = t.flight_id
			""";


	public List<Ticket> findAllById() {
		List<Ticket> tickets = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_TICKETS_SQL)) {
//			prepareStatement.setLong(1, id);
			var resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Ticket ticket = buildTicket(resultSet);
				tickets.add(ticket);
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
}
