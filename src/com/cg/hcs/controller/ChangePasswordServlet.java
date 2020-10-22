package com.cg.hcs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.IUserDAO;
import com.cg.hcs.dao.UserDAOImpl;
import com.cg.hcs.dao.UserDAOImpl;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;

/**********************************
 * @Description: ChangePassword Servlet Implementation
 * @author : Reshma
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	
	static final Logger LOGGER = Logger.getLogger(ChangePasswordServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IUserService userService = new UserServiceImpl();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInStatus")!="user" ) {
			LOGGER.warn("Redirecting to error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		LOGGER.info("Inside Change Password Servlet.");
		
		
		try {
			
			
			
			String userId = (String) session.getAttribute("userId");
			String password = request.getParameter("confirmPassword");
			
			boolean isPasswordChanged = userService.changePassword(userId, password);
			
			if (isPasswordChanged) {
				LOGGER.info("Redirecting to User home page.");
				
				dispatcher = request.getRequestDispatcher("userHomePage.jsp");
				dispatcher.forward(request, response);
			} else {
				
				LOGGER.warn("Could not change password.");
				dispatcher = request.getRequestDispatcher("changePassword.jsp");
				dispatcher.forward(request, response);
			} 
		} catch (Exception e) {
			LOGGER.warn("Error inside Change Password Servlet.");
			System.out.println("In ChangePasswordServlet : "+e.getMessage());
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}
