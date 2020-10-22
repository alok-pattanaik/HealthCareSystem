/*package com.cg.hcs.queryconstants;

*//**********************************
 * @Description: HealthCareSystem QueryConstants
 * @author : Bhavani, Reshma
 * @Date : 12/10/2020
 *
 **********************************//*
public class QueryConstants {
	
	
	//User Queries
	
	public static final String GET_DIAGNOSTICCENTER_LIST = "select c from DiagnosticCenter c";
	
	public static final String GET_TEST_LIST = "SELECT t FROM Test t where t.center.centerId = :centerId";
	
	public static final String GET_TEST = "SELECT t from Test t where t.testId = ?";
	
	public static final String GET_APPOINTMENT_STATUS = "Select a from Appointment a where a.user.userId = :user";
	
	public static final String GET_DIAGNOSTICCENTER_LIST_BY_LOCATION = "select c from DiagnosticCenter c where c.centerAddress like concat('%',:centerAddress,'%')";
	
	//Admin Queries
	
	public static final String GET_DELETE_STATUS = "Delete a from appoinment a where a.center = ?";
	
	public static final String GET_ALL_TESTS = "SELECT t from Test t WHERE t.center=?";
	
	public static final String GET_ALL_PENDING_APPOINTMENT_BY_CENTER = "SELECT a FROM Appointment a WHERE a.center = ? AND a.isApproved = ?";
}
*/
package com.cg.hcs.utility;

/****
 * @Description: HealthCareSystem QueryConstants
 * @author : Bhavani, Reshma, Pratik
 * @Date : 16/10/2020
 *
 ****/
public class QueryConstants {
	
	
	//User Queries
	
	public static final String GET_TEST = "SELECT t from Test t where t.testId = ?";
	
	public static final String GET_APPOINTMENT_STATUS = "Select a from Appointment a where a.user.userId = :user";
	
	public static final String GET_DIAGNOSTICCENTER_LIST_BY_LOCATION = "select c from DiagnosticCenter c where c.centerAddress like concat('%',:centerAddress,'%')";
	
	public static final String EDIT_PROFILE = "update users u set u.username= :username, u.contactNo= :contactNo, u.email= :email where u.userId= :userId";

	
	//Admin Queries
	
	public static final String DELETE_TEST = "DELETE from Test t where t.testId = :testId";
	
	public static final String GET_ALL_APPOINTMENT_BY_CENTER = "SELECT a FROM Appointment a WHERE a.center.centerId = :centerId";
	
	public static final String DELETE_CENTER = "DELETE from DiagnosticCenter c where c.centerId = :centerId";
	
	public static final String DELETE_APPOINTMENT_BY_CENTER = "DELETE from Appointment a WHERE a.center.centerId = :centerId";
	
	public static final String DELETE_TESTS_BY_CENTER = "DELETE from Test t where t.center.centerId = :centerId";
	
	public static final String DELETE_APPOINTMENTS_BY_TEST = "delete from Appointment a WHERE a.test.testId = :testId";
	
	
//	public static final String 
	
	//Admin and User Queries
	
	public static final String GET_DIAGNOSTICCENTER_LIST = "SELECT c from DiagnosticCenter c";
	
	public static final String GET_TEST_LIST = "SELECT t FROM Test t WHERE t.center.centerId = :centerId";

	
}