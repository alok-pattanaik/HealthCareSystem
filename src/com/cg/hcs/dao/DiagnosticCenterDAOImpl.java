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
import com.cg.hcs.exception.DiagnosticCenterException;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.utility.JpaUtility;
import com.cg.hcs.utility.QueryConstants;

/************************
 * @description - This class deals with all the methods related to Appointment in the database
 * @author - Pratik Prakash, Bhavani, Alok Pattnaik
 * */
public class DiagnosticCenterDAOImpl implements IDiagnosticCenterDAO
{
	static final EntityManagerFactory factory = JpaUtility.getFactory();
	static final Logger LOGGER = Logger.getLogger(DiagnosticCenterDAOImpl.class);
	
	/************************* 
	 * @description - This method is used to add a new Diagnostic Center to the database
	 * @author - Pratik Prakash
	 * 
	 * @param - DiagnosticCenter object to be added in the database
	 * 
	 * @return - String which is the center Id of the newly created center
	 * 
	 * @exception : HCSException 
	 **************************/
	@Override
	public String addCenter(DiagnosticCenter center) throws DiagnosticCenterException 
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			LOGGER.info("In Admin DAO - addCenter method.");
			transaction.begin();
			manager.persist(center);
			transaction.commit();
			
		}
		catch (PersistenceException e)
		{
			LOGGER.warn("Error while adding the center.");
			if(transaction.isActive())
				transaction.rollback();
			throw new DiagnosticCenterException("Error while commiting the transaction due to"+ e.getMessage());
			
		}
		finally 
		{
			manager.close();
		}
		return center.getCenterId();
		
		
	}
	
	/******************************************************* 
	 * @Description - This method is used to delete a Diagnostic Center existing in the database
	 * @author - Pratik Prakash
	 * 
	 * @param - String centerId of the diagnostic center which is to be deleted
	 * 
	 * @return - boolean - Whether or not the center is deleted
	 * 
	 * @exception : HCSException 
	 * ******************************************************/
	@Override
	public boolean deleteCenter(String centerId) throws HCSException 
	{
		AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
		TestDAOImpl testDAO = new TestDAOImpl();
		appointmentDAO.deleteAppointmentByCenter(centerId);
		testDAO.deleteTests(centerId);
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			LOGGER.info("In Admin DAO - deleteCenter method.");
			transaction.begin();
			Query query = manager.createQuery(QueryConstants.DELETE_CENTER);
			query.setParameter("centerId", centerId);
			int deleted = query.executeUpdate();
			if(deleted == 0)
				return false;
			transaction.commit();
			return true;
		}
		catch (PersistenceException e) 
		{
			LOGGER.warn("Error while deleting a center.");
			throw new DiagnosticCenterException("Error while removing the center due to " + e.getMessage());
		}
		finally
		{
			manager.close();
		}
		
	}
	
	/***********************************
	 * 
	 * @Description : Method to retrive the list of centers based on location 
	 * @Author : Bhavani
	 * @arg1 : String
	 * 
	 * @returns: List<DiagnosticCenter>
	 * @Exception : HCSException
	 * 
	 ***********************************/

	@Override
	public List<DiagnosticCenter> getDiagnosticCentersListByLocation(String centerAddress) throws DiagnosticCenterException {
		EntityManager manager = factory.createEntityManager();
		List<DiagnosticCenter> centersList = null;
		try
		{
			LOGGER.info("Inside get diagnostic center list by location  method.");
			
			TypedQuery<DiagnosticCenter> query = manager.createQuery(QueryConstants.GET_DIAGNOSTICCENTER_LIST_BY_LOCATION,
					DiagnosticCenter.class);
			query.setParameter("centerAddress", centerAddress);
			centersList = query.getResultList();
		}
		catch (PersistenceException e)
		{
			LOGGER.info("Error while fetching the diagnostic centers list by location ");
			
			throw new DiagnosticCenterException("Error while fetching centers List by location due to "+e.getMessage());
		} 
		finally
		{
			manager.close();
		}

		return centersList;
	}
	
	/******************************
	 * @description -  This method is used to retrieve all the centers in the database
	 * @author - Alok Pattanaik
	 * 
	 * @param - No Arguments
	 * 
	 * @return - List<DiagnosticCenter> of all Diagnostic Centers
	 * 
	 * @exception : DiagnosticCenterException */
	@Override
	public List<DiagnosticCenter> viewAllCenters() throws DiagnosticCenterException 
	{
		EntityManager manager = factory.createEntityManager();
		List<DiagnosticCenter> centersList = null;
		try
		{
			LOGGER.info("In Admin DAO - viewAllCenters.");
			TypedQuery<DiagnosticCenter> query = manager.createQuery(QueryConstants.GET_DIAGNOSTICCENTER_LIST, DiagnosticCenter.class);
			centersList = query.getResultList();
			return centersList;
		}
		catch (PersistenceException e)
		{
			LOGGER.warn("Error while retriving all centers.");
			throw new DiagnosticCenterException("Error retrieving centers list due to "+e.getMessage());
		}
		finally 
		{
			manager.close();
		}
	}
	
	
}
