package com.beyondskool.domain;

import java.io.Serializable;

public class Booking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Booking(){
		
	}
	
	private int id;
	private String bookingDate;
	private String userName;
	private String userEmail;
	private String centreName;
	private String activityName;
	private String alreadyAttended;
	private int activityCost;
	private String parentName;
	private String childName;
	private String fatherMobileNo;
	private String area;
	private String centreAddress;
	private String centrePhoneNo;
	
	public int getActivityCost() {
		return activityCost;
	}
	public void setActivityCost(int activityCost) {
		this.activityCost = activityCost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getAlreadyAttended() {
		return alreadyAttended;
	}
	public void setAlreadyAttended(String alreadyAttended) {
		this.alreadyAttended = alreadyAttended;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getFatherMobileNo() {
		return fatherMobileNo;
	}
	public void setFatherMobileNo(String fatherMobileNo) {
		this.fatherMobileNo = fatherMobileNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCentreAddress() {
		return centreAddress;
	}
	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}
	public String getCentrePhoneNo() {
		return centrePhoneNo;
	}
	public void setCentrePhoneNo(String centrePhoneNo) {
		this.centrePhoneNo = centrePhoneNo;
	}	
}
