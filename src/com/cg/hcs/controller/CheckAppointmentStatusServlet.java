package com.cg.hcs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.service.AppointmentServiceImpl;
import com.cg.hcs.service.IAppointmentService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;

/**********************************
 * @Description: CheckAppointmentStatus Servlet Implementation
 * @author : Reshma
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/CheckAppointmentStatusServlet")
public class CheckAppointmentStatusServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(CheckAppointmentStatusServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		IUserService userService = new UserServiceImpl();
		HttpSession session = request.getSession();
		if( session.getAttribute("loggedInStatus")!="user" ) {
			LOGGER.warn("Redirecting to error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		}
		
		
		try {
			LOGGER.info("Inside Check appointment status servlet.");

			
			String userId = (String) session.getAttribute("userId");
			Users user = userService.getUser(userId);
			
			List<Appointment> appointmentList = appointmentService.getAppointmentStatus(user);

			if(appointmentList != null){
				LOGGER.info("Redirecting to Check Appointment Status page.");
				request.setAttribute("appointmentList", appointmentList);
				request.getRequestDispatcher("checkAppointmentStatus.jsp").include(request, response);
			}
		} catch (Exception e) {
			LOGGER.info("Error while displaying the status of appointment.");
			e.printStackTrace();
		}

	}

}