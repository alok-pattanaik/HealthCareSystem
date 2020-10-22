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
import com.cg.hcs.entity.Test;
import com.cg.hcs.service.ITestService;
import com.cg.hcs.service.TestServiceImpl;

/************
 * @Description: AddTest Servlet Implementation
 * @author : Pratik Prakash
 * @Date : 20/10/2020
 *
 ************/

@WebServlet("/AddTestServlet")
public class AddTestServlet extends HttpServlet 
{

	static final Logger LOGGER = Logger.getLogger(AddTestServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		ITestService testService = new TestServiceImpl();
		
		if( session.getAttribute("loggedInStatus")!="admin" ) {
			LOGGER.warn("Redirecting to Error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		try {
			LOGGER.info("Inside Add test servlet.");
			
			String testName = "";
			
			String centerId = request.getParameter("centerId");
			 if(request.getParameter("testName")!=null)
			 {
				 testName = request.getParameter("testName");
			 }
			 else
			 {
				 String[] selectedTests = request.getParameterValues("testNames");
				 int length = selectedTests.length;
				 for(int i=0;i<length;i++)
				 {
					 if(i==length-1)
					 {
						 testName = testName + selectedTests[i];
					 }
					 else
					 {
						 
						 testName = testName + selectedTests[i] + " ";
					 }
					 if(testName == null)
					 {
						 response.sendRedirect("addTest.jsp");
						 return;
					 }
				 }
			 }
			
			DiagnosticCenter center = new DiagnosticCenter(centerId, "Add Test");
			Test test = new Test(testName, center);
			
			
			String testId = testService.addTest(test);
			if(testId!=null){
				LOGGER.info("Redirecting to Add test page.");
				request.setAttribute("testId", testId);
				request.getRequestDispatcher("addTest.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("testNotAdded","Please enter different test");
				request.getRequestDispatcher("addTest.jsp").forward(request, response);
			}
		} catch (Exception e) {
			LOGGER.warn("Error In Add Test Servlet ");
			
			System.out.print("In AddTestServlet : "+e.getMessage());
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
}