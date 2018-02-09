package com.beyondskool.services;

import org.apache.log4j.Logger;

import com.beyondskool.exception.DataImportException;



public interface ILoginService {
	
	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	
	public int validateLogin(String userName, String password, String userRole);

	public int validateCentreLogin(String username, String password,
			String userRole);
	
	public int validateParentLogin(String username, String password,
			String userRole);

}

