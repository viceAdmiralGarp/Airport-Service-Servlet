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

	private final TicketService ticketService = TicketService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String flightIdS = req.getParameter("flightId");
		Long flightId = Long.valueOf(flightIdS);
		req.setAttribute("tickets", ticketService.findAllFlightsById(flightId));
		req.getRequestDispatcher("/tickets.jsp").forward(req,resp);
	}
}
