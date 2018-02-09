package com.beyondskool.domain;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Payment(){
		
	}
	
	private int id;
	private String date;
	private String email;
	private String userName;
	private String contactNo;
	private String packageName;
	private String paymentMethod;
	private String paymentId;
	private int amount;
	private String status;
	private String city;
	private String method;
	private String currency;
	private String description;
	private String createdAt;
	private int captured;
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String stringDate) {
		this.createdAt = stringDate;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getCaptured() {
		return captured;
	}
	public void setCaptured(int captured) {
		this.captured = captured;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
}
