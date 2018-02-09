package com.beyondskool.dataimport.dao;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.dataimport.ExceptionConstants;
import com.beyondskool.exception.DataImportException;

public class BaseDAO {

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	protected Properties prop;

	public BaseDAO() throws DataImportException {
		INFO_LOGGER.log(Level.INFO, " BaseDao: BaseDao method started");

		try {
			// Loading Query.xml containing SQL Queries , so that subclasses can
			// use it readily
			prop = new Properties();
			prop.loadFromXML(this.getClass().getClassLoader().getResourceAsStream("query.xml"));
		} catch (Exception exception) {
			ERROR_LOGGER.log(Level.ERROR, "BaseDao : - Loading Query xml", exception);
			throw new DataImportException(ExceptionConstants.EXCEPTION_CODE, exception);
		}

		INFO_LOGGER.log(Level.INFO, " BaseDao: BaseDao method ended");

	}

}
