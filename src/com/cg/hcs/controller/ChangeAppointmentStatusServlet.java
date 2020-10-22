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
import com.cg.hcs.service.AppointmentServiceImpl;
import com.cg.hcs.service.IAppointmentService;

/**********************************
 * @Description: ChangeAppointmentStatus Servlet Implementation
 * @author : Yashaswini
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/ChangeAppointmentStatusServlet")
public class ChangeAppointmentStatusServlet extends HttpServlet 
{
	static final Logger LOGGER = Logger.getLogger(ChangeAppointmentStatusServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		if(session.getAttribute("loggedInStatus")!="admin" ) {
			LOGGER.warn("Redirecting to Error page.");
			
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = null;
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		
		try {
			
			LOGGER.info("Inside Change Appointment Status Servlet.");
			
			
			int appId = Integer.parseInt(request.getParameter("appId"));
			char appStatus = request.getParameter("appStatus").charAt(0);
			
			boolean result = appointmentService.approveRejectAppointment(appId, appStatus);
			if(result==true) {
				LOGGER.info("Redirecting to View All Centers page.");
				
				request.setAttribute("approveAppointment", "Appointment status changed!!");
				dispatcher = request.getRequestDispatcher("approveRejectAppointment.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			LOGGER.warn("Error inside Change Appointment Status Servlet.");
			System.out.println("Inside ChangeAppointmentStatusServlet"+ e.getMessage());
		}
	}
}
