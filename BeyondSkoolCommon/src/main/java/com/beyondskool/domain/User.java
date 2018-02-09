package com.beyondskool.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User(){
		
	}
	private int userId;
	private String beyondskoolId;
	private String parentName;
	private String childName;
	private int age;
	private String gender;
	private String email;
	private String password;
	private String fatherMobileNo;
	private String motherMobileNo;
	private String address;
	private int standard;
	private String school;
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String area;
	private String city;
	private String interestedActivities;
	private String applicableTimings;
	private String preference;
	private String updatedAt;
	private int status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBeyondskoolId() {
		return beyondskoolId;
	}

	public void setBeyondskoolId(String beyondskoolId) {
		this.beyondskoolId = beyondskoolId;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFatherMobileNo() {
		return fatherMobileNo;
	}

	public void setFatherMobileNo(String fatherMobileNo) {
		this.fatherMobileNo = fatherMobileNo;
	}

	public String getMotherMobileNo() {
		return motherMobileNo;
	}

	public void setMotherMobileNo(String motherMobileNo) {
		this.motherMobileNo = motherMobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getInterestedActivities() {
		return interestedActivities;
	}

	public void setInterestedActivities(String interestedActivities) {
		this.interestedActivities = interestedActivities;
	}

	/*public String getAlreadyKnownActivities() {
		return alreadyKnownActivities;
	}

	public void setAlreadyKnownActivities(String alreadyKnownActivities) {
		this.alreadyKnownActivities = alreadyKnownActivities;
	}*/

	public String getApplicableTimings() {
		return applicableTimings;
	}

	public void setApplicableTimings(String applicableTimings) {
		this.applicableTimings = applicableTimings;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
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
}
