package com.beyondskool.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.helper.Constants;


public class CommonUtil {
	// to get the timestamp
	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	
	public static Timestamp getTimeStamp() {
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil: getTimeStamp method starts");
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		Timestamp timestamp = new Timestamp(date.getTime());
		
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil: getTimeStamp method ends");

		return timestamp;
	}
	
	// to get String Uppercase in MailList
	public static String changeToUpperCase(String senderName) {

		char[] stringArray = senderName.toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		senderName = new String(stringArray);
		return senderName;

	}
	public static boolean isType(String types, String extension) {
		if(extension==null) return false;
		boolean status = false;

		List<String> typeList = Arrays.asList(types.split(","));
		for (String type : typeList) {
			if (getTypeList(type).contains(extension.trim().toLowerCase())) {
				status = true;
				break;
			}
		}

		return status;
	}
	
	private static List<String> getTypeList(String type) {

		String imgTypes = Constants.RESOURCE_BUNDLE.getString(type);
		String[] imgType = imgTypes.split(",");

		return Arrays.asList(imgType);
	}

	// converts a string array into string
	public static String getString(String[] paramStrings) {
		DEBUG_LOGGER.log(Level.DEBUG, "FileUtil : getString() started");

		StringBuilder builder = new StringBuilder();
		for (String param : paramStrings) {
			builder.append(param);
		}

		DEBUG_LOGGER.log(Level.DEBUG, "FileUtil : getString() ends");
		return builder.toString();
	}

	// Retrieves fileExtension
	public static String getFileExtension(String paramFileName) {

		DEBUG_LOGGER.log(Level.DEBUG,
				"FileUtil: getFileExtension method started");
		int mid;
		String fileExtension = null;

		mid = paramFileName.lastIndexOf(".");
		fileExtension = paramFileName
				.substring(mid + 1, paramFileName.length());

		if (fileExtension.equalsIgnoreCase("jpg")
				|| fileExtension.equalsIgnoreCase("gif")
				|| fileExtension.equalsIgnoreCase("png")) {
			fileExtension = "jpg";
		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"FileUtil: getFileExtension method started");
		return fileExtension;
	}

	// to generate a unique id for the generating case
	public static String uniqueIdGenerator() {
		DEBUG_LOGGER.log(Level.DEBUG,
				"CommonUtil: uniqueIdGenerator method starts");

		String uniId = UUID.randomUUID().toString();

		String docId = null;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < uniId.length(); i++) {
			char c = uniId.charAt(i);
			if (Character.isDigit(c)) {
				builder.append(c);
			}
		}
		docId = builder.toString();
		docId = docId.substring(0, 8);
		DEBUG_LOGGER.log(Level.DEBUG,
				"CommonUtil: uniqueIdGenerator method ends");

