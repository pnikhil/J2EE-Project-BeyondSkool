package com.beyondskool.endpoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.security.RolesAllowed;
import javax.crypto.BadPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.beyondskool.dataimport.dao.AreaDAO;
import com.beyondskool.dataimport.dao.BookingDAO;
import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.dataimport.dao.PaymentDAO;
import com.beyondskool.dataimport.dao.UserDAO;
import com.beyondskool.dataimport.dao.WalletDAO;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.Booking;
import com.beyondskool.domain.Centre;
import com.beyondskool.domain.City;
import com.beyondskool.domain.Payment;
import com.beyondskool.domain.User;
import com.beyondskool.domain.Wallet;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.IAreasService;
import com.beyondskool.services.ICitiesService;
import com.beyondskool.services.IUsersService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/users")
/**
 * The CitiesEndPoint is a rest service for handling
 * cities data.
 */
public class UsersEndPoint{

	IUsersService usersService = (IUsersService) ServiceLocator.getInstance()
			.getService("users");

	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	private GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
	private ApplicationExceptionMapper applicationExceptionMapper = new ApplicationExceptionMapper();

	@Context
	ServletContext servletContext;

	/*
	 * @Inject IUsersService IUsersService;
	 */

	/**
	 * getAreasList is used fetch cities list.
	 * 
	 * @return List<Submission> is areas list that contains city
	 *         data.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userDetailList")
	public List<User> getUserDetailsList(/*@Context SecurityContext sc*/) {
		/*System.out.println(sc.getUserPrincipal().getName());
		System.out.println(sc.isUserInRole("ADMIN"));*/		
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getAreasList method started");
		List<User> getUsersList = new ArrayList<User>();
		try {
			getUsersList = new UserDAO().getUserDetailsList();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getAreasList method ends");
		return getUsersList;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deleteUser")
	public boolean deleteUser(@FormParam("userId") int userId/*, @FormParam("beyondskoolId") String beyondskoolId*/){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new UserDAO().deleteUser(userId/*,beyondskoolId*/);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method ended");
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/paymentsListForParent")
	public List<Payment> getPaymentsListForParent(@FormParam("parentEmail") String parentEmail) {
		INFO_LOGGER.log(Level.INFO, "PaymentsEndPoint: getPaymentsList method started");
		List<Payment> getPaymentsList = new ArrayList<Payment>();
		try {
			getPaymentsList = new PaymentDAO().getPaymentsListForParent(parentEmail);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getPaymentsList method ends");
		return getPaymentsList;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/bookingsListForParent")
	public List<Booking> getBookingsListForParent(@FormParam("parentEmail") String parentEmail) {
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method started");
		List<Booking> getBookingsList = new ArrayList<Booking>();
		try {
			getBookingsList = new BookingDAO().getBookingsListForParent(parentEmail);
		} catch (DataImportException e) {
			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method ends");
		return getBookingsList;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/retrieveWalletInfoForParent")
	public Wallet retrieveWalletInfoForParent(@FormParam("parentEmail") String parentEmail) {
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method started");
		Wallet getWalletInfo = null;
		try {
			getWalletInfo = new WalletDAO().retrieveWalletInfoForParent(parentEmail);
		} catch (DataImportException e) {
			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method ends");
		return getWalletInfo;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveUser")
	public boolean saveUser(String userData) throws AddressException, NumberFormatException, MessagingException {		
		/*String beyondSkoolId = null;*/
		String parentName  = null;
		String childName = null;
		int age = 0;
		String gender  = null;
		String email  = null;
		String password  = null;
		String fatherMobile  = null;
		String motherMobile  = null;
		String address = null;
		String standard = null;
		String school  = null;
		String preference  = null;
		String activity_list  = null;
		String timings  = null;
		String city = null, area = null;
		
		boolean isSuccess = false;  
		try {    			
    		JSONObject jsonObj = new JSONObject(userData);    		
    		/*beyondSkoolId = (String)jsonObj.get("beyondskoolid");*/
    		parentName = (String)jsonObj.get("parentName");
    		childName = (String)jsonObj.get("childName");
    		age = (Integer)jsonObj.get("age");
    		standard = (String)jsonObj.get("standard");
    		gender = (String)jsonObj.get("gender");
    		email = (String)jsonObj.get("email");
    		password = (String)jsonObj.get("password");
    		fatherMobile = (String)jsonObj.get("fatherMobile");
    		motherMobile = (String)jsonObj.get("motherMobile");
    		address = (String)jsonObj.get("address");
    		city = (String)jsonObj.get("city");
    		area = (String)jsonObj.get("area");
    		school = (String)jsonObj.get("school");
    		preference = (String)jsonObj.get("preference");
    		activity_list = (String)jsonObj.get("activity_list");
    		timings = (String)jsonObj.get("timings");    		
			isSuccess = new UserDAO().saveUser(/*beyondSkoolId,*/parentName,childName,age,
					Integer.valueOf(standard),gender,email,password,fatherMobile,motherMobile,address,school,preference,city,area,activity_list,timings);
        } catch (DataImportException e) {
			
			e.printStackTrace();
		}	
		
		return isSuccess;
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateParent")
	public boolean updateParent(String userData) throws AddressException, NumberFormatException, MessagingException, BadPaddingException {		
		String parentName  = null;
		String childName = null;
		int age = 0;
		int userId = 0;
		String gender  = null;
		String email  = null;
		String fatherMobile  = null;
		String motherMobile  = null;
		String address = null;
		int standard = 0;
		String school  = null;
		String preference  = null;
		String activity_list  = null;
		String timings  = null;
		String city = null, area = null;
		
		boolean isSuccess = false;  
		try {    			
    		JSONObject jsonObj = new JSONObject(userData);
    		if(jsonObj.get("userId")!=null)
    		userId = (Integer)jsonObj.get("userId");
    		if(jsonObj.get("parentName")!=null)
    		parentName = (String)jsonObj.get("parentName");
    		if(jsonObj.get("childName")!=null)
    		childName = (String)jsonObj.get("childName");
    		if(jsonObj.get("age")!=null)
    		age = (Integer)jsonObj.get("age");
    		if(jsonObj.get("standard")!=null)
    		standard = (Integer)jsonObj.get("standard");
    		if(jsonObj.get("gender")!=null)
    		gender = (String)jsonObj.get("gender");
    		if(jsonObj.get("email")!=null)
    		email = (String)jsonObj.get("email");
    		if(jsonObj.get("fatherMobile")!=null)
    		fatherMobile = (String)jsonObj.get("fatherMobile");
    		if(jsonObj.get("motherMobile")!=null)
    		motherMobile = (String)jsonObj.get("motherMobile");
    		if(jsonObj.get("address")!=null)
    		address = (String)jsonObj.get("address");
    		if(jsonObj.getString("city")!=null)
    		city = (String)jsonObj.get("city");
    		if((String)jsonObj.get("area")!=null)
    		area = (String)jsonObj.get("area");
    		if(jsonObj.get("school")!=null)
    		school = (String)jsonObj.get("school");
    		if((String)jsonObj.get("preference")!=null)
    		preference = (String)jsonObj.get("preference");
    		if((String)jsonObj.get("activity_list")!=null)
    		activity_list = (String)jsonObj.get("activity_list");
    		if((String)jsonObj.get("timings")!=null)
    		timings = (String)jsonObj.get("timings");    		
			isSuccess = new UserDAO().updateParent(userId,parentName,childName,age,
					standard,gender,email,fatherMobile,motherMobile,address,school,preference,city,area,activity_list,timings);
        } catch (DataImportException e) {
			
			e.printStackTrace();
		}	
		
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/completeParentProfile")
	public User getCompleteParentProfile(@FormParam("parentEmail") String parentEmail) {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method started");
		User user = null;
		try {
			user = new UserDAO().getCompleteParentProfile(parentEmail);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method ended");
		return user;
	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/addArea")
	public boolean addArea(@FormParam("cityId") int cityId, @FormParam("areaName") String areaName){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new AreaDAO().addArea(cityId,areaName);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/deleteArea")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteArea(int areaId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isSuccess = new AreaDAO().deleteArea(areaId);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/updateArea")
	public boolean updateArea(@FormParam("cityId") int cityId, @FormParam("areaId") int areaId,@FormParam("areaName") String areaName){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new AreaDAO().updateArea(cityId,areaId,areaName);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method ended");
		return isSuccess;
	}*/
}