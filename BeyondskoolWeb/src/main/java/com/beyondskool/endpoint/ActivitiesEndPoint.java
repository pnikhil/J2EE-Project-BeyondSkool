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
import com.beyondskool.dataimport.dao.UserDAO;
import com.beyondskool.domain.Activity;
import com.beyondskool.domain.ActivityCentre;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.Centre;
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


@Path("/activities")
/**
 * The CitiesEndPoint is a rest service for handling
 * cities data.
 */
public class ActivitiesEndPoint{

	IActivityService usersService = (IActivityService) ServiceLocator.getInstance()
			.getService("activities");

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/activityDataForUserCentre")
	public List<Activity> getActivityDetailsForDropDown() {		
		List<Activity> getActivityData = new ArrayList<Activity>();
		try {
			getActivityData = new ActivityDAO().getActivityDataForDropDown();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return getActivityData;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/upcomingClassData")
	public List<ActivityCentre> getUpcomingClassData() {		
		List<ActivityCentre> upcomingClassData = new ArrayList<ActivityCentre>();
		try {
			upcomingClassData = new ActivityDAO().getUpcomingClassData();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return upcomingClassData;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/updateActivity")
	public boolean updateActivity(@FormParam("centreActivityId") int centreActivityId, @FormParam("activityId") int activityId, @FormParam("inTime") String inTime, @FormParam("outTime") String outTime, @FormParam("day") String day, @FormParam("fromAge") int fromAge, @FormParam("toAge") int toAge, @FormParam("totalSlots") int totalSlots, @FormParam("duration") String duration, @FormParam("classDescription") String classDescription, @FormParam("amount") int amount, @FormParam("startDate") String startDate, @FormParam("endDate") String endDate) {
		boolean isSuccess = false;
		try {
			isSuccess = new ActivityDAO().updateActivity(centreActivityId,activityId,inTime,outTime,day,fromAge,toAge,totalSlots,duration,classDescription,amount,startDate,endDate);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/filterUpcomingByAge")
	public List<ActivityCentre> filterUpcomingByAge(@FormParam("age") int age) {
		List<ActivityCentre> getUpcomingByAge = new ArrayList<ActivityCentre>();
		try {
			getUpcomingByAge = new ActivityDAO().getUpcomingByAge(age);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getUpcomingByAge;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/filterUpcomingByActivity")
	public List<ActivityCentre> filterUpcomingByActivity(@FormParam("activityId") int activityId) {
		List<ActivityCentre> filterUpcomingByActivity = new ArrayList<ActivityCentre>();
		try {
			filterUpcomingByActivity = new ActivityDAO().getFilteredCentreResults(activityId);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filterUpcomingByActivity;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/filterUpcomingByActivityAndAge")
	public List<ActivityCentre> filterUpcomingByActivityAndAge(@FormParam("activityId") int activityId,@FormParam("age") int age) {
		List<ActivityCentre> filterUpcomingByActivityAndAge = new ArrayList<ActivityCentre>();
		try {
			filterUpcomingByActivityAndAge = new ActivityDAO().filterUpcomingByActivityAndAge(activityId,age);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filterUpcomingByActivityAndAge;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/filterActivityByHomeFilter")
	public List<ActivityCentre> filterActivityByHomeFilter(@FormParam("activity") int activityId,@FormParam("age") int age, @FormParam("area") int areaId) {
		List<ActivityCentre> filterUpcomingByActivityAndAge = new ArrayList<ActivityCentre>();
		try {
			filterUpcomingByActivityAndAge = new ActivityDAO().filterActivityByHomeFilter(activityId,age,areaId);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filterUpcomingByActivityAndAge;
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
	@Path("/deleteActivity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean deleteActivity(@FormParam("activityId") int activityId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "ActivitiesEndPoint: deleteActivity method started");
		boolean isSuccess = new ActivityDAO().deleteActivity(activityId);
		INFO_LOGGER.log(Level.INFO, "ActivitiesEndPoint: deleteActivity method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/updateActivityData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean updateActivityData(@FormParam("activityName") String activityName, @FormParam("imagePath") String imagePath, @FormParam("activityId") int activityId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "ActivitiesEndPoint: deleteActivity method started");
		boolean isSuccess = new ActivityDAO().updateActivity(activityName,imagePath,activityId);
		INFO_LOGGER.log(Level.INFO, "ActivitiesEndPoint: deleteActivity method ended");
		return isSuccess;
	}
	
	@Context
	private ServletContext context;
	@Resource
	ServletContext servletContext;
	
	@POST
	@Path("/fileUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)

	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		String json = null;
		Gson gson = new Gson();
		try {	
			int read = 0;
			//int read2 = 0;
			byte[] bytes = new byte[1024];
			//byte[] bytes2 = new byte[1024];
			String fileName = fileMetaData.getFileName();
			//InputStream backupStream = IOUtils.toBufferedInputStream(fileInputStream);
			File file = new File(context.getRealPath("uploads") + File.separator,
					fileName);
			OutputStream out = new FileOutputStream(file);
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			fileInputStream.close();

			//File file2 = new File(Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString(),fileName);
			//OutputStream out2 = new FileOutputStream(file2);

			/*while ((read2 = backupStream.read(bytes2)) != -1) {
				out2.write(bytes2, 0, read2);
			}
			out2.flush();
			out2.close();
			backupStream.close();*/
			json = gson.toJson(fileName);
		} catch (IOException e) {
			json = gson.toJson("Error in file upload");
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/addActivity")
	public boolean addArea(@FormParam("activityName") String activityName, /*@FormParam("description") String description, */@FormParam("fileName") String fileName){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		boolean isSuccess = false;
		try {
			isSuccess = new ActivityDAO().addActivity(activityName/*,description*/,fileName);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return isSuccess;
	}
}