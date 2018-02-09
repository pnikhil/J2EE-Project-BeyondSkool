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
import com.beyondskool.dataimport.dao.CentreDAO;
import com.beyondskool.dataimport.dao.CityDAO;
import com.beyondskool.dataimport.dao.PaymentDAO;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.Centre;
import com.beyondskool.domain.City;
import com.beyondskool.domain.Payment;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.IAreasService;
import com.beyondskool.services.ICitiesService;
import com.beyondskool.services.IPaymentService;
import com.beyondskool.util.PaymentUtil;
import com.razorpay.RazorpayException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/payments")
/**
 * The PaymentsEndPoint is a rest service for handling
 * payments data.
 */
public class PaymentsEndPoint{

	IPaymentService areasService = (IPaymentService) ServiceLocator.getInstance()
			.getService("payments");

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
	 * getPaymentsList is used fetch payment list.
	 * 
	 * @return List<Payments> is payments list that contains payment
	 *         data.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/paymentsList")
	public List<Payment> getPaymentsList() {
		INFO_LOGGER.log(Level.INFO, "PaymentsEndPoint: getPaymentsList method started");
		List<Payment> getPaymentsList = new ArrayList<Payment>();
		try {
			getPaymentsList = new PaymentDAO().getPaymentsList();
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
	@Path("/capturePayment")
	public boolean capturePayment(@FormParam("paymentId") String paymentId) throws RazorpayException {
		boolean isSuccess = false;
		List<Payment> paymentInfo = new ArrayList<Payment>();
		try {
			paymentInfo = new PaymentUtil().getPaymentDetails(paymentId);
			isSuccess = new PaymentDAO().capturePayment(paymentInfo);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	
}