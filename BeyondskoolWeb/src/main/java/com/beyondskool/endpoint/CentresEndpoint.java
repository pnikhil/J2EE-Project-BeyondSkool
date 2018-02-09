package com.beyondskool.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.beyondskool.dataimport.dao.ActivityDAO;
import com.beyondskool.dataimport.dao.AreaDAO;
import com.beyondskool.dataimport.dao.BookingDAO;
import com.beyondskool.dataimport.dao.CentreDAO;

import com.beyondskool.domain.Activity;
import com.beyondskool.domain.Booking;
import com.beyondskool.domain.Centre;

import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.ICentreService;
import com.google.gson.Gson;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/centres")
/**
 * The CitiesEndPoint is a rest service for handling cities data.
 */
public class CentresEndpoint {

	ICentreService usersService = (ICentreService) ServiceLocator.getInstance().getService("centres");

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
	 * @return List<Activity> is areas list that contains city data.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/centreData")
	public List<Centre> getCentreDetails() {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().getCentreData();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/centreDataActive")
	public List<Centre> getCentreDetailsActive() {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().getCentreDataActive();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/summaryCentreDetailsActive")
	public List<Centre> getSummaryCentreDetailsActive() {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().getSummaryCentreDetailsActive();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/activityFilteredCentre")
	public List<Centre> getActivityFilteredCentre(@FormParam("activityId") int activityId) {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().getFilteredCentreResults(activityId);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/filterCentreByAge")
	public List<Centre> filterCentreByAge(@FormParam("age") int age) {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().filterCentreByAge(age);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/centreDataPending")
	public List<Centre> getCentreDetailsPending() {
		List<Centre> getCentreData = new ArrayList<Centre>();
		try {
			getCentreData = new CentreDAO().getCentreDataPending();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCentreData;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/activityData")
	public List<Activity> centreActivityData(@FormParam("centreId") int centreId,
			@FormParam("centreName") String centreName) {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method started");
		List<Activity> activities = null;
		try {
			activities = new CentreDAO().getActivityData(centreId, centreName);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: updateArea method ended");
		return activities;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/completeCentreData")
	public Centre getCompleteCentreDetails(@FormParam("centreName") String centreName) {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method started");
		Centre centre = null;
		try {
			centre = new CentreDAO().getCompleteCentreDetails(centreName);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method ended");
		return centre;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/completeCentreDataFrontEnd")
	public Centre getCompleteCentreDetailsFrontEnd(@FormParam("centreName") String centreName, @FormParam("centreId") int centreId) {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method started");
		Centre centre = null;
		try {
			centre = new CentreDAO().getCompleteCentreDetails(centreName,centreId);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method ended");
		return centre;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/completeCentreProfile")
	public Centre getCompleteCentreProfile(@FormParam("centreEmail") String centreEmail) {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method started");
		Centre centre = null;
		try {
			centre = new CentreDAO().getCompleteCentreProfile(centreEmail);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: getCompleteCentreDetails method ended");
		return centre;
	}
	

	@POST
	@Path("/approveCentre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean approveCentre(@FormParam("centreId") int centreId, @FormParam("centreEmail") String centreEmail) throws DataImportException, AddressException, MessagingException {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: approveCentre method started");
		boolean isSuccess = new CentreDAO().approveCentre(centreId,centreEmail);
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: approveCentre method ended");
		return isSuccess;
	}
	
	
	@POST
	@Path("/updateCentreDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean updateCentreDetails(@FormParam("centreId") int centreId, @FormParam("ownerName") String ownerName, @FormParam("contactNo") String contactNo, @FormParam("description") String description, @FormParam("address") String address/*, @FormParam("imageFiles") String imageFiles*/) throws DataImportException, AddressException, MessagingException {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: updateCentreDetails method started");
		boolean isSuccess = new CentreDAO().updateCentreDetails(centreId,ownerName,contactNo,address,description/*,imageFiles*/);
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: updateCentreDetails method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/updateCentreDetailsByAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean updateCentreDetailsByAdmin(@FormParam("centreId") int centreId, @FormParam("ownerName") String ownerName, @FormParam("contactNo") String contactNo, @FormParam("description") String description, @FormParam("address") String address,@FormParam("city") int city,@FormParam("area") int area,@FormParam("emailId") String emailId) throws DataImportException, AddressException, MessagingException {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: updateCentreDetailsByAdmin method started");
		boolean isSuccess = new CentreDAO().updateCentreDetailsByAdmin(centreId,ownerName,contactNo,address,description,city,area,emailId);
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: updateCentreDetailsByAdmin method ended");
		return isSuccess;
	}

	@Context
	private ServletContext context;
	@Resource
	ServletContext servletContext;

	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)

	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		String json = null;
		Gson gson = new Gson();
		try {
			//File backUpCentre = null;
			String folderName = getFolderName(fileMetaData.getFileName());
			int read = 0;
			//int read2 = 0;
			byte[] bytes = new byte[1024];
			//byte[] bytes2 = new byte[1024];
			String fileName = fileMetaData.getFileName();
			StringTokenizer st = new StringTokenizer(fileName,"~");
			while(st.hasMoreTokens()){
				fileName = st.nextToken();
			}
			//InputStream backupStream = IOUtils.toBufferedInputStream(fileInputStream);
			File dirForCentre = new File(context.getRealPath("uploads") + File.separator + folderName);
			System.out.println(dirForCentre.getPath());
			if (!dirForCentre.exists()) {
				new File(context.getRealPath("uploads") + File.separator + folderName).mkdirs();
			}

			File file = new File(context.getRealPath("uploads") + File.separator + folderName + File.separator,
					fileName);
			OutputStream out = new FileOutputStream(file);
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			fileInputStream.close();

			/*backUpCentre = new File(
					Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName);
			if (!backUpCentre.exists()) {
				new File(Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName)
						.mkdirs();
			}

			File file2 = new File(
					Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName,
					fileName);
			OutputStream out2 = new FileOutputStream(file2);

			while ((read2 = backupStream.read(bytes2)) != -1) {
				out2.write(bytes2, 0, read2);
			}
			out2.flush();
			out2.close();
			backupStream.close();*/

			/*File[] files = new File(
					Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + folderName)
							.listFiles();*/
			
			File[] files = new File(context.getRealPath("uploads") + File.separator + folderName).listFiles();
			
			List<String> results = new ArrayList<String>();
			for (File file3 : files) {
				if (file3.isFile()) {
					results.add(file3.getName());
				}
			}
			String fileAppend = "";
			String[] fileNameArray = new String[results.size()];
			fileNameArray = results.toArray(fileNameArray);
			int i = 0;
			for (String getfileName : fileNameArray) {
				fileAppend += getfileName;
				++i;
				if (i < results.size()) {
					fileAppend += ",";
				}
			}
			json = gson.toJson(fileAppend);
		} catch (IOException e) {
			json = gson.toJson("Error in file upload");
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deleteImage")
	public boolean deleteImage(@FormParam("centreName") String centreName, @FormParam("imageName") String imageName, @FormParam("centreId") int centreId, @FormParam("imageId") int imageId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: deleteImage method started");
		boolean updateToDb = false;
		File imageDelete = new File(context.getRealPath("uploads") + File.separator + centreName + "_" + imageId + File.separator + imageName);
		if (imageDelete.delete()) {
			System.out.println(imageDelete.getName() + " is deleted!");
			File[] files = new File(context.getRealPath("uploads") + File.separator + centreName + "_" + imageId).listFiles();
			
			List<String> results = new ArrayList<String>();
			for (File file3 : files) {
				if (file3.isFile()) {
					results.add(file3.getName());
				}
			}
			String fileAppend = "";
			String[] fileNameArray = new String[results.size()];
			fileNameArray = results.toArray(fileNameArray);
			int i = 0;
			for (String getfileName : fileNameArray) {
				fileAppend += getfileName;
				++i;
				if (i < results.size()) {
					fileAppend += ",";
				}
			}
			updateToDb = new CentreDAO().updateImageDetails(centreId, centreName, fileAppend);
			
		} else {
			System.out.println("Delete operation is failed.");
		}
		INFO_LOGGER.log(Level.INFO, "CentresEndPoint: deleteImage method ended");
		return updateToDb;
	}

	@POST
	@Path("/deleteCentre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteCentre(int centreId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isSuccess = new CentreDAO().deleteCentre(centreId);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isSuccess;
	}	
	
	@POST
	@Path("/deleteCentreActivity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteCentreActivity(int centreActivityId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isSuccess = new CentreDAO().deleteCentreActivity(centreActivityId);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/updateImages")
	public boolean updateCentreImages(@FormParam("centreId") int centreId, @FormParam("imageNames") String imageNames) {
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method started");
		boolean isSuccess = false;
		try {
			isSuccess = new CentreDAO().updateCentreImages(centreId,imageNames);
		} catch (DataImportException e) {
			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method ends");
		return isSuccess;
	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createCentre")
	public int createCentre(String centreData) {
		String ownerName, emailId, password, centreName, description, contactNo, fileNames, address, status = null;
		int city, area, imageId = 0;

		int centreId = 0;
		try {
			JSONObject jsonObj = new JSONObject(centreData);
			ownerName = (String) jsonObj.get("ownerName");
			emailId = (String) jsonObj.get("emailId");
			password = (String) jsonObj.get("password");
			centreName = (String) jsonObj.get("centreName");
			description = (String) jsonObj.get("description");
			contactNo = (String) jsonObj.get("contactNo");
			fileNames = (String) jsonObj.get("fileNames");
			address = (String) jsonObj.get("address");
			status = (String) jsonObj.get("status");
			city = (Integer) jsonObj.get("city");
			area = (Integer) jsonObj.get("area");
			status = (String) jsonObj.get("status");
			imageId = (Integer) jsonObj.get("imageId");
			centreId = new CentreDAO().createCentre(ownerName, emailId, password, centreName, description, contactNo,
					fileNames, address, status, city, area, status,imageId);
		} catch (DataImportException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return centreId;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createCentreForCurrentUser")
	public int createCentreForCurrentUser(String centreData) {
		String emailId, centreName, description, contactNo, fileNames, address, status = null;
		int city, area = 0;

		int centreId = 0;
		try {
			JSONObject jsonObj = new JSONObject(centreData);
			emailId = (String) jsonObj.get("centreEmail");
			centreName = (String) jsonObj.get("centreName");
			description = (String) jsonObj.get("description");
			fileNames = (String) jsonObj.get("fileNames");
			address = (String) jsonObj.get("address");
			city = (Integer) jsonObj.get("city");
			area = (Integer) jsonObj.get("area");
			centreId = new CentreDAO().createCentreForCurrentUser(emailId, centreName, description,
					fileNames, address, city, area);
		} catch (DataImportException e) {
			e.printStackTrace();
		}
		return centreId;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addActivities")
	public boolean addActivities(Object[] activitesObj) {
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: extractSubmission method Started");

		boolean isSuccess = false;
		JSONArray activitiesArray = new JSONArray(activitesObj);
		List<Activity> activityList = new ArrayList<Activity>();
		for (int i = 0; i < activitiesArray.length(); i++) {
			Activity activity = new Activity();
			activity.setId(activitiesArray.getJSONObject(i).getInt("activityId"));
			activity.setCentreId(activitiesArray.getJSONObject(i).getInt("centreId"));
			activity.setAmount(activitiesArray.getJSONObject(i).getInt("amount"));
			activity.setTotalSlots(activitiesArray.getJSONObject(i).getInt("totalSlots"));
			activity.setDay(activitiesArray.getJSONObject(i).getString("day"));
			activity.setFromAge(activitiesArray.getJSONObject(i).getInt("fromAge"));
			activity.setToAge(activitiesArray.getJSONObject(i).getInt("toAge"));
			activity.setInTime(activitiesArray.getJSONObject(i).getString("fromTime"));
			activity.setOutTime(activitiesArray.getJSONObject(i).getString("toTime"));
			activity.setDuration(activitiesArray.getJSONObject(i).getString("duration"));
			activity.setClassDescription(activitiesArray.getJSONObject(i).getString("classDescription"));
			activity.setStartDate(activitiesArray.getJSONObject(i).getString("startDateString"));
			activity.setEndDate(activitiesArray.getJSONObject(i).getString("endDateString"));
			activityList.add(activity);
		}
		try {
			isSuccess = new ActivityDAO().addActivityForCentres(activityList);
		} catch (DataImportException e) {
			e.printStackTrace();
		}

		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: extractSubmission method ended");
		return isSuccess;
	}

	public String getFolderName(String fileName) {
		String folderName = null;
		StringTokenizer st = new StringTokenizer(fileName, "~");
		while (st.hasMoreTokens()) {
			folderName = st.nextToken();
			break;
		}
		return folderName;
	}

	/*public static void main(String args[]) {
		List<String> results = new ArrayList<String>();

		File[] files = new File(
				Dir_ResourceBundle.getString("CENTRE_IMAGES_UPLOAD_LOCATION").toString() + "\\" + "12312").listFiles();
		// If this pathname does not denote a directory, then listFiles()
		// returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		String[] fileNameArray = new String[results.size()];
		fileNameArray = results.toArray(fileNameArray);
		int i = 0;
		for (String fileName : fileNameArray) {
			System.out.print(fileName);
			++i;
			if (i < results.size()) {
				System.out.print(",");
			}
		}
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/bookingsListForCentre")
	public List<Booking> getBookingsListForCentre(@FormParam("centreEmail") String centreEmail) {
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method started");
		List<Booking> getBookingsList = new ArrayList<Booking>();
		try {
			getBookingsList = new BookingDAO().getBookingsListForCentre(centreEmail);
		} catch (DataImportException e) {
			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "BookingsEndPoint: getBookingsList method ends");
		return getBookingsList;
	}
}