package com.cg.hcs.dao;

import java.util.List;

import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.AppointmentException;

public interface IAppointmentDAO 
{
	public boolean approveRejectAppointment(int appId, char appStatus) throws AppointmentException;
	
	public List<Appointment> viewAllAppointmentsByCenter(String centerId) throws AppointmentException;
	
	public List<Appointment> getAppointmentStatus(Users user) throws AppointmentException;

	public int makeAppointment(Appointment appointment) throws AppointmentException;
}
