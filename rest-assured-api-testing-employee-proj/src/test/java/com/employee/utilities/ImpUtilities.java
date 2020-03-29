package com.employee.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class ImpUtilities {
	
	public static String getFirstName()
	{
		return ("John"+RandomStringUtils.randomAlphabetic(3));
	}
	
	
	public static String getLastName()
	{
		return ("Rodrix"+RandomStringUtils.randomAlphabetic(3));
		
	}
	
	public static String getUserName()
	{
		return ("User"+RandomStringUtils.randomAlphabetic(3));
	}

	public static String getPassword()
	{
		return ("Pass"+RandomStringUtils.randomAlphabetic(3));
	}
	
	public static String getEmail()
	{
		return (RandomStringUtils.randomAlphabetic(6)+"@Gmail.com");
	}
	
	
	
	
	
	
	
	
}
