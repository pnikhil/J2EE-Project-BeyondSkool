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
import org.json.JSONArray;

import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.domain.City;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.ICitiesService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/cities")
/**
 * The CitiesEndPoint is a rest service for handling
 * cities data.
 */
public class CitiesEndPoint{

	ICitiesService citiesService = (ICitiesService) ServiceLocator.getInstance()
			.getService("cities");

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
	 * getCitiesList is used fetch cities list.
	 * 
	 * @return List<Submission> is cities list that contains city
	 *         data.
	 */

	@GET
	/*@RolesAllowed("ADMIN")*/
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/citiesList")
	public List<City> getCitiesList(/*@Context SecurityContext sc*/) {
		/*System.out.println(sc.getUserPrincipal().getName());
		System.out.println(sc.isUserInRole("ADMIN"));		*/
		INFO_LOGGER.log(Level.INFO, "CitiesEndPoint: getCitiesList method started");
		List<City> getCitiesList = new ArrayList<City>();
		getCitiesList = citiesService.getCitiesList();
		INFO_LOGGER.log(Level.INFO, "CitiesEndPoint: getCitiesList method ends");
		return getCitiesList;
	}
	
	@POST
	@Path("/createCity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateStatus(Object cityName) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method started");
		boolean isSuccess = new CityDAO().addCity(cityName.toString());
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method ended");
		return isSuccess;
	}
	
	@POST
	@Path("/deleteCity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean updateStatus(@FormParam("cityId") int cityId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method started");
		boolean isSuccess = new CityDAO().deleteCity(cityId);
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method ended");
		return isSuccess;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/updateCity")
	public boolean updateCity(@FormParam("cityName") String cityName, @FormParam("cityId") int cityId){
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method started");
		boolean isSuccess = false;
		try {
			isSuccess = new CityDAO().updateCity(cityName,cityId);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "SubmissionEndpoint: changeStatusToOpen method ended");
		return isSuccess;
	}
	
}