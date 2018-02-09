package com.beyondskool.servicelocator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.serviceimpl.ActivityServiceImpl;
import com.beyondskool.serviceimpl.AreasServiceImpl;
import com.beyondskool.serviceimpl.BookingServiceImpl;
import com.beyondskool.serviceimpl.CentreServiceImpl;
import com.beyondskool.serviceimpl.CitiesServiceImpl;
import com.beyondskool.serviceimpl.LoginServiceImpl;
import com.beyondskool.serviceimpl.PaymentServiceImpl;
import com.beyondskool.serviceimpl.UsersServiceImpl;


public class ServiceLocator {
	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");
	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");
	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	private Map cache;

	private static ServiceLocator me;

	static {
		try {
			me = new ServiceLocator();
		} catch (ServiceLocatorException se) {
			ERROR_LOGGER.log(Level.ERROR, se.getMessage(), se);
		}
	}

	private ServiceLocator() throws ServiceLocatorException {

		// ic = new InitialContext();
		cache = Collections.synchronizedMap(new HashMap());

	}

	static public ServiceLocator getInstance() {
		DEBUG_LOGGER.log(Level.DEBUG, "ServiceLocator: ServiceLocator method entered and 'me' returned");
		return me;

	}

	public void addService(String name, Object service) {
		cache.put(name, service);
	}

	public Object getService(String name) {
		if (cache.containsKey(name)) {
			return cache.get(name);
		} else {
			addService(name, getObject(name));
			return cache.get(name);
		}
	}

	private Object getObject(String name) {
		Object instance = null;
		if(name.equalsIgnoreCase("authentication")){
			instance = new LoginServiceImpl();
		} else if (name.equalsIgnoreCase("cities")) {
			instance = new CitiesServiceImpl();
		} else if (name.equalsIgnoreCase("areas")) {
			instance = new AreasServiceImpl();
		} else if (name.equalsIgnoreCase("users")) {
			instance = new UsersServiceImpl();
		} else if (name.equalsIgnoreCase("activities")) {
			instance = new ActivityServiceImpl();
		} else if (name.equalsIgnoreCase("centres")) {
			instance = new CentreServiceImpl();
		}else if (name.equalsIgnoreCase("payments")) {
			instance = new PaymentServiceImpl();
		}else if (name.equalsIgnoreCase("bookings")) {
			instance = new BookingServiceImpl();
		}

		DEBUG_LOGGER.log(Level.DEBUG,
				"ServiceLocator: Object instance created");
		return instance;
	}
}