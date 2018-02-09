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

import com.beyondskool.dataimport.dao.AreaDAO;
import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.City;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.IAreasService;
import com.beyondskool.services.ICitiesService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/areas")
/**
 * The CitiesEndPoint is a rest service for handling
 * cities data.
 */
public class AreasEndPoint{

	IAreasService areasService = (IAreasService) ServiceLocator.getInstance()
			.getService("areas");

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
	 * getAreasList is used fetch cities list.
	 * 
	 * @return List<Submission> is areas list that contains city
	 *         data.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/areasList")
	public List<Area> getAreasList(/*@Context SecurityContext sc*/) {
		/*System.out.println(sc.getUserPrincipal().getName());
		System.out.println(sc.isUserInRole("ADMIN"));*/		
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getAreasList method started");
		List<Area> getAreasList = new ArrayList<Area>();
		try {
			getAreasList = new AreaDAO().getAreasList();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getAreasList method ends");
		return getAreasList;
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/getAreasforCity")
	public  List<Area> retrieveAreaNamesForCity(@FormParam("cityId") int cityId){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		List<Area> areas = new ArrayList<Area>();
		
		try {
			areas = new AreaDAO().getAreasForCity(cityId);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return areas;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/getAreasforCityName")
	public  List<Area> retrieveAreaNamesForCityName(@FormParam("cityName") String cityName){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		List<Area> areas = new ArrayList<Area>();
		
		try {
			areas = new AreaDAO().getAreasForCityName(cityName);
		} catch (DataImportException e) {			
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return areas;
	}
	
	@POST
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
	}
}