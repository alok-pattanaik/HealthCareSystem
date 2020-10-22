package com.cg.hcs.service;

import java.util.List;

import com.cg.hcs.entity.DiagnosticCenter;

public interface IDiagnosticCenterService 
{
	public String addCenter(DiagnosticCenter center);

	public List<DiagnosticCenter> viewAllCenters();
	
	public boolean deleteCenter(String centerId);
	
	public List<DiagnosticCenter> getDiagnosticCentersListByLocation(String location);
}
