package com.mmdev.servlet;

import com.mmdev.dto.UserDto;
import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;
import com.mmdev.exception.ValidationException;
import com.mmdev.serivce.UserService;
import com.mmdev.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private final UserService userService = UserService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("roles", List.of(Role.USER, Role.ADMIN));
		req.setAttribute("genders", List.of(Gender.MALE, Gender.FEMALE));

		req.getRequestDispatcher(JspHelper.Path("registration")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDto userDto = UserDto.builder()
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.password(req.getParameter("password"))
				.birthday(req.getParameter("birthday"))
				.role(req.getParameter("role"))
				.gender(req.getParameter("gender"))
				.build();
		try {
			userService.create(userDto);
			resp.sendRedirect("/login");
		}catch (ValidationException e){
		req.setAttribute("errors",e.getErrors());
		doGet(req,resp);
		}
	}
}
