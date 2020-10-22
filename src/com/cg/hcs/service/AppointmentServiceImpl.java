package com.cg.hcs.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.AppointmentDAOImpl;
import com.cg.hcs.dao.IAppointmentDAO;
import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;

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
	 * @Exception : HCSException
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
		catch (HCSException e) 
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
	 * @returns: List<Appointment>
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public List<Appointment> viewAllAppointmentsByCenter(String centerId)
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try 
		{
			return appointmentDAO.viewAllAppointmentsByCenter(centerId);
		}
		catch (HCSException e) 
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
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public int makeAppointment(Appointment appointment) 
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try
		{
			return appointmentDAO.makeAppointment(appointment);
		} 
		catch (HCSException e) 
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
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public List<Appointment> getAppointmentStatus(Users user)
	{
		IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		try
		{
			return appointmentDAO.getAppointmentStatus(user);
		} 
		catch (HCSException e) 
		{	
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
