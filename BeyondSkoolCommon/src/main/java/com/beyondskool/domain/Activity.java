package com.beyondskool.domain;

import java.io.Serializable;

public class Activity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Activity(){
		
	}
	
	private int id;
	private String activityName;
	private String description;
	private String imagePath;
	private String updatedAt;
	private int status;
	private String day;
	private String inTime;
	private String outTime;
	private int fromAge;
	private int toAge;
	private int amount;
	private int centreId;
	private int slotsRemaining;
	private int totalSlots;
	private String duration;
	private String classDescription;
	private int centreActivityId;
	private int activityId;
	private String startDate;
	private String endDate;
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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
	public int getCentreId() {
		return centreId;
	}
	public void setCentreId(int centreId) {
		this.centreId = centreId;
	}
	public int getTotalSlots() {
		return totalSlots;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getCentreActivityId() {
		return centreActivityId;
	}
	public void setCentreActivityId(int centreActivityId) {
		this.centreActivityId = centreActivityId;
	}
	public String getClassDescription() {
		return classDescription;
	}
	public void setClassDescription(String classDescription) {
		this.classDescription = classDescription;
	}
	public int getSlotsRemaining() {
		return slotsRemaining;
	}
	public void setSlotsRemaining(int slotsRemaining) {
		this.slotsRemaining = slotsRemaining;
	}
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
}
