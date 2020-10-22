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

import com.cg.hcs.entity.Appointment;

import com.cg.hcs.entity.Test;
import com.cg.hcs.entity.Users;
import com.cg.hcs.service.AppointmentServiceImpl;
import com.cg.hcs.service.IAppointmentService;


/**********************************
 * @Description: MakeAppointment Servlet Implementation
 * @author : Bhavani
 * @Date : 12/10/2020
 *
 **********************************/
@WebServlet("/MakeAppointmentServlet")
public class MakeAppointmentServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(MakeAppointmentServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		if ( session.getAttribute("loggedInStatus") != "user") 
		{
			LOGGER.warn("Redirecting to error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try {
			LOGGER.info("Inside Make appointment servlet.");
			
			
			String userId = (String) session.getAttribute("userId");
			Users user = new Users(userId);
			Test test = new Test(request.getParameter("testObj"));
			String appDateTime = request.getParameter("date") + " " + request.getParameter("time");


			Appointment appointment = new Appointment(appDateTime, 'P', test, user);
			
			int makeAppointment = appointmentService.makeAppointment(appointment);
			
			if (makeAppointment != 0) {
				LOGGER.info("Redirecting to UserHomePage after booking appointment successfully.");
				
				request.setAttribute("appointmentBooked", "Appointment booked successfully!!");
				
			}
			else
			{
				request.setAttribute("appointmentBooked", "Slot already occupied or you have a appointment for other test at same time");
			}
			request.getRequestDispatcher("userHomePage.jsp").forward(request, response);

		} catch (Exception e) {
			LOGGER.warn("Error in Make Appointment servlet.");
			System.out.println("In MakeAppointmentServlet : "+e.getMessage());

		}

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

}
