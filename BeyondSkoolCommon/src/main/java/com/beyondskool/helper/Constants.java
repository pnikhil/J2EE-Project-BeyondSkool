package com.beyondskool.helper;

import java.util.LinkedHashMap;

import java.util.Map;
import java.util.ResourceBundle;

public final class Constants {

	public static final String BUNDLE_NAME = "BS";
		
	public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static final String LIC_DATE_FORMAT = "MM/dd/yyyy";

	public static final String LIC_KEY_ALGORITHM = "RSA";
	public static final String LIC_SIGN_ALGORITHM = "SHA1withRSA";

	// Constants used in Encryption & Decryption

	public static final String ENCODE_SCHEME = "DESede";
	public static final String UNICODE_FORMAT = "UTF8";
	public static final String ENCODE_KEY = "ThisIsSpartaThisIsSparta";
	public static final String SECURED_USERNAME = "SECURED_USERNAME";
	public static final String SECURED_PASSWORD = "SECURED_PASSWORD";
	
	

	
	//Hiscox xml
	
		public static final String SUBMISSIONS_TAG = "submissions";
		public static final String ROOTFOLDER_TAG = "RootFolder";
		public static final String FOLDERNAME_TAG = "FolderName";
		public static final String FILENAME_TAG = "FileName";
		public static final String LASTRUN_TAG = "LastRun";
		public static final String ARCHIVE_TAG = "Archive";
	
	// GBD Submission XML Tag elements
	public static final String EMAIL_TAG = "email";
	public static final String KANA_ID_TAG = "kanaId";
	public static final String DATE_TAG = "date";
	public static final String ATTACHMENTS_TAG = "attachments";
	public static final String ORIGINAL_ATTACHMENTS_TAG = "OriginalAttachments";

	// Marsh Binder Document Classifier
	public static final String BINDER = "BINDER";
	public static final String INSURED_NAME = "SunTechInc.";
	public static final String HIPHON_DELIMITER = "-";
	public static final String ERROR_MSG_IDENTIFIER = "#";

	public static final String BINARY_DATA = "BinaryData";
	public static final String REFERENCE_ID = "ReferenceId";

	// Extraction Constants - LOG MODE
	public static final String DEBUG_MODE = "DEBUG";
	
	
	
	//Types of rule file available
	public enum RULEFILE_TYPE {
		DRL,XSL;
	}
	
	public static final String PDF_CLASSIFICATION_TYPE = "PDF_CLASSIFICATION_TYPE";
	public static final String XLS_CLASSIFICATION_TYPE = "XLS_CLASSIFICATION_TYPE";
	public static final String XLSX_CLASSIFICATION_TYPE = "XLSX_CLASSIFICATION_TYPE";
	public static final String CUSTOMER_TYPE_EDM = "CUSTOMER_TYPE_EDM";
	public static final String EDM_CLASSIFICATION_TYPE = "EDM_CLASSIFICATION_TYPE";

	// DataQualWeb Role and Screen tabs
	public static final String DIA = "DIA";
	public static final String WORKFLOW = "WorkFlow";
	public static final String REASSIGNBENCH = "ReassignBench";
	public static final String CASEWORKBENCH = "CaseWorkBench";
	public static final String DOCWORKBENCH = "DocWorkBench";
	public static final String HOME = "Home";
	public static final String CUSTOM_LOGIN_ENABLED = "CUSTOM_LOGIN_ENABLED";

	//No dataEXtraction Variable
	public static final String NODATAEXTRACTION = "NO DATA TO BE EXTRACTED";
	
	//errorCode Length in document table
	public static final int ERRCODELENGTH = 12;
	//exception reason code for disoriented image
	public static final String EXCEPTION_REASON_CODE_FOR_DISORIENTED_IMAGE = "201";
	// DataQualWeb Role and Screen tabs
		public static final String DIM = "DIM";
	
	// Added for DIM Report generation
	public static final String CSV_EXT = ".csv";
	public static final String MODIFIEDFIELD_TERM = "ModifiedFields:";
	public static final String BLWTHRESHOLD_TERM = "BelowThreshold:";
	public static final String SPECIALCHAR_FLG1_TERM = "SpecialCharFlag1:";
	public static final String SPELLCHECK_FLG1_TERM = "SpellCheckFlag1:";

	// added for mergedFinalXml construction
		public static final String COLON_CHAR = ":";
		public static final String DOCUMENT_CODE = "DocumentCode";
		public static final String WORKERSCOMPENSATION = "WorkCompPolicyQuoteInqRq";

