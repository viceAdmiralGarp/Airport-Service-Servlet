package com.mmdev.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	private static final String USER = "user";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		var session = req.getSession();//TODO commented code should be removed
//		var user = (UserDto) session.getAttribute(USER);
//		if (user == null) {
//			user = UserDto.builder()
//					.id(25L)
//					.mail("test@gmail.com")
//					.build();
//			session.setAttribute(USER, user);
//		}
	}
}
