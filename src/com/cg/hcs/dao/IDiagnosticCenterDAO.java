package com.cg.hcs.dao;

import java.util.List;

import com.cg.hcs.entity.DiagnosticCenter;
import com.cg.hcs.exception.HCSException;

public interface IDiagnosticCenterDAO 
{
	public String addCenter(DiagnosticCenter center) throws HCSException;
	
	public boolean deleteCenter(String centerId) throws HCSException;
	
	public List<DiagnosticCenter> viewAllCenters() throws HCSException;
	
	public List<DiagnosticCenter> getDiagnosticCentersListByLocation(String location) throws HCSException;
}
