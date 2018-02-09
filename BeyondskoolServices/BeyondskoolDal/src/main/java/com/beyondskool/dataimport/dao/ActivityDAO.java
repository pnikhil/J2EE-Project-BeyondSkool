package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.beyondskool.domain.Activity;
import com.beyondskool.domain.ActivityCentre;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;

public class ActivityDAO extends BaseDAO {

	public ActivityDAO() throws DataImportException {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Activity> getActivityDataForDropDown() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		Activity activity = null;
		List<Activity> activityList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_ACTIVITY_LIST");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					activity = new Activity();
					activity.setId(resultSet.getInt("id"));
					activity.setActivityName(resultSet.getString("activites_name"));					
					activityList.add(activity);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return activityList;
	}
	
	public List<Activity> getActivityData() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		Activity activity = null;
		List<Activity> activityList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_ACTIVITIES");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					activity = new Activity();
					activity.setId(resultSet.getInt("id"));
					activity.setActivityName(resultSet.getString("activites_name"));
					activity.setDescription(resultSet.getString("description"));
					activity.setImagePath(resultSet.getString("img_path"));
					activity.setUpdatedAt(resultSet.getString("updated_at"));
					activity.setStatus(resultSet.getInt("status"));
					activityList.add(activity);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return activityList;
	}

	public boolean addActivityForCentres(List<Activity> activityList) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_ACTIVITIES_FOR_CENTRE");
			for(Activity activity : activityList){
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, activity.getCentreId());			
				pstmt.setString(2, activity.getDay());
				pstmt.setString(3, activity.getInTime());
				pstmt.setString(4, activity.getOutTime());
				pstmt.setInt(5, activity.getId());
				pstmt.setInt(6, activity.getFromAge());
				pstmt.setInt(7, activity.getToAge());
				pstmt.setInt(8,activity.getAmount());
				pstmt.setInt(9,activity.getTotalSlots());
				pstmt.setInt(10,activity.getTotalSlots());
				pstmt.setString(11, activity.getDuration());
				pstmt.setString(12, activity.getClassDescription());
				pstmt.setString(13, activity.getStartDate());
				pstmt.setString(14, activity.getEndDate());
				pstmt.executeUpdate();
			}
			return true;
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
				DBConnection.closeConnection(pstmt, conn);
		}
		return false;
		
	}
	
	public boolean deleteActivity(int activityId) throws DataImportException {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}
	
	public boolean addActivity(String activityName, /*String activityDescription, */String fileName) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, activityName);
			/*pstmt.setString(2, activityDescription);*/
			pstmt.setString(2, fileName);
			pstmt.setTimestamp(3, CommonUtil.getTimeStamp());			
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}


	public List<ActivityCentre> getUpcomingClassData() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		ActivityCentre activityCentre = null;
		List<ActivityCentre> upcomingList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_UPCOMING_LIST");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				upcomingList = new ArrayList<ActivityCentre>();
				while (resultSet.next()) {
					activityCentre = new ActivityCentre();
					activityCentre.setActivityCentreBookingId(resultSet.getInt("id"));
					activityCentre.setActivityName(resultSet.getString("activites_name"));
					activityCentre.setActivityImagePath(resultSet.getString("activity_image"));
					activityCentre.setCentreImagePath(resultSet.getString("img_path"));
					activityCentre.setCityName(resultSet.getString("city_name"));
					activityCentre.setAreaName(resultSet.getString("area_name"));
					activityCentre.setCentreName(resultSet.getString("center_name"));
					activityCentre.setDay(resultSet.getString("day"));
					activityCentre.setInTime(resultSet.getString("in_time"));
					activityCentre.setOutTime(resultSet.getString("out_time"));
					activityCentre.setFromAge(resultSet.getInt("from_age"));
					activityCentre.setToAge(resultSet.getInt("to_age"));
					activityCentre.setAmount(resultSet.getInt("amount"));
					activityCentre.setTotalSlots(resultSet.getInt("total_slots"));
					activityCentre.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activityCentre.setDuration(resultSet.getString("duration"));
					activityCentre.setClassDescription(resultSet.getString("class_description"));
					activityCentre.setStartDate(resultSet.getString("start_date"));
					activityCentre.setEndDate(resultSet.getString("end_date"));
					activityCentre.setCentreId(resultSet.getInt("centre_id"));
					upcomingList.add(activityCentre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return upcomingList;
	}


	public List<ActivityCentre> getUpcomingByAge(int age) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		ActivityCentre activityCentre = null;
		List<ActivityCentre> upcomingList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_UPCOMING_LIST_BY_AGE");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, age);
			pstmt.setInt(2, age);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				upcomingList = new ArrayList<ActivityCentre>();
				while (resultSet.next()) {
					activityCentre = new ActivityCentre();
					activityCentre.setActivityCentreBookingId(resultSet.getInt("id"));
					activityCentre.setActivityName(resultSet.getString("activites_name"));
					activityCentre.setActivityImagePath(resultSet.getString("activity_image"));
					activityCentre.setCentreImagePath(resultSet.getString("img_path"));
					activityCentre.setCityName(resultSet.getString("city_name"));
					activityCentre.setAreaName(resultSet.getString("area_name"));
					activityCentre.setCentreName(resultSet.getString("center_name"));
					activityCentre.setDay(resultSet.getString("day"));
					activityCentre.setInTime(resultSet.getString("in_time"));
					activityCentre.setOutTime(resultSet.getString("out_time"));
					activityCentre.setFromAge(resultSet.getInt("from_age"));
					activityCentre.setToAge(resultSet.getInt("to_age"));
					activityCentre.setAmount(resultSet.getInt("amount"));
					activityCentre.setTotalSlots(resultSet.getInt("total_slots"));
					activityCentre.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activityCentre.setDuration(resultSet.getString("duration"));
					activityCentre.setClassDescription(resultSet.getString("class_description"));
					activityCentre.setStartDate(resultSet.getString("start_date"));
					activityCentre.setEndDate(resultSet.getString("end_date"));
					activityCentre.setCentreId(resultSet.getInt("centre_id"));
					upcomingList.add(activityCentre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return upcomingList;
	}


	public List<ActivityCentre> getFilteredCentreResults(int activityId) throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		ActivityCentre activityCentre = null;
		List<ActivityCentre> upcomingList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_UPCOMING_LIST_BY_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				upcomingList = new ArrayList<ActivityCentre>();
				while (resultSet.next()) {
					activityCentre = new ActivityCentre();
					activityCentre.setActivityCentreBookingId(resultSet.getInt("id"));
					activityCentre.setActivityName(resultSet.getString("activites_name"));
					activityCentre.setActivityImagePath(resultSet.getString("activity_image"));
					activityCentre.setCentreImagePath(resultSet.getString("img_path"));
					activityCentre.setCityName(resultSet.getString("city_name"));
					activityCentre.setAreaName(resultSet.getString("area_name"));
					activityCentre.setCentreName(resultSet.getString("center_name"));
					activityCentre.setDay(resultSet.getString("day"));
					activityCentre.setInTime(resultSet.getString("in_time"));
					activityCentre.setOutTime(resultSet.getString("out_time"));
					activityCentre.setFromAge(resultSet.getInt("from_age"));
					activityCentre.setToAge(resultSet.getInt("to_age"));
					activityCentre.setAmount(resultSet.getInt("amount"));
					activityCentre.setTotalSlots(resultSet.getInt("total_slots"));
					activityCentre.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activityCentre.setDuration(resultSet.getString("duration"));
					activityCentre.setClassDescription(resultSet.getString("class_description"));
					activityCentre.setStartDate(resultSet.getString("start_date"));
					activityCentre.setEndDate(resultSet.getString("end_date"));
					activityCentre.setCentreId(resultSet.getInt("centre_id"));
					upcomingList.add(activityCentre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return upcomingList;
	}


	public List<ActivityCentre> filterUpcomingByActivityAndAge(int activityId, int age) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		ActivityCentre activityCentre = null;
		List<ActivityCentre> upcomingList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_UPCOMING_LIST_BY_ACTIVITY_AND_AGE");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);
			pstmt.setInt(2, age);
			pstmt.setInt(3, age);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				upcomingList = new ArrayList<ActivityCentre>();
				while (resultSet.next()) {
					activityCentre = new ActivityCentre();
					activityCentre.setActivityCentreBookingId(resultSet.getInt("id"));
					activityCentre.setActivityName(resultSet.getString("activites_name"));
					activityCentre.setActivityImagePath(resultSet.getString("activity_image"));
					activityCentre.setCentreImagePath(resultSet.getString("img_path"));
					activityCentre.setCityName(resultSet.getString("city_name"));
					activityCentre.setAreaName(resultSet.getString("area_name"));
					activityCentre.setCentreName(resultSet.getString("center_name"));
					activityCentre.setDay(resultSet.getString("day"));
					activityCentre.setInTime(resultSet.getString("in_time"));
					activityCentre.setOutTime(resultSet.getString("out_time"));
					activityCentre.setFromAge(resultSet.getInt("from_age"));
					activityCentre.setToAge(resultSet.getInt("to_age"));
					activityCentre.setAmount(resultSet.getInt("amount"));
					activityCentre.setTotalSlots(resultSet.getInt("total_slots"));
					activityCentre.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activityCentre.setDuration(resultSet.getString("duration"));
					activityCentre.setClassDescription(resultSet.getString("class_description"));
					activityCentre.setStartDate(resultSet.getString("start_date"));
					activityCentre.setEndDate(resultSet.getString("end_date"));
					activityCentre.setCentreId(resultSet.getInt("centre_id"));
					upcomingList.add(activityCentre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return upcomingList;
	}


	public boolean updateActivity(int centreActivityId, int activityId,
			String inTime, String outTime, String day, int fromAge, int toAge,
			int totalSlots, String duration, String classDescription, int amount, String startDate, String endDate) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_CENTRE_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);
			pstmt.setString(2, inTime);
			pstmt.setString(3, outTime);
			pstmt.setString(4, day);
			pstmt.setInt(5, fromAge);
			pstmt.setInt(6, toAge);
			pstmt.setInt(7, totalSlots);
			pstmt.setInt(8, totalSlots);
			pstmt.setString(9, duration);
			pstmt.setString(10, classDescription);
			pstmt.setInt(11, amount);
			pstmt.setString(12, startDate);
			pstmt.setString(13, endDate);
			pstmt.setInt(14, centreActivityId);			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}


	public List<ActivityCentre> filterActivityByHomeFilter(int activityId,
			int age, int areaId) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		ActivityCentre activityCentre = null;
		List<ActivityCentre> upcomingList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_UPCOMING_LIST_BY_ACTIVITY_AGE_AREA");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);
			pstmt.setInt(2, age);
			pstmt.setInt(3, age);
			pstmt.setInt(4, areaId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				upcomingList = new ArrayList<ActivityCentre>();
				while (resultSet.next()) {
					activityCentre = new ActivityCentre();
					activityCentre.setActivityCentreBookingId(resultSet.getInt("id"));
					activityCentre.setActivityName(resultSet.getString("activites_name"));
					activityCentre.setActivityImagePath(resultSet.getString("activity_image"));
					activityCentre.setCentreImagePath(resultSet.getString("img_path"));
					activityCentre.setCityName(resultSet.getString("city_name"));
					activityCentre.setAreaName(resultSet.getString("area_name"));
					activityCentre.setCentreName(resultSet.getString("center_name"));
					activityCentre.setDay(resultSet.getString("day"));
					activityCentre.setInTime(resultSet.getString("in_time"));
					activityCentre.setOutTime(resultSet.getString("out_time"));
					activityCentre.setFromAge(resultSet.getInt("from_age"));
					activityCentre.setToAge(resultSet.getInt("to_age"));
					activityCentre.setAmount(resultSet.getInt("amount"));
					activityCentre.setTotalSlots(resultSet.getInt("total_slots"));
					activityCentre.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activityCentre.setDuration(resultSet.getString("duration"));
					activityCentre.setClassDescription(resultSet.getString("class_description"));
					activityCentre.setStartDate(resultSet.getString("start_date"));
					activityCentre.setEndDate(resultSet.getString("end_date"));
					upcomingList.add(activityCentre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return upcomingList;
	}

	public boolean updateActivity(String activityName, String imagePath,
			int activityId) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, activityName);
			pstmt.setString(2, imagePath);
			pstmt.setInt(3, activityId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}
}
