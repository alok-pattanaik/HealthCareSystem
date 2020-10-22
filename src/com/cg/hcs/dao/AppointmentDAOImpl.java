package com.cg.hcs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.entity.Test;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.utility.JpaUtility;
import com.cg.hcs.utility.QueryConstants;

public class AppointmentDAOImpl implements IAppointmentDAO
{
	static final EntityManagerFactory factory = JpaUtility.getFactory();
	static final Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class);
	
	public final void deleteAppointmentByCenter(String centerId) throws HCSException
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			transaction.begin();
			Query query = manager.createQuery(QueryConstants.DELETE_APPOINTMENT_BY_CENTER);
			query.setParameter("centerId", centerId);
			query.executeUpdate();
			transaction.commit();
		}
		catch(PersistenceException e)
		{
			throw new HCSException("Error while deleting appointments for center : "+centerId+" due to "+e.getMessage());
		}
		finally
		{
			manager.close();
		}
	}
	
	public final void deleteAppointmentByTest(String testId) throws HCSException
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			transaction.begin();
			Query query = manager.createQuery(QueryConstants.DELETE_APPOINTMENTS_BY_TEST);
			query.setParameter("testId", testId);
			query.executeUpdate();
			transaction.commit();
		}
		catch (PersistenceException e) 
		{
			throw new HCSException("Error while deleting appointments for test : "+testId+"due to "+e.getMessage());
		}
		finally
		{
			manager.close();
		}
	}
	
	/* This method is used to approve or reject an appointment of a test existing in the database
	 * Author - Yashaswini
	 * 
	 * Argument - int - Appointment Id of the appointment, char - The app status to which the appointment will update
	 * 
	 * return type - boolean - Whether or not the appointment's status is changed
	 * 
	 * Exception : HCSException */
	@Override
	public boolean approveRejectAppointment(int appId,char appStatus) throws HCSException 
	{
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try
		{
			LOGGER.info("In Admin DAO - approveRejectMethod.");
			
			transaction.begin();
			Appointment appointmentFetched = manager.find(Appointment.class, appId);
			appointmentFetched.setAppStatus(appStatus);
			manager.persist(appointmentFetched);
			transaction.commit();
			return true;
		}
		catch (PersistenceException e)
		{
			LOGGER.warn("Error while Approving or rejecting the appointment.");
			if(transaction.isActive())
				transaction.rollback();
			throw new HCSException("Cannot find appointment"+ e.getMessage());
		}
		finally
		{
			manager.close();
		}	
	}
	
	/* This method is used to retrieve all the appointments existing in a center in the database
	 * Author - Yashaswini
	 * 
	 * Argument - String - center Id for which all the appointments are to be retrieved
	 * 
	 * return type - List of Appointments under that particular center
	 * 
	 * Exception : HCSException */
	@Override
	public List<Appointment> viewAllAppointmentsByCenter(String centerId) throws HCSException 
	{
		EntityManager manager = factory.createEntityManager();
		try
		{
			LOGGER.info("In Admin DAO - viewAllAppointmentsByCenter.");
			TypedQuery<Appointment> query = manager.createQuery(QueryConstants.GET_ALL_APPOINTMENT_BY_CENTER,Appointment.class);
			query.setParameter("centerId", centerId);
			List<Appointment> listOfPendingAppointments = query.getResultList();
			return listOfPendingAppointments;
		}
		catch (PersistenceException e) 
		{
			LOGGER.warn("Error while retriving all appointments under a center.");
			throw new HCSException("Error while retreiving all appointments"+ e.getMessage());
		}
		finally
		{
			manager.close();
		}
		
	}
	
	/***********************************
	 * 
	 * @Description : Method to book an appointment
	 * @Author : Bhavani
	 * @arg1 : Appointment
	 * 
	 * @returns: int
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public int makeAppointment(Appointment appointment)  throws HCSException
	{

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			LOGGER.info("Inside make appointment method.");			
			
			transaction.begin();
			Users user = manager.find(Users.class, appointment.getUser().getUserId());
			Test test = manager.find(Test.class, appointment.getTest().getTestId());
			DiagnosticCenter center = manager.find(DiagnosticCenter.class, test.getCenter().getCenterId());
			appointment.setUser(user);
			appointment.setTest(test);
			appointment.setCenter(center);
			manager.persist(appointment);
			transaction.commit();
			return appointment.getAppId();
		} catch (PersistenceException e) {
			LOGGER.info("Error while booking appointment for patient.");
			
			if (transaction.isActive())
				transaction.rollback();
			throw new HCSException("Error while making appointment" +e.getMessage());
			
		} finally {
			manager.close();
		}

	}
	
	/***********************************
	 * 
	 * @Description : Method to retrive the Status of the Appointment
	 * @Author : Reshma
	 * @arg1 : String
	 * 
	 * @returns: List<Appointment>
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Transactional
	@Override
	public List<Appointment> getAppointmentStatus(Users user) throws HCSException {

		EntityManager manager = factory.createEntityManager();
		List<Appointment> appointmentList = null;
		try {
			LOGGER.info("Inside get appointment status method.");
			
			TypedQuery<Appointment> query = manager.createQuery(QueryConstants.GET_APPOINTMENT_STATUS,
					Appointment.class);
			query.setParameter("user", user.getUserId());
			appointmentList = query.getResultList();
			return appointmentList;
		} catch (PersistenceException e) {
			LOGGER.info("Error while fetching the status of appointment by customer.");
			throw new HCSException("Error while retrieving all appointments");
		} finally {
			manager.close();
		}
	}
}
