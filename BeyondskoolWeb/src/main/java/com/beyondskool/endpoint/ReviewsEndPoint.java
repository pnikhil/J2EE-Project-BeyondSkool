package com.beyondskool.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.dataimport.dao.CentreDAO;
import com.beyondskool.dataimport.dao.PaymentDAO;
import com.beyondskool.dataimport.dao.ReviewsDAO;
import com.beyondskool.domain.Payment;
import com.beyondskool.domain.Review;
import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.exception.GenericExceptionMapper;


@Path("/reviews")
/**
 * The PaymentsEndPoint is a rest service for handling
 * payments data.
 */
public class ReviewsEndPoint{

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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/centreReviews")
	public List<Review> getCentreReviews(@FormParam("centreName") String centreName) {
		INFO_LOGGER.log(Level.INFO, "ReviewsEndPoint: getCentreReviews method started");
		List<Review> getCentreReviews = new ArrayList<Review>();
		try {
			getCentreReviews = new ReviewsDAO().getCentreReviews(centreName);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "ReviewsEndPoint: getCentreReviews method ends");
		return getCentreReviews;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/addReview")
	public int addReview(@FormParam("parentEmail") String parentEmail, @FormParam("centreId") int centreId, @FormParam("review") String review, @FormParam("rating") int rating,@FormParam("currentTime") String currentTime) {
		INFO_LOGGER.log(Level.INFO, "ReviewsEndPoint: getCentreReviews method started");
		int isSuccess = 0;
		try {
			isSuccess = new ReviewsDAO().addReview(parentEmail,centreId,review,rating,currentTime);
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "ReviewsEndPoint: getCentreReviews method ends");
		return isSuccess;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/reviewsList")
	public List<Review> getReviews() {
		INFO_LOGGER.log(Level.INFO, "PaymentsEndPoint: getPaymentsList method started");
		List<Review> getReviews = new ArrayList<Review>();
		try {
			getReviews = new ReviewsDAO().getReviews();
		} catch (DataImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: getPaymentsList method ends");
		return getReviews;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/deleteReview")
	public boolean deleteReview(@FormParam("reviewId") int reviewId) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method started");
		boolean isSuccess = new ReviewsDAO().deleteReview(reviewId);
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: deleteArea method ended");
		return isSuccess;
	}	
	
}