  
package com.cg.hcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterService;

/**********************************
 * @Description: DeleteCenter Servlet Implementation
 * @author : Alok Pattanaik, Pratik Prakash
 * @Date : 20/10/2020
 *
 **********************************/

@WebServlet("/DeleteCenterServlet")
public class DeleteCenterServlet extends HttpServlet 
{
	static final Logger LOGGER = Logger.getLogger(DeleteCenterServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("loggedInStatus")!="admin" ) 
		{
			LOGGER.warn("Redirecting to error page.");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		}
		
		
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		
		try {
			
			LOGGER.info("Inside Delete Center servlet.");
			
			
			String centerId = request.getParameter("centerId");
			
			boolean result= diagnosticCenterService.deleteCenter(centerId);
			
			if(result)
			{
				LOGGER.info("Redirecting to View all centers page after deleting the center.");
				request.setAttribute("centerDeleted", "Center with center id : "+centerId+" deleted");
				request.getRequestDispatcher("viewAllCenters.jsp").forward(request, response);
			}
			else
			{
				LOGGER.info("Could not delete center -- redirecting to View all centers page.");
				request.setAttribute("centerDeleted", "Selected Center can't be deleted");
				request.getRequestDispatcher("viewAllCenters.jsp").forward(request, response);
			}
		} catch (Exception e) {
			LOGGER.warn("Error in delete center servlet.");
			System.out.println("Inside delet center servlet : "+e.getMessage());
		}
	}
}