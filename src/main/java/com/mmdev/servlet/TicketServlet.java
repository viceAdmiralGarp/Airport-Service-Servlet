package com.mmdev.servlet;

import com.mmdev.dao.TicketDao;
import com.mmdev.serivce.JdbcTicketDao;
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
		JdbcTicketDao jdbcTicketDao = new JdbcTicketDao();
		var ticketsDao = jdbcTicketDao.findAllById();
		try (var outputStream = resp.getOutputStream()) {
			for (TicketDao ticketDao : ticketsDao) {
				if (Long.parseLong(flightId) == ticketDao.getFlightId()) {
					outputStream.print("<h1> ID ticket - " + ticketDao.getId()+" passenger no - "+ticketDao.getPassengerNo() + "</h1>");
				}
			}
		}
	}
}
