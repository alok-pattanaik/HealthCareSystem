package com.cg.hcs.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.AppointmentDAOImpl;
import com.cg.hcs.dao.IAppointmentDAO;
import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.AppointmentException;

public class AppointmentServiceImpl implements IAppointmentService
{
	static final Logger LOGGER = Logger.getLogger(AppointmentServiceImpl.class);
	
	/***********************************
	 * 
	 * @Description : Method to approve and reject the Appointment
	 * @Author : Yashaswini
	 * @arg1 : Appointment, char
	 * 
	 * @returns: boolean
	 * @Exception : AppointmentException
	 * 
	 ***********************************/
	@Override
	public boolean approveRejectAppointment(int appId, char appStatus) 
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try 
		{
			return appointmentDAO.approveRejectAppointment(appId, appStatus);
		}
		catch (AppointmentException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Methods to view all appointments under a center
	 * @Author : Yashaswini
	 * @arg1 : DiagnosticCenter
	 * 
	 * @return : List<Appointment>
	 * @Exception : AppointmentException
	 ***********************************/
	@Override
	public List<Appointment> viewAllAppointmentsByCenter(String centerId)
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try 
		{
			return appointmentDAO.viewAllAppointmentsByCenter(centerId);
		}
		catch (AppointmentException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/***********************************
	 * 
	 * @Description : Method to book an appointment
	 * @Author : Bhavani
	 * @arg1 : Appointment
	 * 
	 * @returns: int
	 * @Exception : AppointmentException
	 ***********************************/
	@Override
	public int makeAppointment(Appointment appointment) 
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try
		{
			return appointmentDAO.makeAppointment(appointment);
		} 
		catch (AppointmentException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/***********************************
	 * 
	 * @Description : Method to retrive the Status of the Appointment
	 * @Author : Reshma
	 * @arg1 : String
	 * 
	 * @returns: List<Appointment>
	 * @Exception : AppointmentException
	 ***********************************/
	@Override
	public List<Appointment> getAppointmentStatus(Users user)
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try
		{
			return appointmentDAO.getAppointmentStatus(user);
		} 
		catch (AppointmentException e) 
		{	
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
