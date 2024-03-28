package com.mmdev.dao;

import com.mmdev.entity.Flight;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;


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

	private static final String FIND_BY_ID_SQL = """
							SELECT id,
							flight_no, 
							departure_date, 
							departure_airport_code, 
							arrival_date, 
							arrival_airport_code, 
							aircraft_id, 
							status FROM flight
							WHERE id = ?
			""";

	private FlightDao() {
	}

	@Override
	public List<Flight> findAll() {
		List<Flight> flights = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open()) {
			var preparedStatement = open.prepareStatement(FIND_ALL_SQL);
			var resultSet = preparedStatement.executeQuery();
			Flight flight;
			while (resultSet.next()) {
				flight = buildFlight(resultSet);
				flights.add(flight);
			}
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Flight findAll)", e);
		}
		return flights;
	}

	@Override
	public Flight findById(Long id) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_BY_ID_SQL)) {
			prepareStatement.setLong(1, id);
			var resultSet = prepareStatement.executeQuery();
			Flight flight = null;
			while (resultSet.next()) {
				flight = buildFlight(resultSet);
			}
			return flight;
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (Flight findById)", e);
		}
	}

	@Override
	public void create(Flight entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(SAVE_SQL)) {
			prepareStatement.setString(1, entity.getFlightNo());
			prepareStatement.setTimestamp(2, entity.getDepartureDate());
			prepareStatement.setString(3, entity.getDepartureAirportCode());
			prepareStatement.setTimestamp(4, entity.getArrivalDate());
			prepareStatement.setString(5, entity.getArrivalAirportCode());
			prepareStatement.setInt(6, entity.getAircraftId());
			prepareStatement.setString(7, entity.getStatus());
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
		return Flight.builder()
				.id(resultSet.getLong("id"))
				.flightNo(resultSet.getString("flight_no"))
				.departureDate(resultSet.getTimestamp("departure_date"))
				.departureAirportCode(resultSet.getString("departure_airport_code"))
				.arrivalAirportCode(resultSet.getString("arrival_airport_code"))
				.aircraftId(resultSet.getInt("aircraft_id"))
				.status(resultSet.getString("status"))
				.build();
	}

	public static FlightDao getInstance() {
		return INSTANCE;
	}
}
