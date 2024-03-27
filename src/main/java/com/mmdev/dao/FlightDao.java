package com.mmdev.dao;

import com.mmdev.entity.Flight;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao implements Dao<Flight> {
	private static final FlightDao INSTANCE = new FlightDao();
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

	private static final String SAVE_SQL = """
							INSERT INTO flight (flight_no, 
							departure_date, 
							departure_airport_code, 
							arrival_date, 
							arrival_airport_code, 
							aircraft_id, 
							status) 
							VALUES (?,?,?,?,?,?,?);
			""";

	private FlightDao() {
	}

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
			throw new DaoException("Error while executing SQL query (Flight findAll)", e);
		}
		return flights;
	}

	@Override
	public Flight findById(Long id) {
		return null;
	}

	@Override
	public void create(Flight entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(SAVE_SQL)) {
			prepareStatement.setString(1,entity.getFlightNo());
			prepareStatement.setTimestamp(2,entity.getDepartureDate());
			prepareStatement.setString(3,entity.getDepartureAirportCode());
			prepareStatement.setTimestamp(4,entity.getArrivalDate());
			prepareStatement.setString(5,entity.getArrivalAirportCode());
			prepareStatement.setInt(6,entity.getAircraftId());
			prepareStatement.setString(7,entity.getStatus());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Flight create)", e);
		}
	}

	@Override
	public void update(Flight entity) {

	}

	@Override
	public void remove(Flight entity) {

	}

	private Flight buildFlight(ResultSet resultSet) throws SQLException {
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

	public static FlightDao getInstance() {
		return INSTANCE;
	}
}
