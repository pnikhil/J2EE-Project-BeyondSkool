package com.beyondskool.endpoint;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.dataimport.dao.BookingDAO;
import com.beyondskool.dataimport.dao.CentreDAO;
import com.beyondskool.domain.Booking;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.IBookingService;
import com.beyondskool.services.IPaymentService;


@Path("/bookings")
/**
 * The PaymentsEndPoint is a rest service for handling
 * payments data.
 */
public class BookingsEndPoint{

	IBookingService areasService = (IBookingService) ServiceLocator.getInstance()
			.getService("bookings");

	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	private GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
	private ApplicationExceptionMapper applicationExceptionMapper = new ApplicationExceptionMapper();

	@Context
	ServletContext servletContext;

	/*
	 * @Inject IAdminService adminService;
	 */

	/**
	 * getBookingsList is used fetch booking list.
	 * 
	 * @return List<Bookings> is bookings list that contains booking
	 *         data.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/bookingsList")
	public List<Booking> getBookingsList() {
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method started");
		List<Booking> getBookingsList = new ArrayList<Booking>();
		try {
			getBookingsList = new BookingDAO().getBookingsList();
		} catch (DataImportException e) {
			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method ends");
		return getBookingsList;
	}
	
	@POST
	@Path("/changeBookingStatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean changeBookingStatus(@FormParam("choice") String choice, @FormParam("bookingId") int bookingId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isSuccess = new BookingDAO().changeBookingStatus(choice,bookingId);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/bookAClass")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int bookAClass(@FormParam("bookingSlot") String bookingSlot, @FormParam("centreActivityId") int centreActivityId, @FormParam("cost") int bookingCost, @FormParam("userEmail") String userEmail) throws DataImportException, MessagingException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		int bookingStatus = new BookingDAO().bookAClass(bookingSlot,centreActivityId,bookingCost,userEmail);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return bookingStatus;
	}
	
}