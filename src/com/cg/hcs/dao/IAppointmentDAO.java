package com.cg.hcs.dao;

import java.util.List;

import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;
import com.cg.hcs.exception.HCSException;

public interface IAppointmentDAO 
{
	public boolean approveRejectAppointment(int appId, char appStatus) throws HCSException;
	
	public List<Appointment> viewAllAppointmentsByCenter(String centerId) throws HCSException;
	
	public List<Appointment> getAppointmentStatus(Users user) throws HCSException;

	public int makeAppointment(Appointment appointment) throws HCSException;
}
