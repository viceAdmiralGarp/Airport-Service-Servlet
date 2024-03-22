package com.mmdev.serivce;

import com.mmdev.dao.Dao;
import com.mmdev.dao.FlightDao;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightDao implements Dao<FlightDao> {

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
	public List<FlightDao> findAll() {
		List<FlightDao> flights = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open()) {
			var preparedStatement = open.prepareStatement(FIND_ALL_SQL);
			var resultSet = preparedStatement.executeQuery();
			FlightDao flightDao;
			while (resultSet.next()) {
				flightDao = buildFlight(resultSet);
				flights.add(flightDao);
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return flights;
	}

	public FlightDao buildFlight(ResultSet resultSet) throws SQLException {
		return new FlightDao(
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
