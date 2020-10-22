package com.cg.hcs.service;

public interface IValidation {
	public boolean isValidUsername(String username);

	public boolean isValidPassword(String password);

	public boolean isValidContactNumber(String contactnumber);
}
