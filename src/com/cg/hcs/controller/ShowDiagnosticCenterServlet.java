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

import com.cg.hcs.dao.IUserDAO;
import com.cg.hcs.dao.UserDAOImpl;
import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;

/**********************************
 * @Description: ShowDiagnosticCenter servlet Implementation
 * @author : Bhavani
 * @Date : 12/10/2020
 *
 **********************************/
@WebServlet("/ShowDiagnosticCenterServlet")
public class ShowDiagnosticCenterServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(ShowDiagnosticCenterServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		
		if (session.getAttribute("loggedInStatus") !="user") {
			LOGGER.warn("Redirecting ro Error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try {
			LOGGER.info("Inside Show Diagnostic Center Servlet.");
			
			
			
			List<DiagnosticCenter> centersList = diagnosticCenterService.viewAllCenters();
			if(centersList != null){
				LOGGER.info("Redirecting to show centers page.");
				request.setAttribute("centersList", centersList);
				request.getRequestDispatcher("showCenters.jsp").forward(request, response);
			}
		} catch (Exception e) {
			LOGGER.warn("Error in show diagnostic center servlet.");
			System.out.println("In ShowDiagnosticCenter" + e.getMessage());

		}

	}

}
