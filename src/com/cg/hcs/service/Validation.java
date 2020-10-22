package com.cg.hcs.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation implements IValidation {

	@Override
	public boolean isValidUsername(String username) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("[^A-Za-z ]");
		Matcher m = p.matcher(username);
		// boolean b = m.matches();
		boolean b = m.find();
		if (b)
			return false;
		else
			return true;
	}

	@Override
	public boolean isValidPassword(String password) {
		if (password.length() >= 8) {
			Pattern letter = Pattern.compile("[a-zA-z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
			// Pattern eight = Pattern.compile (".{8}");

			Matcher hasLetter = letter.matcher(password);
			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecial = special.matcher(password);

			return hasLetter.find() && hasDigit.find() && hasSpecial.find();

		} else
			return false;
	}

	@Override
	public boolean isValidContactNumber(String contactNumber) {
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(contactNumber);
		// boolean b = m.matches();
		boolean b = m.find();
		if (b)
			return false;
		else
			return true;
	}

}
