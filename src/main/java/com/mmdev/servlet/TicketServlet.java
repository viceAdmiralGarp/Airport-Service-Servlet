package com.mmdev.servlet;

import com.mmdev.entity.Ticket;
import com.mmdev.dao.TicketDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String flightId = req.getParameter("flightId");
		TicketDao ticketDao = TicketDao.getInstance();
		var ticketsDao = ticketDao.findAll();
		try (var outputStream = resp.getOutputStream()) {
			for (Ticket ticket : ticketsDao) {
				if (Long.parseLong(flightId) == ticket.getFlightId()) {
					outputStream.print("<h1> ID ticket - " + ticket.getId() +
									   " passenger no - " + ticket.getPassengerNo() + "</h1>");
				}
			}
		}
	}
}