		return docId;
	}

	public static Date getDateObj(String dateString) throws ParseException {

		String pattern = "MM/dd/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(dateString);
		return date;
	}

	public static Date compareDates(List<Date> dates, String minOrMax) {

		Date returnDate = null;
		if (minOrMax.equalsIgnoreCase("minimum")) {

			for (int i = 0; i < dates.size(); i++) {
				if (null == returnDate) {
					returnDate = dates.get(i);
				} else if (dates.get(i).before(returnDate)) {
					returnDate = dates.get(i);
				}

			}
		} else if (minOrMax.equalsIgnoreCase("maximum")) {

			for (int i = 0; i < dates.size(); i++) {
				if (null == returnDate) {
					returnDate = dates.get(i);
				} else if (dates.get(i).after(returnDate)) {
					returnDate = dates.get(i);
				}

			}
		}
		return returnDate;
	}

	public static Date getDate(String dateString) throws ParseException {

		String pattern = "MMddyyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(dateString);
		return date;
	}

	

	// Converting the date format into the required pattern
	public static String dateTimeConverter(String recievedDate_Val) {
		String format = "EEEE MMMM dd hh:mm:ss zzz yyyy";
		String formattedDate = "";
		try {
			Date receivedDate = new SimpleDateFormat(format)
					.parse(recievedDate_Val);
			formattedDate = new SimpleDateFormat("dd/yyyy")
					.format(receivedDate);
			SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
			String month = monthFormat.format(receivedDate);
			formattedDate = month + "/" + formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formattedDate;

	}

	// Method to split based on regex character and trims the
	// trailing and leading spaces in the resultant array on iteration...
	public static String[] splitAndTrim(String regexChar, String inputVal) {
		// modified for words not highlighting..
		List<String> list = new ArrayList<String>();
		if (null != inputVal) {
			for (String obj : (inputVal.trim()).split(regexChar)) {
				list.add(obj.trim());

			}
			// String[] objArr = (inputVal.trim()).split(regexChar);
			// List<String> list = new ArrayList<String>(Arrays.asList(objArr));
			Iterator<String> itr = list.iterator();
			String strElement = "";
			while (itr.hasNext()) {
				strElement = ((String) itr.next()).trim();
				if (strElement.equals("") || strElement.isEmpty()) {
					itr.remove();
				}
			}
		}
		return list.toArray(new String[0]);
	}

	// Method to get double array from string array...
	public static double[] getDoubleArray(String[] inputArray)
			throws NumberFormatException {
		double[] outputArray = new double[inputArray.length];
		if (null != inputArray) {
			for (int i = 0; i < inputArray.length; i++) {
				if (null != inputArray[i] && !inputArray[i].isEmpty()) {
					outputArray[i] = Double.parseDouble(inputArray[i]);
				}
			}
		}
		return outputArray;
	}

	public static String getAttachmentId(String submissionId, int count) {
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil : getAttachmentId() started");
		String attachmentId = null;
		if (null != submissionId && !submissionId.isEmpty()) {
			if (count < 10) {
				attachmentId = "A" + submissionId + "0" + count;
			} else {
				attachmentId = "A" + submissionId + count;
			}
		}
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil : getAttachmentId() ends");
		return attachmentId;
	}

	public static String getDocId(String caseId, int count) {
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil : getDocId() started");
		String docId = null;
		if (null != caseId && !caseId.isEmpty()) {
			docId = caseId.replace("C", "D");
			if (count < 10) {
				docId = docId + "0" + count;
			} else {
				docId = docId + count;
			}
		}
		DEBUG_LOGGER.log(Level.DEBUG, "CommonUtil : getDocId() ends");
		return docId;
	}

	/**
	 * Convert to bytes.
	 * 
	 * @param inputStream
	 *            the input stream
	 * @return the byte[]
	 */
	public static byte[] convertToBytes(InputStream inputStream) {
		byte[] dataBytes = null;

		if (inputStream != null) {

			try {

				dataBytes = IOUtils.toByteArray(inputStream);

			} catch (IOException ioException) {

				INFO_LOGGER.log(Level.ERROR,
						"CommonUtil: convertToBytes method - "
								+ getStackTraceAsString(ioException));
			}
		}
		return dataBytes;
	}

	/**
	 * Convert to list.
	 * 
	 * @param input
	 *            the input
	 * @param delimiter
	 *            the delimiter
	 * @return the list
	 */
	public static List<String> convertToList(String input, String delimiter) {
		INFO_LOGGER.log(Level.INFO, "CommonUtil: convertToList method starts");
		List<String> list = new ArrayList<String>();

		if (input != null) {

			StringTokenizer strTokenizer = new StringTokenizer(input, delimiter);

			while (strTokenizer.hasMoreTokens()) {
				list.add(strTokenizer.nextToken());
			}

		}
		INFO_LOGGER.log(Level.INFO, "CommonUtil: convertToList method ends");
		return list;
	}

	/**
	 * Convert to array.
	 * 
	 * @param input
	 *            the input
	 * @param delimiter
	 *            the delimiter
	 * @return the string[]
	 */
	public static String[] convertToArray(String input, String delimiter) {
		INFO_LOGGER.log(Level.INFO, "CommonUtil: convertToArray method starts");

		String[] strArray = null;
		int tokenCount = 0;

		if (input != null) {

			StringTokenizer strTokenizer = new StringTokenizer(input, delimiter);
			strArray = new String[strTokenizer.countTokens()];

			while (strTokenizer.hasMoreTokens()) {
				strArray[tokenCount] = strTokenizer.nextToken();
				tokenCount++;
			}

		}
		INFO_LOGGER.log(Level.INFO, "CommonUtil: convertToArray method ends");
		return strArray;
	}

	/**
	 * Validate extension.
	 * 
	 * @param regEx
	 *            the reg ex
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean validateExtension(String regEx, String name) {
		Pattern pattern = null;
		Matcher matcher = null;

		pattern = Pattern.compile(regEx);
		matcher = pattern.matcher(name);

		return matcher.matches();
	}

	/**
	 * Gets the stack trace as string.
	 * 
	 * @param t
	 *            the t
	 * @return the stack trace as string
	 */
	public static String getStackTraceAsString(Throwable t) {
		// Get a string representation of the stack trace to be included
		StringWriter stringOut = new StringWriter();
		PrintWriter printOut = new PrintWriter(stringOut);
		t.printStackTrace(printOut);
		return stringOut.toString();
	}

	/**
	 * Gets the error message.
	 * 
	 * @param resourceBundle
	 *            the resource bundle
	 * @param errorCode
	 *            the error code
	 * @return the error message
	 */
	public static String getErrorMessage(ResourceBundle resourceBundle,
			String errorCode) {
		String errorMessage = null;
		errorMessage = resourceBundle.getString(errorCode);
		return errorMessage;
	}

	/**
	 * Convert to string.
	 * 
	 * @param list
	 *            the list
	 * @param delimiter
	 *            the delimiter
	 * @return the string
	 */
	public static String convertToString(List<String> list, String delimiter) {
		StringBuilder strBuilder = new StringBuilder();
		String strValue = null;

		for (String value : list) {
			strBuilder.append(value);
			strBuilder.append(delimiter);
		}

		if (strBuilder.length() > 0) {
			strValue = strBuilder.substring(0, strBuilder.length() - 1);
		}
		return strValue;
	}

	
	
	

	public static String convertDateToMMddYYYY(String yyyyMMddhhmmss) {
		if (yyyyMMddhhmmss == null || "".equals(yyyyMMddhhmmss))
			return null;
		String formattedDate = "";
		try {
			Date receivedDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.parse(yyyyMMddhhmmss);
			formattedDate = new SimpleDateFormat("MM/dd/yyyy")
					.format(receivedDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;

	}
	
	public static String convertDateFormat(String inputFormatPatter,String outPutFormatPattern,String inputValue) {		
		String formattedDate = "";
		try {
			Date receivedDate = new SimpleDateFormat(inputFormatPatter).parse(inputValue);
			formattedDate = new SimpleDateFormat(outPutFormatPattern).format(receivedDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;

	}	
	

	// Utility method created to get the calendar instance with UTC timezone to
	// avoid conflict with DB server connected through fabric url....
	public static Calendar getUTCCalendar() {
		INFO_LOGGER.log(Level.INFO, "getUTCCalendar method call starts");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		INFO_LOGGER.log(Level.INFO, "getUTCCalendar method call ends");
		return cal;
	}
	public static boolean isEmpty(String dataValue) {
		if(dataValue.length()==0)
		{
			return true;
		}
		return false;
	}
	
	public static int differenceInMinutes(Date laterDate, Date earlierDate) {
		return (int)((laterDate.getTime()/60000) - (earlierDate.getTime()/60000));
	}

}
