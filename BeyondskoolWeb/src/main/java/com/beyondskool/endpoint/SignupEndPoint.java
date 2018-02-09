package com.beyondskool.endpoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.beyondskool.dataimport.dao.ActivityDAO;
import com.beyondskool.dataimport.dao.AreaDAO;
import com.beyondskool.dataimport.dao.CentreDAO;
import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.dataimport.dao.SignupDAO;
import com.beyondskool.dataimport.dao.UserDAO;
import com.beyondskool.domain.Activity;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.City;
import com.beyondskool.domain.User;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.IActivityService;
import com.beyondskool.services.IAreasService;
import com.beyondskool.services.ICitiesService;
import com.beyondskool.services.IUsersService;
import com.google.gson.Gson;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/signup")
/**
 * The SignupEndPoint is a rest service for handling
 * signup's.
 */
public class SignupEndPoint{

/*	IActivityService usersService = (IActivityService) ServiceLocator.getInstance()
			.getService("activities");
*/
	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	private GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
	private ApplicationExceptionMapper applicationExceptionMapper = new ApplicationExceptionMapper();

	/*
	 * @Inject IUsersService IUsersService;
	 */

	/**
	 * getActivityDetails is used fetch activities list.
	 * 
	 * @return List<Activity> is areas list that contains city
	 *         data.
	 */	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/centreRegister")
	public boolean centreRegister(@FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password, @FormParam("contact") String contact, @FormParam("imageDirId") int imageDirId){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new SignupDAO().centreRegister(name,email,password,contact,imageDirId);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/userRegister")
	public boolean userRegister(@FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password, @FormParam("contact") String contact, @FormParam("childName") String childName, @FormParam("childAge") int childAge, @FormParam("childSchool") String childSchool) throws AddressException, MessagingException{
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new SignupDAO().userRegister(name,email,password,contact,childName,childAge,childSchool);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/checkCentreAccountExists")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean checkCentreAccountExists(String email) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isExists =  new SignupDAO().centreEmailExists(email);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isExists;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/activityData")
	public List<Activity> getActivityDetails() {		
		List<Activity> getActivityData = new ArrayList<Activity>();
		try {
			getActivityData = new ActivityDAO().getActivityData();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return getActivityData;
	}
	
	@POST
	@Path("/checkParentAccountExists")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean checkUserAccountExists(String email) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isExists =  new SignupDAO().centreParentEmailExists(email);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isExists;
	}
}