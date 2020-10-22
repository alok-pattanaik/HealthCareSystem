package com.cg.hcs.dao;

import java.util.List;

import com.cg.hcs.entity.Test;
import com.cg.hcs.exception.HCSException;
import com.cg.hcs.exception.TestException;

public interface ITestDAO
{
	public String addTest(Test test) throws TestException;
	
	boolean removeTest(String testId) throws HCSException;
	
	public List<Test> viewAllTest(String centerId) throws TestException;
}