		public static final String MENU="menu";
		
		public static final String FORGOT_PASSWORD="FORGOT_PASSWORD";
		public static final String EXPIRY_DAYS_COUNT="EXPIRY_DAYS_COUNT";
		public static final String HISTORICAL_PASSWORD_COUNT="HISTORICAL_PASSWORD_COUNT";
		public static final String FAILED_ATTEMPT_COUNT="FAILED_ATTEMPT_COUNT";
		public static final String LOGIN_DEFAULT_STATUS="LOGIN_DEFAULT_STATUS";
		public static final String LOGIN_FRST_STATUS="LOGIN_FRST_STATUS";
		
		
		public static final String FAILED_ATTEMPT_LOCK="FAILED_ATTEMPT_LOCK";
		public static final String RECENT_PASSWORD_CHECK="RECENT_PASSWORD_CHECK";
		public static final String PASSWORD_EXPIRY="PASSWORD_EXPIRY";
		public static final String CHANGE_PASSWORD="CHANGE_PASSWORD";
		public static final String UNLOCK_ACCOUNT="UNLOCK_ACCOUNT";
		public static final String ADMIN_DEFAULT_PASSWORD="Test123**";

		public static final String APPLICATION = "Application";
		public static final String DI="DI";
		
		//Hiscox Document Classifier
		public static final String MarineSlip = "Marine Slip";  
		public static final String FOLDER_CURRENCY = "Folder Currency";  
		public static final String FOLDER_ROE = "Folder ROE";  
		public static final String UNDERWRITER = "Underwriter";  
		public static final String TYPE_OF_MASTER_POLICY = "Type Of Master Policy";  
		public static final String COB = "COB";  
		public static final String CLIENT_MOP = "Client MOP";  
		public static final String MOP = "MOP";  
		public static final String BROKER = "Broker";  
		public static final String ORIGINAL_INSURED = "Original Insured";  
		public static final String PROPERTY = "Property";  
		public static final String FRIENDLY_NAME = "FriendlyName";  
		public static final String AeroSpace = "AeroSpace";
		public static final String TRVPackagePolicy="TRVPackagePolicy";
		public static final String COUNTRY = "Country";
		public static final String STATE = "State";
		public static final String TYPE = "type";
		
		public static final String[] AVAILABLE_DATE_FMT = {
			"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
			"MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
			"MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
			"MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
			"yyyy:MM:dd HH:mm:ss", "yyyyMMdd", "MMMMM dd,yyyy",
			"MMMMM dd yyyy", "E MMM dd HH:mm a zzz yyyy",
			"EEE MMM d HH:mm:ss zzz yyyy" };

	public static final Map<String, String> DATE_FORMAT_REGEXPS = new LinkedHashMap<String, String>() {
		{
			put("^\\d{8}$", "MMddyyyy");
			put("^\\d{6}$", "MMddyy");
			put("^\\d{7}$", "Mddyyyy");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "MM-dd-yyyy");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
			put("^\\d{1,2}\\s[a-z]{4}\\s\\d{4}$", "dd MMMM yyyy");
			put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "MM-dd-yy");
			put("^\\d{1,2}/\\d{1,2}/\\d{2}$", "MM/dd/yy");
			put("^\\d{1,2}\\s\\d{1,2}\\s\\d{2}$", "MM dd yy");
			put("^\\d{1,2}\\s\\d{1,2}\\s\\d{4}$", "MM dd yyyy");
			put("^\\d{4}\\s\\d{1,2}\\s\\d{1,2}$", "yyyy MM dd");
			put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$", "MM.dd.yyyy");
			put("^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", "yyyy.MM.dd");
			put("^\\d{1,2}\\.\\d{1,2}\\.\\d{2}$", "MM.dd.yy");
	put("^((?:J(anuary|u(ne|ly))|February|Ma(rch|y)|A(pril|ugust)|(((Sept|Nov|Dec)em)|Octo)ber)|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\s((\\d{1,2},(\\s)?\\d{4})|\\d{2,4})$","MMMM d,yyyy");
		}
	};

	
	public static final String[] MAIL_DATE_FMT = {
			"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
			"MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
			"MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
			"MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
			"yyyy:MM:dd HH:mm:ss", "yyyyMMdd", "MMMMM dd,yyyy",
			"MMMMM dd yyyy", "E MMM dd hh:mm a zzz yyyy",
			"EEE MMM d hh:mm:ss zzz yyyy" };
		
}



