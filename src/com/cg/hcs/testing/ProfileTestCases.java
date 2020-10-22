package com.cg.hcs.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.hcs.entity.Users;
import com.cg.hcs.service.IUserService;
import com.cg.hcs.service.UserServiceImpl;

public class ProfileTestCases {

	/*************
	 * 
	 * @Description : Testing Register Method
	 * No Arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testRegister() {
		Users user = new Users("Abc@1234", "Test User", 78543210l, "usr2@xyz.co", "usr");
		
		IUserService profileService = new UserServiceImpl();
		assertNotNull(profileService.register(user));

	}

	/*************
	 * 
	 * @Description : Testing RoleCode Method
	 * No Arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	// Test to check getRoleCode()
	@Test
	public void testGetRoleCode() 
	{
		
		IUserService profileService = new UserServiceImpl();
		assertEquals("usr",profileService.getRoleCode("U00032"));
	}

	/*************
	 * 
	 * @Description : Testing method to test for a valid user
	 * No arguments
	 * 
	 * @returns: void
	 * 
	 *************/
	@Test
	public void testValidateUser() {
		String userId = "U00032";
		String password = "Abcd@123";
		IUserService profileService = new UserServiceImpl();
		boolean status = profileService.validateUser(userId, password);
		assertTrue(status);
	}
	
	@Test
	public void testValidateUser2()
	{
		String userId = "U00001";
		String password = "Incorrect";
		IUserService profileService = new UserServiceImpl();
		boolean status = profileService.validateUser(userId, password);
		assertFalse(status);
	}
	
	@Test
	public void testValidateUser3()
	{
		String userId = "Incorrect";
		String password = "Abcd@123";
		IUserService profileService = new UserServiceImpl();
		boolean status = profileService.validateUser(userId, password);
		assertFalse(status);
	}
	
	@Test
	public void testValidateUser4()
	{
		String userId = "Incorrect";
		String password = "Incorrect";
		IUserService profileService = new UserServiceImpl();
		boolean status = profileService.validateUser(userId, password);
		assertFalse(status);
	}
	
}
