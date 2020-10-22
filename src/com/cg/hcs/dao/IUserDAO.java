package com.cg.hcs.dao;

import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;

public interface IUserDAO {

	public String register(Users user) throws HCSException;

	public boolean validateUser(String userId, String password) throws HCSException;

	public String getRoleCode(String userId) throws HCSException;

	public boolean editProfile(Users user) throws HCSException;

	public boolean changePassword(String userId, String password) throws HCSException;

	public String getUsername(String userId) throws HCSException;
	
	public Users getUser(String userId) throws HCSException;
}
