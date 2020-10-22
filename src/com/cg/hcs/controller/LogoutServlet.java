package com.cg.hcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**********************************
 * @Description: Logout Servlet Implementation
 * @author : Alok Pattanaik
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Inside Logout servlet.");
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
}
