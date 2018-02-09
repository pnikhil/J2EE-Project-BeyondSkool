package com.beyondskool.domain;

import java.io.Serializable;

public class ActivityCentre implements Serializable{
	
	public ActivityCentre(){
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int activityCentreBookingId;
	private String activityName;
	private String centreName;
	private String cityName;
	private String areaName;	
	private String day;
	private String duration;
	private String inTime;
	private String outTime;
	private int fromAge;
	private int toAge;
	private int amount;
	private int slotsRemaining;
	private int totalSlots;
	private String classDescription;
	private String centreImagePath;
	private String activityImagePath;
	private String startDate;
	private String endDate;
	private int centreId;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getActivityCentreBookingId() {
		return activityCentreBookingId;
	}
	public void setActivityCentreBookingId(int activityCentreBookingId) {
		this.activityCentreBookingId = activityCentreBookingId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public int getFromAge() {
		return fromAge;
	}
	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}
	public int getToAge() {
		return toAge;
	}
	public void setToAge(int toAge) {
		this.toAge = toAge;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSlotsRemaining() {
		return slotsRemaining;
	}
	public void setSlotsRemaining(int slotsRemaining) {
		this.slotsRemaining = slotsRemaining;
	}
	public int getTotalSlots() {
		return totalSlots;
	}
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}
	public String getClassDescription() {
		return classDescription;
	}
	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	public String getCentreImagePath() {
		return centreImagePath;
	}
	public void setCentreImagePath(String centreImagePath) {
		this.centreImagePath = centreImagePath;
	}
	public String getActivityImagePath() {
		return activityImagePath;
	}
	public void setActivityImagePath(String activityImagePath) {
		this.activityImagePath = activityImagePath;
	}
	public int getCentreId() {
		return centreId;
	}
	public void setCentreId(int centreId) {
		this.centreId = centreId;
	}
}