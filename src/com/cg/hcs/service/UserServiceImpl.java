package com.cg.hcs.service;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.IUserDAO;
import com.cg.hcs.dao.UserDAOImpl;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;

public class UserServiceImpl implements IUserService
{
	IUserDAO userDao = new UserDAOImpl();
	static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	/***********************************
	 * 
	 * @Description : User registration Method
	 * @Author : Reshma
	 * @arg1 : Users
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public String register(Users user) {
		try 
		{
			return userDao.register(user);
		}
		catch (HCSException e)
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/***********************************
	 * 
	 * @Description : Method to retrive the RoleCode
	 * @Author : Yashaswini
	 * @arg1 : String
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Override
	public String getRoleCode(String userId) {
		
		try 
		{
			return userDao.getRoleCode(userId);
		}
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/***********************************
	 * 
	 * @Description : Validate User Method
	 * @Author : Yashaswini
	 * @arg1 : String,String
	 * 
	 * @returns: Boolean
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public boolean validateUser(String userId, String password) 
	{
		
		try 
		{
			return userDao.validateUser(userId, password);
		} 
		catch (HCSException e) 
		{	
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Edit PRofile Method
	 * @Author : Reshma
	 * @arg1 : User
	 * 
	 * @returns: Boolean
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public boolean editProfile(Users user) {
		try 
		{
			return userDao.editProfile(user);
		}
		catch (HCSException e)
		{
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Get username  Method
	 * @Author : Reshma
	 * @arg1 : String
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public String getUsername(String userId) {
		try 
		{
			return userDao.getUsername(userId);
		}
		catch (HCSException e)
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/***********************************
	 * 
	 * @Description : Change password Method
	 * @Author : Reshma
	 * @arg1 : String,String
	 * 
	 * @returns: Boolean
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public boolean changePassword(String userId, String password) {
		try 
		{
			return userDao.changePassword(userId, password);
		}
		catch (HCSException e)
		{
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Method to retrieve the user based on userId
	 * @Author : Bhavani
	 * @arg1 : String
	 * 
	 * @returns: Users
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public Users getUser(String userId) 
	{
		IUserDAO userDAO = new UserDAOImpl();
		try 
		{
			return userDAO.getUser(userId);
		}
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
