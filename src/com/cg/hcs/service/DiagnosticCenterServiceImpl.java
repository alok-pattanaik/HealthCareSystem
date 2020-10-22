package com.cg.hcs.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.cg.hcs.dao.DiagnosticCenterDAOImpl;
import com.cg.hcs.dao.IDiagnosticCenterDAO;
import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.exception.HCSException;

public class DiagnosticCenterServiceImpl implements IDiagnosticCenterService
{
	static final Logger LOGGER = Logger.getLogger(DiagnosticCenterServiceImpl.class);
	/***********************************
	 * 
	 * @Description :Method to add DiagnosticCenter
	 * @Author : Alok Pattanaik
	 * @arg1 : Center
	 * 
	 * @returns: String
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Override
	public String addCenter(DiagnosticCenter center) 
	{
		IDiagnosticCenterDAO diagnosticCenterDAO = new DiagnosticCenterDAOImpl();
		
		try 
		{
			return diagnosticCenterDAO.addCenter(center);
		} 
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
			return null;
		}
	}
	
	/***********************************
	 * 
	 * @Description : Method to delete DiagnosticCenter
	 * @Author : Alok Pattanaik
	 * @arg1 : Center
	 * 
	 * @returns: boolean
	 * @Exception : HCSException
	 * 
	 ***********************************/
	@Override
	public boolean deleteCenter(String centerId) 
	{
		IDiagnosticCenterDAO diagnosticCenterDAO = new DiagnosticCenterDAOImpl();
		try 
		{
			return diagnosticCenterDAO.deleteCenter(centerId);
		} 
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	/***********************************
	 * 
	 * @Description : Method to view all Centers
	 * @Author : Alok Pattanaik
	 * No Arguments
	 * 
	 * @Exception : HCSException
	 * 
	 ***********************************/
	
	@Override

	public List<DiagnosticCenter> viewAllCenters()
	{
		IDiagnosticCenterDAO diagnosticCenterDAO = new DiagnosticCenterDAOImpl();
		try 
		{
			return diagnosticCenterDAO.viewAllCenters();
		}
		catch (HCSException e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/***********************************
	 * 
	 * @Description : Method to retrive the list of appointment based on location
	 * @Author : Bhavani
	 * @arg1 : String
	 * 
	 * @returns: List<DiagnosticCenter>
	 * @Exception : HCSException
	 ***********************************/
	@Override
	public List<DiagnosticCenter> getDiagnosticCentersListByLocation(String location) 
	{
		IDiagnosticCenterDAO diagnosticCenterDAO = new DiagnosticCenterDAOImpl();
		try
		{
			return diagnosticCenterDAO.getDiagnosticCentersListByLocation(location);
		} 
		catch (HCSException e) 
		{	
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
