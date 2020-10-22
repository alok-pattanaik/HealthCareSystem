package com.cg.hcs.service;

import com.cg.hcs.entity.Users;

public interface IUserService {

	public String register(Users user);
	
	public boolean validateUser(String userId, String password);
	
	public String getRoleCode(String userId);
	
	public boolean editProfile(Users user);
	
	public boolean changePassword(String userId, String password);
	
	public String getUsername(String userId);
	
	public Users getUser(String userId);
	
}
