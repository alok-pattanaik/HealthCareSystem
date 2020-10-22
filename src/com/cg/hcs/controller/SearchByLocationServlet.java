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

import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;

/**********************************
 * @Description: SearchByLocation servlet Implementation
 * @author : Uradi Bhavani
 * @Date : 20/10/2020
 *
 **********************************/
@WebServlet("/SearchByLocation")
public class SearchByLocationServlet extends HttpServlet {
	
	static final Logger LOGGER = Logger.getLogger(SearchByLocationServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		HttpSession session = request.getSession();
		if( session.getAttribute("loggedInStatus")!="user" ) 
		{
			LOGGER.warn("Redirecting to Error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try{
			LOGGER.info("Inside Search By Location servlet.");
			String location = request.getParameter("location");
			List<DiagnosticCenter> centersList = diagnosticCenterService.getDiagnosticCentersListByLocation(location);
			if(centersList != null){
				LOGGER.info("Redirecting to Show centers page after successfull search.");
				
				request.setAttribute("centersList", centersList);
				request.getRequestDispatcher("showCenters.jsp").forward(request, response);
			}
		}catch(Exception e){
			LOGGER.warn("Error in search by location servlet.");
			System.out.println("In SearchByLocation servlet : "+e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}
