package com.cg.hcs.service;

import java.util.List;

import com.cg.hcs.entity.Appointment;
import com.cg.hcs.entity.Users;

public interface IAppointmentService 
{
	public List<Appointment> viewAllAppointmentsByCenter(String centerId);

	public boolean approveRejectAppointment(int appId, char appStatus);
	
	public int makeAppointment(Appointment appointment);
	
	public List<Appointment> getAppointmentStatus(Users user);
}
