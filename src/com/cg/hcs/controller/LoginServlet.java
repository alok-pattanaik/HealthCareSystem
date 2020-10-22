package com.cg.hcs.controller;

import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;
import com.cg.hcs.service.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.log4j.Logger;

/**********************************
 * @Description: Login Servlet Implementation
 * @author : Yashaswini
 * @Date : 12/10/2020
 *
 **********************************/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		IUserService userService = new UserServiceImpl();
		
		try {
			LOGGER.info("Inside Login servlet.");
			
			String userId = request.getParameter("userid");
			if(userId == null)
			{
				response.sendRedirect("login.jsp");
				return;
			}
			String password = request.getParameter("password");
			
			boolean isValid = userService.validateUser(userId, password);
			String username = userService.getUsername(userId);
			
			if (isValid) {
				String userRole = userService.getRoleCode(userId);
				System.out.println(userRole);
				out.println("<html>");
				if (userRole.equals("adm")) {
					LOGGER.info("Redirecting to Admin Home Page after successfull login.");
					out.println("<body> Admin loggedin Successfully...!</body>");
					session.setAttribute("userId", userId);
					session.setAttribute("role", "adm");
					session.setAttribute("name", username);
					session.setAttribute("loggedInStatus", "admin");
					dispatcher = request.getRequestDispatcher("adminHomePage.jsp");
					dispatcher.forward(request, response);
				} else {
					LOGGER.info("Redirecting to User Home Page after successfull login.");
					out.println("<body> User loggedin Successfully...!</body>");
					session.setAttribute("userId", userId);
					session.setAttribute("role", "usr");
					session.setAttribute("name", username);
					session.setAttribute("loggedInStatus", "user");
					dispatcher = request.getRequestDispatcher("userHomePage.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				LOGGER.warn("Login failed -- Redirecting to login page.");
				request.setAttribute("loginStatusFailed", "Invalid credentials...!");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			out.println("</html>");
		} catch (Exception e) {
			LOGGER.warn("Error in loing servlet.");
			System.out.println("In LoginServlet catch block : "+e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}


}
