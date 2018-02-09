package com.beyondskool.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.domain.City;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.services.IAreasService;
import com.beyondskool.services.ICitiesService;

public class AreasServiceImpl implements IAreasService {
	

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");


}
