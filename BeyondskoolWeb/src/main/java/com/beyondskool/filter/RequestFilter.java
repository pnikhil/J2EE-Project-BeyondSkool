package com.beyondskool.filter;

import java.util.Date;
import java.util.Map;
import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.TokenUtil;
import com.beyondskool.vo.UserVO;

import io.jsonwebtoken.Claims;

@Provider
@Priority(value = 1)
@PreMatching
public class RequestFilter extends ResourceConfig implements ContainerRequestFilter {
	/**
	 * Apply the filter : check input request, validate or not with user auth
	 * 
	 * @param containerRequest
	 *            The request from Tomcat server
	 */

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	public RequestFilter() {

	}

	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
		INFO_LOGGER.log(Level.INFO, "RequestFilter: filter method started");
		String path = containerRequest.getUriInfo().getPath(true);
		String method = containerRequest.getMethod();
		String token;
		final String userName;
		UserVO user = new UserVO();
		final String role;
		TokenUtil tokenUtil;
		Map<String, Claims> userData;
		String extension = FilenameUtils.getExtension(path);
		// URL's that shouldn't be validated for token - roles, login end point
		if (path.startsWith("/login/") || path.startsWith("/contact") || path.startsWith("/centres") || path.startsWith("/signup/") || path.startsWith("/activities/") || path.startsWith("/areas/") || path.startsWith("/cities/") || path.startsWith("/reviews/")) {
			
			return;
		}
		else if(path.equalsIgnoreCase("/upload/file")){
			return; 
		}
		else if (CommonUtil.isType("DOCUMENT_TYPE", extension) || CommonUtil.isType("EXCEL_TYPE", extension)|| CommonUtil.isType("IMAGE_TYPE", extension) || CommonUtil.isType("TIFF_TYPE", extension)) {

			return;
		} else {
			String validationHeader = containerRequest.getHeaderString("Authorization");
			if (validationHeader != null && !validationHeader.isEmpty()) {
				if (validationHeader.startsWith("Bearer")) {
					try {
						token = validationHeader.split(" ")[1];
						tokenUtil = new TokenUtil();
						userData = tokenUtil.validateToken(token);
						if (!isNullOrEmpty(userData)) {
							for (Map.Entry<String, Claims> entry : userData.entrySet()) {
								Date expiration = entry.getValue().getExpiration();
								Date current = new Date();
								long diff = Math.abs(expiration.getTime() - current.getTime());
								if (diff > 0) {
									userName = entry.getKey();
									role = String.valueOf(entry.getValue().get("userRole"));
									user.setUserName(userName);
									user.setCurrentUserRole(role);
									/*String scheme = containerRequest.getUriInfo().getRequestUri().getScheme();
									containerRequest.setSecurityContext(new AppSecurityContext(user, scheme));*/
									return;
								} else {
									containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
								}
							}
						}
					} catch (Exception ex) {
						ERROR_LOGGER.log(Level.ERROR, "Request  : filter() - Exception Occured");
						containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
					}
				}
			} else {
				Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Invalid").build();
				containerRequest.abortWith(response);
			}
		}
		INFO_LOGGER.log(Level.INFO, "RequestFilter: filter method ended");
	}
	
	public boolean isNullOrEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}
}