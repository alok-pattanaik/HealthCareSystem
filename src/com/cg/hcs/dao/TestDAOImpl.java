package com.cg.hcs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.entity.Test;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.exception.TestException;
import com.cg.hcs.utility.JpaUtility;
import com.cg.hcs.utility.QueryConstants;

/************************
 * @description - This class deals with all the methods related to com.cg.hcs.entity.Test in the database
 * @author - Pratik Prakash, Alok Pattnaik
 * */
public class TestDAOImpl  implements ITestDAO
{
	static final EntityManagerFactory factory = JpaUtility.getFactory();
	static final Logger LOGGER = Logger.getLogger(TestDAOImpl.class);
	
	/***********************************
	 * 
	 * @Description : To delete all the tests under a particular center
	 * @Author : Pratik Prakash
	 * @arg1 : String (center Id of the center)
	 * 
	 * @returns: void
	 * @Exception : TestException
	 ***********************************/
	public void deleteTests (String centerId) throws TestException
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			transaction.begin();
			Query query = manager.createQuery(QueryConstants.DELETE_TESTS_BY_CENTER);
			query.setParameter("centerId", centerId);
			query.executeUpdate();
			transaction.commit();
		}
		catch (PersistenceException e)
		{
			throw new TestException("Error while deleting tests for center : "+centerId+" due to "+e.getMessage());
		}
		finally
		{
			manager.close();
		}
	}
	
	/*********************
	 * @Description This method is used to add a new Test to a center in the database
	 * @Author : Alok Pattanaik
	 * 
	 * @param - Test object to be added
	 * 
	 * @return - String which is the test Id of the newly created test
	 * 
	 * @exception : HCSException 
	 * *********************/
	@Override
	public String addTest(Test test) throws TestException 
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			LOGGER.info("In Admin DAO - addTest method.");
			
			transaction.begin();
			DiagnosticCenter center = test.getCenter();
			DiagnosticCenter centerFetched = manager.find(DiagnosticCenter.class, center.getCenterId());
			test.setCenter(centerFetched);
			manager.persist(test);
			transaction.commit();
		}
		catch (PersistenceException e)
		{
			LOGGER.warn("Error while adding a test");
			if(transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
			throw new TestException("Error while adding the test due to "+ e.getMessage());
		}
		finally
		{
			manager.close();
		}
		return test.getTestId();
	}
	
	/************************
	 *  @Description This method is used to delete a Test existing in a center in the database
	 * @author -  Pratik Prakash
	 * 
	 * @param - Test object which is to be deleted
	 * 
	 * @return - boolean - Whether or not the test is deleted
	 * 
	 * Exception : HCSException
	 * ***********************/
	@Override
	public boolean removeTest(String testId) throws HCSException 
	{
		AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
		appointmentDAO.deleteAppointmentByTest(testId);
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			LOGGER.info("In Admin DAO - removeTest.");
			transaction.begin();
			Query query = manager.createQuery(QueryConstants.DELETE_TEST);
			query.setParameter("testId", testId);
			query.executeUpdate();
			transaction.commit();
			return true;
		}
		catch (PersistenceException e) 
		{
			LOGGER.warn("Error while removing test.");
			throw new TestException("Error while removing test due to "+e.getMessage());
		}
		finally
		{
			manager.close();
		}
	}
	
	
	
	
	
	/*******************
	 * @description - This method is used to retrieve all the tests existing in a center in the database
	 * @author - Pratik Prakash
	 * 
	 * @param - String - center Id for which all the tests are to be retrieved
	 * 
	 * @return - List of Tests under that particular center
	 * 
	 * @exception : TestException */
	@Override
	public List<Test> viewAllTest(String centerId) throws TestException 
	{
		EntityManager manager = factory.createEntityManager();
		List<Test> listOfTests = null;
		try
		{	
			LOGGER.info("In Admin DAO - ViewAllTest.");
			
			TypedQuery<Test> query = manager.createQuery(QueryConstants.GET_TEST_LIST,Test.class);
			query.setParameter("centerId", centerId);
			listOfTests = query.getResultList();
			System.out.println(listOfTests);
			return listOfTests;
		}
		catch (PersistenceException e) 
		{
			LOGGER.warn("Error while retriving all the test under a particular ceter.");
			throw new TestException("Error while retreiving all tests");
		}
	}
}
