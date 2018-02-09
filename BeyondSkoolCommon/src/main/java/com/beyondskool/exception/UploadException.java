package com.beyondskool.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 
 * Base class for the custom exceptions
 * 
 */

public class UploadException extends Exception {
	
	
		/**
		 * Represents the Error Code value
		 */
		private int errorCode = 0;

		/**
		 * Error message arguments
		 */
		private Object[] arguments;

		/**
		 * Represents the Error Message
		 */
		private String errorText;

		/**
		 * Represents as the Error cause
		 */
		private Throwable rootCause;

		private static final long serialVersionUID = 1L;
		
		public UploadException(){
			
		}

		public UploadException(int errorCode) {
			this.errorCode = errorCode;
		}

		public UploadException(String errorText) {
			this.errorText = errorText;
		}

		public UploadException(int errorCode, String errorText) {
			this.errorCode = errorCode;
			this.errorText = errorText;
		}

		public UploadException(int errorCode, Throwable rootCause) {
			this.errorCode = errorCode;
			this.rootCause = rootCause;
		}

		public UploadException(int errorCode, String errorText, Throwable rootCause) {
			this.errorCode = errorCode;
			this.errorText = errorText;
			this.rootCause = rootCause;

		}

		public UploadException(int errorCode, Object[] arguments) {
			this.errorCode = errorCode;
			this.arguments = arguments;
		}

		public UploadException(int errorCode, Object[] arguments, Throwable rootCause) {
			this.errorCode = errorCode;
			this.arguments = arguments;
			this.rootCause = rootCause;
		}

		public String getErrorText() {
			if (this.errorText != null) {
				return this.errorText;
			}

			Properties errorMessages = new Properties();// TODO read from
			// ErrorMessagesfile
			try {
				if (arguments == null) {
					return errorMessages.getProperty(String.valueOf(errorCode));
				} else {
					return MessageFormat.format(errorMessages.getProperty(String
							.valueOf(errorCode)), arguments);
				}
			} catch (Exception e) {
				// TODO Log it. It is deliberately suppressed
			}

			return null;
		}

		@Override
		public Throwable getCause() {
			return rootCause;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public int getErrorCode() {
			return this.errorCode;
		}

		public static String getStackTraceAsString(Throwable t) {
			// Get a string representation of the stack trace to be included
			StringWriter stringOut = new StringWriter();
			PrintWriter printOut = new PrintWriter(stringOut);
			t.printStackTrace(printOut);
			return stringOut.toString();
		}

		public String getStackTraceAsString() {
			return getStackTraceAsString(this);
		}

		@Override
		public String getMessage() {
			if (rootCause != null) {
				return "Root Cause :" + rootCause.getMessage() + "\n"
						+ getErrorText();
			} else {
				return getErrorText();
			}
		}

		public void logError() {

			if (rootCause != null) {
				// TODO call bwLog to log this
				// "Root Cause :" + rootCause.getMessage() + "\n" + getErrorText())+
				// "\n" +getStackTraceAsString();
			} else {
				// TODO call bwLog to log getErrorText()++ "\n"
				// +getStackTraceAsString();
			}
		}

	}



