package com.mmdev.servlet;

import com.mmdev.dao.FlightDao;
import com.mmdev.serivce.JdbcFlightDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		JdbcFlightDao jdbcFlightsDao = new JdbcFlightDao();
		try (var outputStream = resp.getOutputStream()) {
			for (FlightDao flightDao : jdbcFlightsDao.findAll()) {
				outputStream.print("<h1><a href=\"ticket?flightId=" + flightDao.getId() + "\">" +
								   flightDao.getFlightNo() + " || " + flightDao.getId() + "</a></h1>");
			}
		}
	}
}
