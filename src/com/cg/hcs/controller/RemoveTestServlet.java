package com.cg.hcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cg.hcs.entity.Test;
import com.cg.hcs.service.ITestService;
import com.cg.hcs.service.TestServiceImpl;

/**********************************
 * @Description: RemoveTest servlet Implementation
 * @author : Pratik Prakash
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/RemoveTestServlet")
public class RemoveTestServlet extends HttpServlet
{
	static final Logger LOGGER = Logger.getLogger(RemoveTestServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		ITestService testService = new TestServiceImpl();
		
		if( session.getAttribute("loggedInStatus")!="admin" ) 
		{
			LOGGER.warn("Redirecting to Error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try {
			LOGGER.info("Inside Remove test servlet.");
			
			
			String testId = request.getParameter("testId");
			
			if(testService.removeTest(testId))
			{
				LOGGER.info("Redirecting to Admin Home page after removing test successfully.");
				request.setAttribute("testDeleted", "Test with test ID : "+testId+" deleted");
			}
			else
			{
				LOGGER.warn("Could not remove test.");
				request.setAttribute("testDeleted", "Test with test ID : "+testId+" could not be deleted");
			}
			request.getRequestDispatcher("removeTest.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.warn("Error in remove test servlet.");
			System.out.println("In Remove Test servlet : "+e.getMessage());
		}
	}
}
