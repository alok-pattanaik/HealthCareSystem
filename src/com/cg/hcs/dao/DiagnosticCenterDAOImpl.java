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
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.utility.JpaUtility;
import com.cg.hcs.utility.QueryConstants;

public class DiagnosticCenterDAOImpl implements IDiagnosticCenterDAO
{
	static final EntityManagerFactory factory = JpaUtility.getFactory();
	static final Logger LOGGER = Logger.getLogger(DiagnosticCenterDAOImpl.class);
	
	/* This method is used to add a new Diagnostic Center to the database
	 * Author- Pratik Prakash
	 * 
	 * Argument - DiagnosticCenter object to be added in the database
	 * 
	 * return type - String which is the center Id of the newly created center
	 * 
	 * Exception : HCSException */
	@Override
	public String addCenter(DiagnosticCenter center) throws HCSException 
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
			throw new HCSException("Error while commiting the transaction"+ e.getMessage());
			
		}
		finally 
		{
			manager.close();
		}
		return center.getCenterId();
		
		
	}
	
	/******************************************************* 
	 * @Description - This method is used to delete a Diagnostic Center existing in the database
	 * @author PratikPrakash
	 * 
	 * Argument - String centerId of the diagnostic center which is to be deleted
	 * 
	 * return type - boolean - Whether or not the center is deleted
	 * 
	 * Exception : HCSException 
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
			throw new HCSException("Error while removing the center" + e.getMessage());
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
	public List<DiagnosticCenter> getDiagnosticCentersListByLocation(String centerAddress) throws HCSException {
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
			
			throw new HCSException("Error while fetching centers List by location");
		} 
		finally
		{
			manager.close();
		}

		return centersList;
	}
	
	/* This method is used to retrieve all the centers in the database
	 * Author - Alok Pattanaik
	 * 
	 * No Arguments
	 * 
	 * return type - List of all Diagnostic Centers
	 * 
	 * Exception : HCSException */
	@Override
	public List<DiagnosticCenter> viewAllCenters() throws HCSException 
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
			throw new HCSException("Error retrieving centers list");
		}
		finally 
		{
			manager.close();
		}
	}
	
	
}
