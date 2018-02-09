package com.beyondskool.endpoint;

import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.exception.ApplicationExceptionMapper;
import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.util.MailUtil;


@Path("/contact")
/**
 * The CitiesEndPoint is a rest service for handling
 * cities data.
 */
public class ContactEndPoint{
	
	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	private GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
	private ApplicationExceptionMapper applicationExceptionMapper = new ApplicationExceptionMapper();
	/*
	 * @Inject IAdminService adminService;
	 */

	/**
	 * getAreasList is used fetch cities list.
	 * 
	 * @return List<Submission> is areas list that contains city
	 *         data.
	 * @throws MessagingException 
	 * @throws AddressException 
	 */

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/contactForm")
	public boolean contact(@FormParam("name") String name, @FormParam("email") String email, @FormParam("subject") String subject, @FormParam("message") String message){
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method started");
		boolean isSuccess = true;
		MailUtil mail = new MailUtil();
		try {
			isSuccess = mail.generateAndSendEmail(name,email,subject,message);
		} catch (MessagingException e) {			
			e.printStackTrace();
			return false;
		}
		INFO_LOGGER.log(Level.INFO, "AreasEndPoint: addArea method ended");
		return isSuccess;
	}	
}