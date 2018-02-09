package com.beyondskool.dataimport.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Level;

import com.beyondskool.domain.Activity;
import com.beyondskool.domain.Centre;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.EncryptionUtil;
import com.beyondskool.util.MailUtil;

public class CentreDAO extends BaseDAO {

	public CentreDAO() throws DataImportException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Centre> getCentreData() throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_DATA");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAddress(resultSet.getString("address"));
					centre.setAreaId(resultSet.getInt("area_id"));
					centre.setCityId(resultSet.getInt("city_id"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setStatus(resultSet.getString("status"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}
	
	public List<Centre> getCentreDataActive() throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_DATA_ACTIVE");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAddress(resultSet.getString("address"));
					centre.setAreaId(resultSet.getInt("area_id"));
					centre.setCityId(resultSet.getInt("city_id"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setStatus(resultSet.getString("status"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}
	
	
	public List<Centre> getSummaryCentreDetailsActive() throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_SUMMARY_CENTRE_DATA_ACTIVE");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setId(resultSet.getInt("id"));
					centre.setDescription(resultSet.getString("description"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}
	
	public List<Centre> getFilteredCentreResults(int activityId) throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FILTER_CENTRE_BY_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, activityId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setDescription(resultSet.getString("description"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}
	
	public List<Centre> filterCentreByAge(int age) throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FILTER_CENTRE_BY_AGE");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, age);
			pstmt.setInt(2, age);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setDescription(resultSet.getString("description"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}
	
	public List<Centre> getCentreDataPending() throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		Centre centre = null;
		List<Centre> centreList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_DATA_PENDING");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreList = new ArrayList<Centre>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAddress(resultSet.getString("address"));
					centre.setAreaId(resultSet.getInt("area_id"));
					centre.setCityId(resultSet.getInt("city_id"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setStatus(resultSet.getString("status"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
					centreList.add(centre);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreList;
	}

	public List<Activity> getActivityData(int centreId, String centreName)
			throws DataImportException {
		DEBUG_LOGGER
				.log(Level.DEBUG, "ActivityDAO : getActivityData() started");
		Connection conn = null;
		Activity activity = null;
		List<Activity> activityList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_CENTRE_ACTIVITY_DATA");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, centreId);
			pstmt.setString(2, centreName);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					activity = new Activity();
					activity.setDay(resultSet.getString("DAY"));
					activity.setInTime(resultSet.getString("in_time"));
					activity.setOutTime(resultSet.getString("out_time"));
					activity.setFromAge(resultSet.getInt("from_age"));
					activity.setToAge(resultSet.getInt("to_age"));
					activity.setActivityName(resultSet
							.getString("activites_name"));
					activity.setAmount(resultSet.getInt("amount"));
					activity.setTotalSlots(resultSet.getInt("total_slots"));
					activity.setSlotsRemaining(resultSet.getInt("slots_remaining"));
					activity.setDuration(resultSet.getString("duration"));
					activity.setClassDescription(resultSet.getString("class_description"));
					activity.setCentreActivityId(resultSet.getInt("center_activity_id"));
					activity.setActivityId(resultSet.getInt("activity_id"));
					activity.setStartDate(resultSet.getString("start_date"));
					activity.setEndDate(resultSet.getString("end_date"));
					activityList.add(activity);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return activityList;
	}

	public int createCentre(String ownerName, String emailId, String password,
			String centreName, String description, String contactNo,
			String fileNames, String address, String status, int city,
			int area, String isSuccess,int imageId) throws DataImportException,
			InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_CENTRE");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ownerName);
			pstmt.setString(2, emailId);
			pstmt.setString(3, EncryptionUtil.encrypt(password));
			pstmt.setString(4, centreName);
			pstmt.setString(5, description);
			pstmt.setString(6, contactNo);
			pstmt.setString(7, fileNames);
			pstmt.setInt(8, city);
			pstmt.setInt(9, area);
			pstmt.setString(10, address);
			pstmt.setTimestamp(11, CommonUtil.getTimeStamp());
			pstmt.setString(12, status);
			pstmt.setInt(13,imageId);
			pstmt.executeUpdate();
			int centreId = getCentreId(centreName, area, address);
			return centreId;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public int getCentreId(String centreName, int areaId, String address)
			throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getCentreData() started");
		Connection conn = null;
		int centreId = 0;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_ID");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreName);
			pstmt.setInt(2, areaId);
			pstmt.setString(3, address);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				while (resultSet.next()) {
					centreId = resultSet.getInt("id");
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getActivityData() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "ActivityDAO : getActivityData() ended");
		return centreId;
	}

	public boolean deleteCentre(int centreId) throws DataImportException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_CENTRE");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, centreId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public Centre getCompleteCentreDetails(String centreName)
			throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() started");
		Connection conn = null;
		Centre centre = null;
		Activity activity = null;
		List<Activity> activityList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection conn2 = null;
		String query2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet2 = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_COMPLETE_DETAILS");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreName);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setAddress(resultSet.getString("address"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
				}
			}
			query2 = this.prop.getProperty("FETCH_COMPLETE_ACTIVITY_DETAILS");
			conn2 = DBConnection.getDbconnection();
			pstmt2 = conn2.prepareStatement(query2);
			pstmt2.setInt(1, centre.getId());
			pstmt2.setString(2, centre.getCentreName());
			resultSet2 = pstmt2.executeQuery();
			if (null != resultSet2) {
				while (resultSet2.next()) {
					activity = new Activity();
					activity.setDay(resultSet2.getString("day"));
					activity.setInTime(resultSet2.getString("in_time"));
					activity.setOutTime(resultSet2.getString("out_time"));
					activity.setFromAge(resultSet2.getInt("from_age"));
					activity.setToAge(resultSet2.getInt("to_age"));
					activity.setActivityName(resultSet2
							.getString("activites_name"));
					activity.setAmount(resultSet2.getInt("amount"));
					activity.setSlotsRemaining(resultSet2
							.getInt("slots_remaining"));
					activity.setImagePath(resultSet2.getString("img_path"));
					activity.setDuration(resultSet2.getString("duration"));
					activity.setClassDescription(resultSet2.getString("class_description"));
					activity.setId(resultSet2.getInt("id"));
					activity.setStartDate(resultSet2.getString("start_date"));
					activity.setEndDate(resultSet2.getString("end_date"));
					activityList.add(activity);
				}
			}
			centre.setActivityList(activityList);

		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getCompleteCentreDetails() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
			DBConnection.closeConnection(resultSet2, pstmt2, conn2);
		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() ended");
		return centre;
	}
	
	public Centre getCompleteCentreDetails(String centreName, int centreId)
			throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() started");
		Connection conn = null;
		Centre centre = null;
		Activity activity = null;
		List<Activity> activityList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection conn2 = null;
		String query2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet2 = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_COMPLETE_DETAILS_WITH_ID");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreName);
			pstmt.setInt(2, centreId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setAddress(resultSet.getString("address"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
				}
			}
			query2 = this.prop.getProperty("FETCH_COMPLETE_ACTIVITY_DETAILS");
			conn2 = DBConnection.getDbconnection();
			pstmt2 = conn2.prepareStatement(query2);
			pstmt2.setInt(1, centreId);
			pstmt2.setString(2, centre.getCentreName());
			resultSet2 = pstmt2.executeQuery();
			if (null != resultSet2) {
				while (resultSet2.next()) {
					activity = new Activity();
					activity.setDay(resultSet2.getString("day"));
					activity.setInTime(resultSet2.getString("in_time"));
					activity.setOutTime(resultSet2.getString("out_time"));
					activity.setFromAge(resultSet2.getInt("from_age"));
					activity.setToAge(resultSet2.getInt("to_age"));
					activity.setActivityName(resultSet2
							.getString("activites_name"));
					activity.setAmount(resultSet2.getInt("amount"));
					activity.setSlotsRemaining(resultSet2
							.getInt("slots_remaining"));
					activity.setTotalSlots(resultSet2
							.getInt("total_slots"));
					activity.setImagePath(resultSet2.getString("img_path"));
					activity.setDuration(resultSet2.getString("duration"));
					activity.setClassDescription(resultSet2.getString("class_description"));
					activity.setId(resultSet2.getInt("id"));
					activity.setStartDate(resultSet2.getString("start_date"));
					activity.setEndDate(resultSet2.getString("end_date"));
					activityList.add(activity);
				}
			}
			centre.setActivityList(activityList);

		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getCompleteCentreDetails() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
			DBConnection.closeConnection(resultSet2, pstmt2, conn2);
		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() ended");
		return centre;
	}

	public Centre getCompleteCentreProfile(String centreEmail)
			throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() started");
		Connection conn = null;
		Centre centre = null;
		//Activity activity = null;
		//List<Activity> activityList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Connection conn2 = null;
		String query2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet2 = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CENTRE_COMPLETE_PROFILE");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				//activityList = new ArrayList<Activity>();
				while (resultSet.next()) {
					centre = new Centre();
					centre.setId(resultSet.getInt("id"));
					centre.setOwnerName(resultSet.getString("owner_name"));
					centre.setMailId(resultSet.getString("mail_id"));
					centre.setCentreName(resultSet.getString("center_name"));
					centre.setDescription(resultSet.getString("description"));
					centre.setContactNo(resultSet.getString("contact_no"));
					centre.setImagePath(resultSet.getString("img_path"));
					centre.setAreaName(resultSet.getString("area_name"));
					centre.setCityName(resultSet.getString("city_name"));
					centre.setAddress(resultSet.getString("address"));
					centre.setStatus(resultSet.getString("status"));
					centre.setImageDirId(resultSet.getInt("img_dir_id"));
				}
			}
			if (centre == null) {
				query2 = this.prop.getProperty("FETCH_BASIC_CENTRE_DETAILS");
				pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, centreEmail);
				resultSet2 = pstmt2.executeQuery();
				if (null != resultSet2) {					
					while (resultSet2.next()) {
						centre = new Centre();
						centre.setId(resultSet2.getInt("id"));
						centre.setOwnerName(resultSet2.getString("owner_name"));
						centre.setMailId(resultSet2.getString("mail_id"));
						centre.setContactNo(resultSet2.getString("contact_no"));
						centre.setStatus(resultSet2.getString("status"));
						centre.setImageDirId(resultSet2.getInt("img_dir_id"));
					}
				}
			}/* else {
				query2 = this.prop.getProperty("FETCH_COMPLETE_ACTIVITY_DETAILS");
				conn2 = DBConnection.getDbconnection();
				pstmt2 = conn2.prepareStatement(query2);
				pstmt2.setInt(1, centre.getId());
				pstmt2.setString(2, centre.getCentreName());
				resultSet2 = pstmt2.executeQuery();
				if (null != resultSet2) {
					while (resultSet2.next()) {
						activity = new Activity();
						activity.setDay(resultSet2.getString("day"));
						activity.setInTime(resultSet2.getString("in_time"));
						activity.setOutTime(resultSet2.getString("out_time"));
						activity.setFromAge(resultSet2.getInt("from_age"));
						activity.setToAge(resultSet2.getInt("to_age"));
						activity.setActivityName(resultSet2
								.getString("activites_name"));
						activity.setAmount(resultSet2.getInt("amount"));
						activity.setSlotsRemaining(resultSet2
								.getInt("slots_remaining"));
						activity.setImagePath(resultSet2.getString("img_path"));
						activity.setDuration(resultSet2.getString("duration"));
						activity.setClassDescription(resultSet2.getString("class_description"));
						activity.setId(resultSet2.getInt("id"));
						activityList.add(activity);
					}
				}
				centre.setActivityList(activityList);
			}*/
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getCompleteCentreDetails() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
			DBConnection.closeConnection(resultSet2, pstmt2, conn2);
		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() ended");
		return centre;
	}

	public int createCentreForCurrentUser(String emailId, String centreName,
			String description, String fileNames,
			String address, int city, int area) throws DataImportException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("NEW_CENTRE_BY_OWNER");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreName);
			pstmt.setString(2, description);
			pstmt.setString(3, fileNames);
			pstmt.setInt(4, city);
			pstmt.setInt(5, area);
			pstmt.setString(6, address);
			pstmt.setTimestamp(7, CommonUtil.getTimeStamp());
			pstmt.setString(8, "Moderation");
			pstmt.setString(9, emailId);
			pstmt.executeUpdate();
			int centreId = getCentreId(centreName, area, address);
			return centreId;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public boolean approveCentre(int centreId, String centreEmail) throws DataImportException, AddressException, MessagingException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("APPROVE_CENTRE");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, centreId);
			pstmt.executeUpdate();
			new MailUtil().sendEmailOnCentreActive(centreEmail);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public boolean updateCentreDetails(int centreId, String ownerName, String contactNo, String address,
			String description/*, String imageFiles*/) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_CENTRE_DETAILS");
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, ownerName);
			pstmt.setString(2, address);
			pstmt.setString(3, description);
			/*pstmt.setString(4, imageFiles);*/
			pstmt.setTimestamp(4, CommonUtil.getTimeStamp());
			pstmt.setString(5, contactNo);
			pstmt.setInt(6, centreId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public boolean deleteCentreActivity(int centreActivityId) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_CENTRE_ACTIVITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, centreActivityId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}		
	}

	public boolean updateImageDetails(int centreId, String centreName,
			String fileAppend) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_IMAGES");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileAppend);
			pstmt.setInt(2, centreId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public boolean updateCentreImages(int centreId, String imageNames) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_IMAGES");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, imageNames);
			pstmt.setInt(2, centreId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public boolean updateCentreDetailsByAdmin(int centreId, String ownerName,
			String contactNo, String address, String description, int city,
			int area, String emailId) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_CENTRE_DETAILS_BY_ADMIN");
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, ownerName);
			pstmt.setString(2, address);
			pstmt.setString(3, description);			
			pstmt.setTimestamp(4, CommonUtil.getTimeStamp());
			pstmt.setString(5, contactNo);
			pstmt.setInt(6, city);
			pstmt.setInt(7, area);
			pstmt.setString(8, emailId);
			pstmt.setInt(9, centreId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}
}
