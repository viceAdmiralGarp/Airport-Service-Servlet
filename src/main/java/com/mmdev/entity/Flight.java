package com.mmdev.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Flight {
	private Long id;
	private String flightNo;
	private Timestamp departureDate;
	private String departureAirportCode;
	private Timestamp arrivalDate;
	private String arrivalAirportCode;
	private Integer aircraftId;
	private String Status;

	public Flight(Long id,
				  String flightNo,
				  Timestamp departureDate,
				  String departureAirportCode,
				  Timestamp arrivalDate,
				  String arrivalAirportCode,
				  Integer aircraftId,
				  String status) {
		this.id = id;
		this.flightNo = flightNo;
		this.departureDate = departureDate;
		this.departureAirportCode = departureAirportCode;
		this.arrivalDate = arrivalDate;
		this.arrivalAirportCode = arrivalAirportCode;
		this.aircraftId = aircraftId;
		Status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Timestamp getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Timestamp departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return Objects.equals(id, flight.id)
			   && Objects.equals(flightNo, flight.flightNo)
			   && Objects.equals(departureDate, flight.departureDate)
			   && Objects.equals(departureAirportCode, flight.departureAirportCode)
			   && Objects.equals(arrivalDate, flight.arrivalDate)
			   && Objects.equals(arrivalAirportCode, flight.arrivalAirportCode)
			   && Objects.equals(aircraftId, flight.aircraftId)
			   && Objects.equals(Status, flight.Status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,
				flightNo,
				departureDate,
				departureAirportCode,
				arrivalDate,
				arrivalAirportCode,
				aircraftId,
				Status);
	}

	@Override
	public String toString() {
		return "FlightDao{" +
			   "id=" + id +
			   ", flightNo='" + flightNo + '\'' +
			   ", departureDate=" + departureDate +
			   ", departureAirportCode='" + departureAirportCode + '\'' +
			   ", arrivalDate=" + arrivalDate +
			   ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
			   ", aircraftId=" + aircraftId +
			   ", Status='" + Status + '\'' +
			   '}';
	}
}
