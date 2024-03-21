package com.mmdev.dao;

import java.util.Objects;

public class TicketDao {
	private Long id;
	private String passengerNo;
	private String passengerName;
	private Long flightId;
	private String seatNo;
	private Long Cost;

	public TicketDao(Long id, String passengerNo, String passengerName, Long flightId, String seatNo, Long cost) {
		this.id = id;
		this.passengerNo = passengerNo;
		this.passengerName = passengerName;
		this.flightId = flightId;
		this.seatNo = seatNo;
		Cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerNo() {
		return passengerNo;
	}

	public void setPassengerNo(String passengerNo) {
		this.passengerNo = passengerNo;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public Long getCost() {
		return Cost;
	}

	public void setCost(Long cost) {
		Cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketDao ticketDao = (TicketDao) o;
		return Objects.equals(id, ticketDao.id)
			   && Objects.equals(passengerNo, ticketDao.passengerNo)
			   && Objects.equals(passengerName, ticketDao.passengerName)
			   && Objects.equals(flightId, ticketDao.flightId)
			   && Objects.equals(seatNo, ticketDao.seatNo)
			   && Objects.equals(Cost, ticketDao.Cost);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, passengerNo, passengerName, flightId, seatNo, Cost);
	}

	@Override
	public String toString() {
		return "TicketDao{" +
			   "id=" + id +
			   ", passengerNo='" + passengerNo + '\'' +
			   ", passengerName='" + passengerName + '\'' +
			   ", flightId=" + flightId +
			   ", seatNo='" + seatNo + '\'' +
			   ", Cost=" + Cost +
			   '}';
	}
}
