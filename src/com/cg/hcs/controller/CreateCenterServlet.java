package com.cg.hcs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterService;

/**********************************
 * @Description: CreateCenter Servlet Implementation
 * @author : Alok Pattanaik
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/CreateCenterServlet")
public class CreateCenterServlet extends HttpServlet
{
	static final Logger LOGGER = Logger.getLogger(CreateCenterServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		if(session.getAttribute("loggedInStatus")!="admin" ) {
			LOGGER.warn("Redirecting to error page");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		try {
			LOGGER.info("Inside Create center servlet.");
			
			String centerName = request.getParameter("centerName");
			String centerAddress = request.getParameter("centerAddress");
			long contactNumber = Long.parseLong(request.getParameter("contactNumber"));
			
			DiagnosticCenter center = new DiagnosticCenter(centerName, centerAddress, contactNumber);
			
			
			String centerId = diagnosticCenterService.addCenter(center);
			if(centerId != null)
			{
				LOGGER.info("Created center Successfully.");
				request.setAttribute("centerId","Center created with "+centerId);
				request.getRequestDispatcher("adminHomePage.jsp").include(request, response);
			}
			else
			{
				LOGGER.info("Center creation failed.");
				request.setAttribute("centerId","Center could not get created. Please Try Again.");
				request.getRequestDispatcher("adminHomePage.jsp").include(request, response);
			}
		} catch (NumberFormatException e) {
			LOGGER.warn("Error in Create center servlet.");
			System.out.println("In Create center servlet : "+e.getMessage());
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}
