package com.cg.hcs.service;

import java.util.List;

import com.cg.hcs.entity.Test;

public interface ITestService 
{
	public String addTest(Test test);
	
	public List<Test> viewAllTest(String centerId);

	public boolean removeTest(String testId);
	
}
