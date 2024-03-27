package com.mmdev.servlet;

import com.mmdev.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("roles", List.of("USER","ADMIN"));
		req.setAttribute("genders", List.of("Male","Female"));

		req.getRequestDispatcher(JspHelper.Path("registration")).forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var name = req.getParameter("name");
	}
}
