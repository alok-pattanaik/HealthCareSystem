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
import com.cg.hcs.entity.Users;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.IValidation;
import com.cg.hcs.service.UserServiceImpl;
import com.cg.hcs.service.UserServiceImpl;
import com.cg.hcs.service.Validation;

/**********************************
 * @Description: Register servlet Implementation
 * @author : Reshma
 * @Date : 20/10/2020
 *
 **********************************/
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		IUserService profileService = new UserServiceImpl();
		RequestDispatcher dispatcher = null;
		Users user = new Users();

		try {
			LOGGER.info("Inside Register servlet.");
			
			String uname = request.getParameter("username");
			if(uname == null)
			{
				response.sendRedirect("registration.jsp");
				return;
			}
			IValidation validate = new Validation();
			if (validate.isValidUsername(uname)) {
				user.setUserName(uname);
			}
			else {
				LOGGER.warn("Username is not validate redirect to registration page.");
				request.setAttribute("userNameValidate","failed");
				request.setAttribute("userRegistered", "User registered Unsuccessfull!!! ");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			}
			user.setUserPassword(request.getParameter("password"));
			user.setUserRole("usr");
			user.setEmail(request.getParameter("email"));
			user.setContactNo(Long.parseLong(request.getParameter("contactno")));
			
			String userId = profileService.register(user);
			System.out.println(userId);
			if (userId != null) {
				LOGGER.info("Displaying that user has registered successfull.");
				
				session.setAttribute("userId", userId);
				request.setAttribute("userRegistered", "User registered successfully with id : "+userId);
				
			}
			else {
				request.setAttribute("userRegistered", "User already exist! ");
			}
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			LOGGER.warn("Error in Register servlet.");
			System.out.println("In registerServlet."+e.getMessage());

		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}
