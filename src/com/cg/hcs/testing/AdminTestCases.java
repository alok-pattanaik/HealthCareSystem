package com.cg.hcs.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.Test;
import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.service.AppointmentServiceImpl;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IAppointmentService;
import com.cg.hcs.service.IDiagnosticCenterService;
import com.cg.hcs.service.ITestService;
import com.cg.hcs.service.TestServiceImpl;

/*************
 * 
 * Description : HealthCareSystem Testing
 * @Date : 12/10/2020
 * 
 *************/
public class AdminTestCases 
{
	
	/*************
	 * 
	 * @Description : Testing method to add DiagnosticCenter
	 * No arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testAddCenter()
	{
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		DiagnosticCenter center = new DiagnosticCenter("Test Diagnostic Center","Test Address",98543210l);;
		String centerId = diagnosticCenterService.addCenter(center);
		assertNotNull(centerId);
	}
	
	/*************
	 * 
	 * @Description : Testing method to check Deletion of DiagnosticCenter is successful
	 * No Arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testDeleteCenter()
	{
		String centerId = "";//incorrect
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		assertTrue(diagnosticCenterService.deleteCenter(centerId));
	}
	
	@Test
	public void testDeleteCenter2()
	{
		String centerId = "";//incorrect
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		assertFalse(diagnosticCenterService.deleteCenter(centerId));
	}
	
	
	/*************
	 * 
	 * @Description : Testing method to view test
	 * No Arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testViewTests()
	{
		ITestService testService = new TestServiceImpl();
		assertNotNull(testService.viewAllTest("C00015"));
	}
	
	
	/*************
	 * 
	 * @Description : Testing method to add test under a particular DiagnosticCenter
	 * No Arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	  @Test 
	  public void testAddTest() 
	  {
		  DiagnosticCenter center = new DiagnosticCenter("C_00015", "Add Test"); 
		  com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("Covid 190", center);
		  ITestService testService = new TestServiceImpl();
		  assertNotNull(testService.addTest(test));
	  }
	 
	  /*************
		 * 
		 * @Description : Testing method for viewing appointment
		 * No Arguments
		 * 
		 * @returns: void
		 * 
		 *************/
	@Test
	public void testViewAppointment()
	{
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		List<Appointment> appointmentList = appointmentService.viewAllAppointmentsByCenter("C00222");
		assertNotNull(appointmentList);
	}
	
	@Test
	public void testViewAppointment2()
	{
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		List<Appointment> appointmentList = appointmentService.viewAllAppointmentsByCenter("C00015");
		assertNull(appointmentList);
	}
	
}