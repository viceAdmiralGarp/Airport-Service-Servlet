package com.mmdev.servlet;

import com.mmdev.serivce.TicketService;
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
		TicketService ticketService = TicketService.getInstance();
		try (var writer = resp.getWriter()) {
			ticketService.findAllFlightsById(Long.parseLong(flightId))
					.forEach(ticketDto -> writer.write("<h1>" + ticketDto.getDescription() + "</h1>"));
		}
	}
}
