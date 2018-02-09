package com.beyondskool.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.beyondskool.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {

		ErrorMessage errorMessage = new ErrorMessage("RUN_TIME_ERROR", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return Response.status(errorMessage.getErrorCode()).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
	}

}
