package com.mmdev.dao;

import com.mmdev.entity.Flight;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao implements Dao<Flight> {

	private static final String FIND_ALL_SQL = """
							SELECT id,
							flight_no,
							departure_date,
							departure_airport_code,
							arrival_date,
							arrival_airport_code,
							aircraft_id,
							status
							FROM flight
			""";

	@Override
	public List<Flight> findAll() {
		List<Flight> flights = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open()) {
			var preparedStatement = open.prepareStatement(FIND_ALL_SQL);
			var resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Flight flight = buildFlight(resultSet);
				flights.add(flight);
			}
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Flight)", e);
		}
		return flights;
	}

	public Flight buildFlight(ResultSet resultSet) throws SQLException {
		return new Flight(
				resultSet.getLong("id"),
				resultSet.getString("flight_no"),
				resultSet.getTimestamp("departure_date"),
				resultSet.getString("departure_airport_code"),
				resultSet.getTimestamp("arrival_date"),
				resultSet.getString("arrival_airport_code"),
				resultSet.getInt("aircraft_id"),
				resultSet.getString("status")
		);
	}
}
