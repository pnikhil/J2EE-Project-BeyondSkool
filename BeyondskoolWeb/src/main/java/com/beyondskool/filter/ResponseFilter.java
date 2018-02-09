package com.beyondskool.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Priority;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.TokenUtil;

import io.jsonwebtoken.Claims;

@Provider
@Priority(value = 1)
@PreMatching
public class ResponseFilter implements ContainerResponseFilter {
	
	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("BS");

	public ResponseFilter() {		
	}

	public void filter(ContainerRequestContext containerRequest, ContainerResponseContext containerResponse)
			throws IOException {		
		INFO_LOGGER.log(Level.INFO, "ResponseFilter: filter method started");
		Map<String, Claims> userData;
		String userName;
		String role, token;
		TokenUtil tokenUtil;
		String path = containerRequest.getUriInfo().getPath(true);
		String method = containerRequest.getMethod();
		String extension = FilenameUtils.getExtension(path);
		if (path.startsWith("/login/") || path.startsWith("/contact") || path.startsWith("/centres") || path.startsWith("/signup/") || path.startsWith("/activities/") || path.startsWith("/areas/") || path.startsWith("/cities/") || path.startsWith("/reviews/")) {
			return;
		} else if (CommonUtil.isType("DOCUMENT_TYPE", extension) || CommonUtil.isType("EXCEL_TYPE", extension)
				|| CommonUtil.isType("IMAGE_TYPE", extension) || CommonUtil.isType("TIFF_TYPE", extension)) {

			return;
		}else {
			if (containerRequest.getProperty("auth-failed") != null) {
				Boolean failed = (Boolean) containerRequest.getProperty("auth-failed");
				if (failed) {					
					return;
				}
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
									if (diff > 0 && diff < Integer.valueOf(Dir_ResourceBundle.getString("SESSION_TIME_REMAINING"))) {
										userName = entry.getKey();
										role = String.valueOf(entry.getValue().get("userRole"));
										token = TokenUtil.createToken(userName, role);
										containerResponse.getHeaders().add("jwt", token);
									} else if (diff <= 0) {
										containerRequest
												.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
									} else {
										containerResponse.getHeaders().add("jwt",
												containerRequest.getHeaderString("Authorization").split(" ")[1]);
									}
								}
							}
						} catch (InvalidKeyException | InvalidKeySpecException | IllegalBlockSizeException
								| BadPaddingException e) {
							ERROR_LOGGER
							.log(Level.ERROR,
									"ResponseFilter  : filter() - Exception Occured");
						}
					}
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" - Entity: ").append(containerResponse.getEntity());
					// containerRequest.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				}
			}
		}
		INFO_LOGGER.log(Level.INFO, "ResponseFilter: filter method ended");
	}
	
	public boolean isNullOrEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}
}