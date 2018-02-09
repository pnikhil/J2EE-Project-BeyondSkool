package com.beyondskool.serviceimpl;

import com.beyondskool.dataimport.dao.*;

import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The TaskSubmissionServiceImpl implements a logic that fetches submission data
 * via DAO layer.
 *
 * @author 269877
 * @version 1.0
 * @since 2017-01-19
 */
public class LoginServiceImpl implements com.beyondskool.services.ILoginService {

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	// method to validate login credentials
	@Override
	public int validateLogin(String userName, String password, String userRole) {
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method started");
		int isLogin = 0;
		try {
			isLogin = new LoginDAO().login(userName, password, userRole);
		} catch (com.beyondskool.exception.DataImportException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"TaskSubmissionServiceImpl : getSubmissionDetails() - Error parsing date" + e);
		}
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method ended");

		return isLogin;
	}

	public int validateCentreLogin(String username, String password, String userRole) {
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method started");
		int isLogin = 0;
		try {
			isLogin = new LoginDAO().centreLogin(username, password, userRole);
		} catch (com.beyondskool.exception.DataImportException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"TaskSubmissionServiceImpl : getSubmissionDetails() - Error parsing date" + e);
		}
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method ended");

		return isLogin;
	}

	public int validateParentLogin(String username, String password, String userRole) {
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method started");

		int isLogin = 0;
		try {
			isLogin = new LoginDAO().parentLogin(username, password, userRole);
		} catch (com.beyondskool.exception.DataImportException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"TaskSubmissionServiceImpl : getSubmissionDetails() - Error parsing date" + e);
		}
		INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: getSubmissionDetails method ended");
		return isLogin;
	}
}
