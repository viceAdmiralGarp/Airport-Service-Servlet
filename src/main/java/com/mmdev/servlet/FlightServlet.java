package com.mmdev.servlet;


import com.mmdev.serivce.FlightService;
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
		FlightService flightService = FlightService.getInstance();
		try (var writer = resp.getWriter()) {
			flightService.findAll().forEach(flightDto -> writer.write("<h1><a href=\"ticket?flightId="
																	  + flightDto.getId() + "\">"
																	  + flightDto.getDescription() + "</a></h1>")
			);
		}
	}
}
