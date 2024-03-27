package com.mmdev.servlet;


import com.mmdev.serivce.FlightService;
import com.mmdev.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {

	private final static FlightService flightService = FlightService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("flights",flightService.findAll());
		req.getRequestDispatcher(JspHelper.Path("flights")).forward(req,resp);
	}
}
