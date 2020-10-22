package com.cg.hcs.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.UserException;
import com.cg.hcs.utility.JpaUtility;

/***************************************
 * 
 * Description : This class deals with com.cg.hcs.entity.Users in the database
 * 
 * @author : Reshma, Yashaswini, Bhavani
 * @Date : 20/10/2020
 * 
 ***************************************/

public class UserDAOImpl implements IUserDAO
{
	
	static final EntityManagerFactory factory = JpaUtility.getFactory();
	EntityManager manager = null;
	EntityTransaction transaction = null;

	static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	/***********************************
	 * 
	 * @Description : User registration Method
	 * @Author : Reshma
	 * @arg1 : Users
	 * 
	 * @returns: String
	 * @Exception : UserException
	 ***********************************/
	@Override
	public String register(Users user) throws UserException 
	{
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();

		try {
			LOGGER.info("Inside User registration Method");
			
			manager.persist(user);
			transaction.commit();
		} catch (PersistenceException e) {
			LOGGER.info("Error while registering the user");
			
			if (transaction.isActive())
				transaction.rollback();
			throw new UserException("Error while registering the new user");
		} finally {
			manager.close();
		}
		return user.getUserId();
	}

	/***********************************
	 * 
	 * @Description : Method to retrive the RoleCode
	 * @Author : Yashaswini
	 * @arg1 : String
	 * 
	 * @returns: String
	 * @Exception : UserException
	 * 
	 ***********************************/

	@Override
	public String getRoleCode(String userId) throws UserException {

		manager = factory.createEntityManager();
		Users user = null;

		try 
		{
			user = manager.find(Users.class, userId);
		}
		catch (PersistenceException e)
		{
			throw new UserException("Error while retreiving the role code");
		}
		finally 
		{
			manager.close();
		}
		return user.getUserRole();
	}

	/***********************************
	 * 
	 * @Description : Validate user Method
	 * @Author : Yashaswini
	 * @arg1 : String,String
	 * 
	 * @returns: Boolean
	 * @Exception : UserException
	 ***********************************/
	@Override
	public boolean validateUser(String userId, String password) throws UserException {

		manager = factory.createEntityManager();
		Users user = null;

		try {
			LOGGER.info("Inside validate user method.");
			System.out.println("In dao :"+userId+"---"+password);
			user = manager.find(Users.class, userId);
			System.out.println("In validate user : "+user);
			if (user!=null && user.getUserPassword().equals(password) )
				return true;
			else
				return false;
		} catch (PersistenceException e) {
			LOGGER.info("Error while validating the user");
			
			throw new UserException("Error while validating user");
		} finally {
			manager.close();
		}

	}
	
	/***********************************
	 * 
	 * @Description : Edit profile Method
	 * @Author : Reshma
	 * @arg1 : User
	 * 
	 * @returns: Boolean
	 * @Exception : UserException
	 ***********************************/
	@Override
	public boolean editProfile(Users user) throws UserException 
	{
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		try {
			LOGGER.info("Inside edit profile method.");
			
			transaction.begin();
			Users usr = manager.find(Users.class, user.getUserId());
			usr.setUserName(user.getUserName());
			usr.setContactNo(user.getContactNo());
			usr.setEmail(user.getEmail());
			manager.persist(usr);
			transaction.commit();
			return true;
		}
		catch (PersistenceException e) 
		{
			LOGGER.info("Error while editing profile");	
			throw new UserException("Error while editing profile");
		}
		finally
		{
			manager.close();
		}
		
	}

	/***********************************
	 * 
	 * @Description : Change password Method
	 * @Author : Reshma
	 * @arg1 : String,String
	 * 
	 * @returns: Boolean
	 * @Exception : UserException
	 ***********************************/
	@Override
	public boolean changePassword(String userId, String password) throws UserException {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		Users user = null;

		try {
			LOGGER.info("Inidechange password.");
			transaction.begin();
			user = manager.find(Users.class, userId);
			System.out.println("in changepwd dao : "+user);
			user.setUserPassword(password);
			manager.persist(user);
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			LOGGER.info("Error while retriving the user details.");
			if (transaction.isActive())
				transaction.rollback();
			throw new UserException("Error while retreiving user");
		} finally {
			manager.close();
		}
	}


	/***********************************
	 * 
	 * @Description : Method to retrieve the username using userId
	 * @Author : Alok
	 * @arg1 : String
	 * 
	 * @returns: String
	 * @Exception : UserException
	 * 
	 ***********************************/
	@Override
	public String getUsername(String userId) throws UserException {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

		Users user = null;

		try 
		{		
			transaction.begin();
			user = manager.find(Users.class, userId);
			transaction.commit();
			return user.getUserName();
		}
		catch (RuntimeException e) 
		{
			if (transaction.isActive())
				transaction.rollback();
			throw new UserException("Error while retrieving username due to "+e.getMessage());
		}
		finally 
		{
			manager.close();
		}
			
	}
	
	
	/***********************************
	 * 
	 * @Description : Method to retrieve the user object using userId
	 * @Author : Alok Pattnaik
	 * @arg1 : String
	 * 
	 * @returns: String
	 * @Exception : UserException
	 * 
	 ***********************************/
	@Override
	public Users getUser(String userId) throws UserException {

		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

		Users user = null;

		try {
			LOGGER.info("Inide get user method.");
			
			transaction.begin();
			user = manager.find(Users.class, userId);
			transaction.commit();
			return user;
		} 
		catch (PersistenceException e) 
		{
			LOGGER.info("Error while retriving the user details.");
			if (transaction.isActive())
				transaction.rollback();
			throw new UserException("Error while retreiving user due to "+e.getMessage());
		}
		finally 
		{
			manager.close();
		}

	}

}
