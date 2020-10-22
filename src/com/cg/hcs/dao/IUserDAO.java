package com.cg.hcs.dao;

import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.UserException;

public interface IUserDAO {

	public String register(Users user) throws UserException;

	public boolean validateUser(String userId, String password) throws UserException;

	public String getRoleCode(String userId) throws UserException;

	public boolean editProfile(Users user) throws UserException;

	public boolean changePassword(String userId, String password) throws UserException;

	public String getUsername(String userId) throws UserException;
	
	public Users getUser(String userId) throws UserException;
}
