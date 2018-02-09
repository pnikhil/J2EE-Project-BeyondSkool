package com.beyondskool.util;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
public class MailUtil {
	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("Common");
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage; 
 
	public boolean generateAndSendEmail(String name, String email, String subject, String message) throws AddressException, MessagingException {
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Dir_ResourceBundle.getString("CONTACT_EMAIL")));
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject(Dir_ResourceBundle.getString("CONTACT_MAIL_SUBJECT"));
		String emailBody = "Email sent by " + "<b>"+name+"</b>";
		emailBody += "<br><br>Subject: " + subject;
		emailBody += "<br><br>Sender Email ID: " + email;
		emailBody += "<br><br>Message: " + "<br><br>"+ message;
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com",  Dir_ResourceBundle.getString("GMAIL_ID"),  Dir_ResourceBundle.getString("GMAIL_PASSWORD"));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		return true;
	}
	
	public boolean sendEmailOnUserRegister(String name, String email, String childName, int childAge, String school,String password) throws AddressException, MessagingException {
	
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject(Dir_ResourceBundle.getString("PARENT_REGISTER_MAIL_SUBJECT"));
		String emailBody = "Dear " + "<b>" + name + ",</b>";
		emailBody += "<br><br>Thanks for registering on BeyondSkool. Your registration details are as follows:";
		emailBody += "<br><br>Child Name: " + childName;
		emailBody += "<br><br>Child Age: " + childAge;
		emailBody += "<br><br>Child School: " + school;
		emailBody += "<br><br>Your login details:";
		emailBody += "<br><br>Login ID:" + email;
		emailBody += "<br><br>Password:" + password;
		generateMailMessage.setContent(emailBody, "text/html"); 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com",  Dir_ResourceBundle.getString("GMAIL_ID"),  Dir_ResourceBundle.getString("GMAIL_PASSWORD"));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		return true;
	}
	
	public boolean sendEmailOnUserRegisterByAdmin(String name, String email, String childName, int childAge, String school, String interestedActivities,String password) throws AddressException, MessagingException {
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject(Dir_ResourceBundle.getString("PARENT_REGISTER_MAIL_SUBJECT"));
		String emailBody = "Dear " + "<b>" + name + ",</b>";
		emailBody += "<br><br>You have been registered on BeyondSkool. Your registration details are as follows:";
		emailBody += "<br><br>Child Name: " + childName;
		emailBody += "<br><br>Child Age: " + childAge;
		emailBody += "<br><br>Child School: " + school;
		emailBody += "<br><br>Interested Activities: " + interestedActivities;
		emailBody += "<br><br>Your login details:";
		emailBody += "<br><br>Login ID:" + email;
		emailBody += "<br><br>Password:" + password;
		generateMailMessage.setContent(emailBody, "text/html"); 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com",  Dir_ResourceBundle.getString("GMAIL_ID"),  Dir_ResourceBundle.getString("GMAIL_PASSWORD"));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		return true;
	}
	
	public boolean sendEmailOnCentreActive(String email) throws AddressException, MessagingException{
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject(Dir_ResourceBundle.getString("CENTRE_ACCOUNT_ACTIVE"));
		String emailBody = "Dear Centre owner,<b></b>";
		emailBody += "<br><br>Thanks for registering on BeyondSkool. Your centre account is now active.<br><br>";
		generateMailMessage.setContent(emailBody, "text/html"); 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com",  Dir_ResourceBundle.getString("GMAIL_ID"),  Dir_ResourceBundle.getString("GMAIL_PASSWORD"));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		return true;
	}

	public boolean sendEmailForBooking(String centreEmail, String userEmail,
			String bookingSlot, int bookingCost, String parentName,
			String ownerName, String contactNo, String centreName,
			String address, String activityName) throws MessagingException {
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(centreEmail));
		generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject(Dir_ResourceBundle.getString("PARENT_REGISTER_MAIL_SUBJECT"));
		String emailBody = "Dear " + "<b>" + parentName + ",</b>";
		emailBody += "<br><br>Thanks for booking an activity on BeyondSkool. Your activity booking details are as follows:";
		emailBody += "<br><br>Activity Name: " + activityName;
		emailBody += "<br><br>Booking Slot: " + bookingSlot;
		emailBody += "<br><br>Booking Cost: Rs." + bookingCost;
		emailBody += "<br><br>Centre Name:" + centreName;
		emailBody += "<br><br>Centre Address:" + address;
		emailBody += "<br><br>Centre Contact Person:" + ownerName;
		emailBody += "<br><br>Centre contact Number:" + contactNo;
		generateMailMessage.setContent(emailBody, "text/html"); 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com",  Dir_ResourceBundle.getString("GMAIL_ID"),  Dir_ResourceBundle.getString("GMAIL_PASSWORD"));
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		return true;
		
	}
	
	public static void main(String args[]) throws AddressException, MessagingException{
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("nikhil@techarta.com"));
		//generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(Dir_ResourceBundle.getString("CC_ADDRESS")));
		generateMailMessage.setSubject("testing");
		String emailBody = "Dear Centre owner,<b></b>";
		emailBody += "<br><br>Thanks for registering on BeyondSkool. Your centre account is now active.<br><br>";
		generateMailMessage.setContent(emailBody, "text/html"); 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.zoho.com",  "info@beyondskool.com", "anmol123");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
		
	}
}