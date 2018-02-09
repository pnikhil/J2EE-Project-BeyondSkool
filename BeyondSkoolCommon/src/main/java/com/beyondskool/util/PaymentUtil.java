package com.beyondskool.util;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

 
public class PaymentUtil {
	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("Common");
	
/*public static void main(String args[]) throws RazorpayException{
		RazorpayClient razorpayClient = new RazorpayClient("rzp_live_DYa0m9Ae74bNSY", "Ey47Mn1kBc68Fl8cKaBRskZM");
		List<Payment> payments = razorpayClient.Payments.fetchAll();
		for(Payment payment : payments){
			System.out.println(payment);
		}
		Payment payment = razorpayClient.Payments.fetch("pay_7X89Ff4DhOPm4O");
		int amount = (Integer)payment.get("amount")/100;
		String id = payment.get("id");
		Date createdAt = payment.get("created_at");
		Timestamp timestamp = new Timestamp(createdAt.getTime());
		System.out.println(timestamp);
		Format formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		String s = formatter.format(createdAt);
		System.out.println(s);
		String email = payment.get("email");
		String currency = payment.get("currency");
		String description = payment.get("description");
		String method = payment.get("method");		
		String contact = payment.get("contact");
		String status = payment.get("status");
	}*/

	public List<com.beyondskool.domain.Payment> getPaymentDetails(String paymentId) throws RazorpayException {
		RazorpayClient razorpayClient = new RazorpayClient( Dir_ResourceBundle.getString("KEY_ID"), Dir_ResourceBundle.getString("KEY_SECRET"));
		Payment paymentData = razorpayClient.Payments.fetch(paymentId);
		List<com.beyondskool.domain.Payment> paymentList = new ArrayList<com.beyondskool.domain.Payment>();
		int amount = (Integer)paymentData.get("amount")/100;
		String payId = paymentData.get("id");
		Date createdAt = paymentData.get("created_at");
		Format formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		String stringDate = formatter.format(createdAt);
		String email = paymentData.get("email");
		String currency = paymentData.get("currency");
		String description = paymentData.get("description");
		String method = paymentData.get("method");
		String status = paymentData.get("status");
		//int captured = Boolean.TRUE.equals(paymentData.get("captured")) ? 1 : 0;
		String contact = paymentData.get("contact");		
		com.beyondskool.domain.Payment payment = new com.beyondskool.domain.Payment();		
		payment.setAmount(amount);
		payment.setPaymentId(payId);
		payment.setCreatedAt(stringDate);
		payment.setEmail(email);
		payment.setCurrency(currency);
		payment.setPackageName(description);
		payment.setMethod(method);
		payment.setContactNo(contact);
		payment.setStatus(status);
		paymentList.add(payment);		
		return paymentList;
	}
}