package com.beyondskool.servicelocator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class implements an exception which can wrapped a lower-level exception.
 *
 */
public class ServiceLocatorException extends Exception {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Exception exception;
  public static final Logger INFO_LOGGER = Logger.getLogger("infologging");
	
	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");
	
	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
  /**
   * Creates a new ServiceLocatorException wrapping another exception, and with a detail message.
   * @param message the detail message.
   * @param exception the wrapped exception.
   */
  public ServiceLocatorException(String message, Exception exception) {
    super(message);
    this.exception = exception;
    return;
  }

  /**
   * Creates a ServiceLocatorException with the specified detail message.
   * @param message the detail message.
   */
  public ServiceLocatorException(String message) {
    this(message, null);
    return;
  }

  /**
   * Creates a new ServiceLocatorException wrapping another exception, and with no detail message.
   * @param exception the wrapped exception.
   */
  public ServiceLocatorException(Exception exception) {
    this(null, exception);
    return;
  }

  /**
   * Gets the wrapped exception.
   *
   * @return the wrapped exception.
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Retrieves (recursively) the root cause exception.
   *
   * @return the root cause exception.
   */
  public Exception getRootCause() {
	  DEBUG_LOGGER.log(Level.DEBUG, "ServiceLocatorException: getRootCause method started");  
    if (exception instanceof ServiceLocatorException) {
      return ((ServiceLocatorException) exception).getRootCause();
    }
    DEBUG_LOGGER.log(Level.DEBUG, "ServiceLocatorException: getRootCause method ended");
    return exception == null ? this : exception;
  }

  public String toString() {
	  DEBUG_LOGGER.log(Level.DEBUG, "ServiceLocatorException: toString method started"); 
	  if (exception instanceof ServiceLocatorException) {
      return ((ServiceLocatorException) exception).toString();
    }
	  DEBUG_LOGGER.log(Level.DEBUG, "ServiceLocatorException: toString method ended"); 
	  return exception == null ? super.toString() : exception.toString();
  }
}
