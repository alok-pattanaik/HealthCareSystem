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

import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;
import com.cg.hcs.service.UserServiceImpl;


/**********************************
 * @Description: Edit profile Servlet Implementation
 * @author : Reshma
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	
	static final Logger LOGGER = Logger.getLogger(EditProfileServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = new UserServiceImpl();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInStatus")!="user" ) {
			LOGGER.warn("Redirecting to roor page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try{
			LOGGER.info("Inside Edit profile servlet.");
			
			
			
			String userId = (String) session.getAttribute("userId");
			String username = request.getParameter("username");
			Long contactNo = Long.parseLong(request.getParameter("contactNo"));
			String email = request.getParameter("email");
			
			Users user = new Users(userId, username, contactNo, email);
			
			boolean res = userService.editProfile(user);
			
			if(res){
				LOGGER.info("Redirecting to user home page after editing the profile.");
				dispatcher = request.getRequestDispatcher("userHomePage.jsp");
				dispatcher.forward(request,response);
			}else{
				LOGGER.info("Could not update profile");
				dispatcher = request.getRequestDispatcher("editProfile.jsp");
				dispatcher.forward(request,response);
			}
		}catch(Exception e){
			LOGGER.warn("Eoor in Edit profile servlet.");
			System.out.println("In EditProfileServlet : "+e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}
