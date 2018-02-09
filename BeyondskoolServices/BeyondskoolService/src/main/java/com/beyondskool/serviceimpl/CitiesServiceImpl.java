package com.beyondskool.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.domain.City;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.services.ICitiesService;

public class CitiesServiceImpl implements ICitiesService {
	

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	@Override
	public List<City> getCitiesList() {
		INFO_LOGGER.log(Level.INFO, "CitiesServiceImpl: getCitiesList method started");	
		List<City> citiesList = new ArrayList<City>();
		try {
			citiesList = new CityDAO().getCitiesList();
			INFO_LOGGER.log(Level.INFO, "TaskSubmissionServiceImpl: Submission retrieved from submission DAO");
		} catch (DataImportException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"CitiesServiceImpl : getCitiesList() - Error " + e);
		}
		INFO_LOGGER.log(Level.INFO, "CitiesServiceImpl: getCitiesList method ended");
		return citiesList;
	}

}
