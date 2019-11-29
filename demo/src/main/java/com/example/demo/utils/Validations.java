package com.example.demo.utils;

import java.util.regex.Pattern;

public class Validations {
	
	public static boolean validateEmail(String email) {
		final String regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}";
		return Pattern.matches(regex, email);
	}
	
	public static boolean validatePassword(String password) {
		final String regex = "^[a-z]*[A-Z][a-z]*[0-9]{2}$";
		return Pattern.matches(regex, password);
	}
}
