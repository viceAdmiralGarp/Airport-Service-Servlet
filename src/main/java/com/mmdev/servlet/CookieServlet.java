package com.mmdev.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

	private static final String UNIQUE_ID = "userId";
	private final AtomicInteger siteVisit = new AtomicInteger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//TODO ServletException is not needed
		var cookies = req.getCookies();
		if (cookies == null || Arrays.stream(cookies)
				.filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
				.findFirst()
				.isEmpty()) {//TODO Arrays.stream(cookies).noneMatch(cookie -> UNIQUE_ID.equals(cookie.getName())) is more readable and extract that to a separate method
			var cookie = new Cookie(UNIQUE_ID, "1");
			resp.addCookie(cookie);
			siteVisit.incrementAndGet();
		}
		resp.setContentType("text/html");
		var writer = resp.getWriter();
		writer.write(siteVisit.get());
	}
}
