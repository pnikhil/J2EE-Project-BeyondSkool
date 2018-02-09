package com.beyondskool.endpoint;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beyondskool.exception.GenericExceptionMapper;
import com.beyondskool.servicelocator.ServiceLocator;
import com.beyondskool.services.ILoginService;
import com.beyondskool.util.TokenUtil;

@Path("/login")

public class LoginEndPoint {
	
	ILoginService authService = (ILoginService) ServiceLocator.getInstance()
			.getService("authentication");
	private GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/authenticate")
	public Map<String,Integer> authenticate(@FormParam("username") String username, @FormParam("password") String password,  @FormParam("role") String userRole){
		Map<String,Integer> responseMap = null;
		Response response = null;
		int loginStatus = 0;
		String token = "";
		loginStatus = this.authService.validateLogin(username, password, userRole);
		if(loginStatus == 1) {
			try
			{
				token = TokenUtil.createToken(username, userRole);
				
			} catch (Exception e) {
				response = this.genericExceptionMapper.toResponse(e);
			}			
		}
		response = (response!=null)? response:Response.ok(token).build();
		if(!token.equals("") && loginStatus == 1){
		responseMap = new HashMap<String,Integer>();
		responseMap.put(String.valueOf(response.getEntity()), Integer.valueOf(response.getStatus()));
		}
		else if(loginStatus == 0){
			responseMap = new HashMap<String,Integer>();
			responseMap.put("Account Locked", 0);
		}
		return responseMap;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/centreAuthenticate")
	public Map<String,Integer> centreAuthenticate(@FormParam("username") String username, @FormParam("password") String password,  @FormParam("role") String userRole){
		Map<String,Integer> responseMap = null;
		Response response = null;
		int loginStatus = 0;
		String token = "";
		loginStatus = this.authService.validateCentreLogin(username, password, userRole);
		if(loginStatus == 1) {
			try
			{
				token = TokenUtil.createToken(username, userRole);
				
			} catch (Exception e) {
				response = this.genericExceptionMapper.toResponse(e);
			}			
		}
		response = (response!=null)? response:Response.ok(token).build();
		if(!token.equals("") && loginStatus == 1){
		responseMap = new HashMap<String,Integer>();
		responseMap.put(String.valueOf(response.getEntity()), Integer.valueOf(response.getStatus()));
		}
		else if(loginStatus == 0){
			responseMap = new HashMap<String,Integer>();
			responseMap.put("Account Locked", 0);
		}
		return responseMap;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/parentAuthenticate")
	public Map<String,Integer> parentAuthenticate(@FormParam("username") String username, @FormParam("password") String password,  @FormParam("role") String userRole){
		Map<String,Integer> responseMap = null;
		Response response = null;
		int loginStatus = 0;
		String token = "";
		loginStatus = this.authService.validateParentLogin(username, password, userRole);
		if(loginStatus == 1) {
			try
			{
				token = TokenUtil.createToken(username, userRole);
				
			} catch (Exception e) {
				response = this.genericExceptionMapper.toResponse(e);
			}			
		}
		response = (response!=null)? response:Response.ok(token).build();
		if(!token.equals("") && loginStatus == 1){
		responseMap = new HashMap<String,Integer>();
		responseMap.put(String.valueOf(response.getEntity()), Integer.valueOf(response.getStatus()));
		}
		else if(loginStatus == 0){
			responseMap = new HashMap<String,Integer>();
			responseMap.put("Account Locked", 0);
		}
		return responseMap;
	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/unlockAccount")
	public boolean unlockUserAccount(@FormParam("username") String userName, @FormParam("secretQuestion") String secretQuestion,  @FormParam("secretAnswer") String secretAnswer) throws NumberFormatException, DataImportException{	
		return(authService.unlockUser(userName, Integer.valueOf(secretQuestion), secretAnswer));			
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/forgetPassword")
	public boolean forgetPassword(@FormParam("username") String userName, @FormParam("secretQuestion") String secretQuestion,  @FormParam("secretAnswer") String secretAnswer) throws NumberFormatException, DataImportException{	
		return(authService.forgetPassword(userName, Integer.valueOf(secretQuestion), secretAnswer));			
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/resetPassword")
	public boolean resetPassword(@FormParam("username") String userName, @FormParam("password") String password) throws NumberFormatException, DataImportException{	
		return(authService.changePassword(userName,password));			
	}*/
}