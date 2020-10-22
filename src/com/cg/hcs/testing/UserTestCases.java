package com.cg.hcs.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.service.AppointmentServiceImpl;
import com.cg.hcs.service.DiagnosticCenterServiceImpl;
import com.cg.hcs.service.IAppointmentService;
import com.cg.hcs.service.IDiagnosticCenterService;
import com.cg.hcs.service.ITestService;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.TestServiceImpl;
import com.cg.hcs.service.UserServiceImpl;


/*************
 * 
 * Description : HealthCareSystem Service Implementation Testing
 * @Date : 12/10/2020
 * 
 *************/
public class UserTestCases {

	
	
	/*************
	 * 
	 * @Description : Testing method to get DiagnosticCenter list
	 *
	 *	No arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testGetDiagnosticCenterList() 
	{
		IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
		
		assertNotNull(diagnosticCenterService.viewAllCenters());
	}
	
	/*************
	 * 
	 * @Description : Testing method to test TestList is not empty
	 * No Arguments
	 * 
	 * @returns: void
	 *************/
	
	@Test
	public void testGetTestLists() 
	{
		ITestService testService = new TestServiceImpl();
		assertNotNull(testService.viewAllTest("C00222"));//correct centerid
	}
	
	@Test
	public void testGetTestLists2() 
	{
		ITestService testService = new TestServiceImpl();
		assertNull(testService.viewAllTest("C00015"));//incorrect centerid
	}
	
	
	
	/*************
	 * 
	 * @Description : Testing method to test appointment successful
	 * No Arguments
	 * 
	 * @returns: void
	 *************/
	@Test
	public void testMakeAppointment()//All correct values 
	{
		Users user = new Users("U00273");
		com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("T00000280");
		Appointment appointment = new Appointment("28/12/2020 18:35", 'P', test, user);
		
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		assertNotSame(0, appointmentService.makeAppointment(appointment));
		
	}
	
	@Test
	public void testMakeAppointment2() //incorrect values
	{
		Users user = new Users("U00032");
		com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("T00000028");
		Appointment appointment = new Appointment("12/10/2020 18:35", 'P', test, user);
		
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		assertSame(0, appointmentService.makeAppointment(appointment));
		
	}
	
	@Test
	public void testMakeAppointment3() 
	{
		Users user = new Users("U00273");//correct
		com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("T00000028");//incorrect
		Appointment appointment = new Appointment("12/10/2020 18:35", 'P', test, user);
		
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		assertSame(0, appointmentService.makeAppointment(appointment));
		
	}
	
	@Test
	public void testMakeAppointment4()//All correct values but same appointment time for different user 
	{
		Users user = new Users("U00274");
		com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("T00000280");
		Appointment appointment = new Appointment("28/12/2020 18:35", 'P', test, user);
		
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		assertSame(0, appointmentService.makeAppointment(appointment));
		
	}
	
	@Test
	public void testMakeAppointment5()//All correct values but same appointment time for different test 
	{
		Users user = new Users("U00273");
		com.cg.hcs.entity.Test test = new com.cg.hcs.entity.Test("T00000281");
		Appointment appointment = new Appointment("28/12/2020 18:35", 'P', test, user);
		
		IAppointmentService appointmentService = new AppointmentServiceImpl();
		assertSame(0, appointmentService.makeAppointment(appointment));
		
	}
	
}