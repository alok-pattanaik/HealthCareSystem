package com.cg.hcs.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.ITestDAO;
import com.cg.hcs.dao.TestDAOImpl;
import com.cg.hcs.entity.Test;
import com.cg.hcs.exception.HCSException;

public class TestServiceImpl implements ITestService
{
	
	static final Logger LOGGER = Logger.getLogger(TestServiceImpl.class);
	
	/***********************************
	 * 
	 * @Description : Method to add Test under a particular DiagnosticCenter
	 * @Author : Pratik Prakash
	 * @arg1 : Test
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Override
	public String addTest(Test test) {
		ITestDAO testDAO = new TestDAOImpl();
		try 
		{
			return testDAO.addTest(test);
		} 
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}
	
	

	
	/***********************************
	 * 
	 * @Description : Method to remove Test
	 * @Author : Pratik Prakash
	 * @arg1 : Test
	 * 
	 * @returns: boolean
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Override
	public boolean removeTest(String testId) 
	{
		ITestDAO testDAO = new TestDAOImpl();
		try 
		{
			return testDAO.removeTest(testId);
		} 
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());		
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Method to view all Tests
	 * @Author : Pratik Prakash
	 * @arg1 : centerId
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 * 
	 ***********************************/


	@Override
	public List<Test> viewAllTest(String centerId) 
	{
		ITestDAO testDAO = new TestDAOImpl();
		try 
		{
			return testDAO.viewAllTest(centerId);
		}
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
