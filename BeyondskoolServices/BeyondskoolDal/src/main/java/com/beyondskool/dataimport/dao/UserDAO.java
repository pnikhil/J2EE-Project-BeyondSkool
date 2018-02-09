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
import com.beyondskool.domain.User;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.EncryptionUtil;
import com.beyondskool.util.MailUtil;

public class UserDAO extends BaseDAO {

	public UserDAO() throws DataImportException {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<User> getUserDetailsList() throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "UserDAO : getUserDetailsList() started");
		Connection conn = null;
		User user = null;
		List<User> userList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_USER_DATA");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				userList = new ArrayList<User>();
				while (resultSet.next()) {
					user = new User();
					user.setUserId(resultSet.getInt("user_id"));
					user.setBeyondskoolId(resultSet.getString("beyondskool_id"));
					user.setParentName(resultSet.getString("parent_name"));
					user.setChildName(resultSet.getString("child_name"));
					user.setAge(resultSet.getInt("age"));
					user.setGender(resultSet.getString("gender"));
					user.setEmail(resultSet.getString("email"));
					user.setFatherMobileNo(resultSet.getString("father_mobile_no"));
					user.setMotherMobileNo(resultSet.getString("mother_mobile_no"));
					user.setAddress(resultSet.getString("address"));
					user.setStandard(resultSet.getInt("standard"));
					user.setSchool(resultSet.getString("school"));
					user.setArea(resultSet.getString("location"));
					user.setCity(resultSet.getString("city"));
					user.setInterestedActivities(resultSet.getString("interested_activities"));
					user.setApplicableTimings(resultSet.getString("applicable_timings"));
					user.setPreference(resultSet.getString("preference"));
					user.setUpdatedAt(resultSet.getString("updated_at"));
					user.setStatus(resultSet.getInt("status"));
					userList.add(user);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"UserDAO : getUserDetailsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "UserDAO : getUserDetailsList() ended");
		return userList;
	}

	public boolean deleteUser(int userId/*, String beyondskoolId*/) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_USER");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			/*pstmt.setString(2, beyondskoolId);*/
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public boolean saveUser(/*String beyondSkoolId, */String parentName, String childName, int age, int standard,
			String gender, String email, String password, String fatherMobile, String motherMobile, String address, String school, String preference,String city,String area, String activity_list,
			String timings) throws DataImportException, AddressException, MessagingException {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		boolean mailSent = false;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_USER");
			pstmt = conn.prepareStatement(query);
			/*pstmt.setString(1, beyondSkoolId);*/
			pstmt.setString(1, parentName);
			pstmt.setString(2, childName);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, email);
			pstmt.setString(6, EncryptionUtil.encrypt(password));			
			pstmt.setString(7, fatherMobile);
			pstmt.setString(8, motherMobile);
			pstmt.setString(9, address);
			pstmt.setInt(10, standard);
			pstmt.setString(11, school);
			pstmt.setString(12, city);
			pstmt.setString(13, area);
			pstmt.setString(14, activity_list);
			pstmt.setString(15, timings);
			pstmt.setString(16, preference);
			pstmt.setTimestamp(17, CommonUtil.getTimeStamp());
			pstmt.setInt(18, 1);		
			pstmt.executeUpdate();
			mailSent = new MailUtil().sendEmailOnUserRegisterByAdmin(parentName, email, childName, age, school, activity_list,password);
			return true;
			
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}catch (InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException
				| NoSuchPaddingException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}		
	}
	
	public boolean updateParent(int userId, String parentName, String childName, int age, int standard,
			String gender, String email, String fatherMobile, String motherMobile, String address, String school, String preference,String city,String area, String activity_list,
			String timings) throws DataImportException, AddressException, MessagingException, BadPaddingException {
		Connection conn = null;		
		PreparedStatement pstmt = null;		
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_PARENT");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentName);
			pstmt.setString(2, childName);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, email);		
			pstmt.setString(6, fatherMobile);
			pstmt.setString(7, motherMobile);
			pstmt.setString(8, address);
			pstmt.setInt(9, standard);
			pstmt.setString(10, school);
			pstmt.setString(11, city);
			pstmt.setString(12, area);
			pstmt.setString(13, activity_list);
			pstmt.setString(14, timings);
			pstmt.setString(15, preference);
			pstmt.setTimestamp(16, CommonUtil.getTimeStamp());
			pstmt.setInt(17, 1);
			pstmt.setInt(18, userId);
			pstmt.executeUpdate();			
			return true;
			
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}		
	}
	
	public User getCompleteParentProfile(String parentEmail)
			throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() started");
		Connection conn = null;
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_PARENT_DETAILS_FOR_DASHBOARD");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {			
				while (resultSet.next()) {
					user = new User();
					user.setUserId(resultSet.getInt("user_id"));
					user.setParentName(resultSet.getString("parent_name"));
					user.setChildName(resultSet.getString("child_name"));
					user.setAge(resultSet.getInt("age"));
					user.setEmail(resultSet.getString("email"));
					user.setGender(resultSet.getString("gender"));
					user.setFatherMobileNo(resultSet.getString("father_mobile_no"));
					user.setMotherMobileNo(resultSet.getString("mother_mobile_no"));
					user.setAddress(resultSet.getString("address"));
					user.setStandard(resultSet.getInt("standard"));
					user.setSchool(resultSet.getString("school"));
					user.setCity(resultSet.getString("city"));
					user.setArea(resultSet.getString("location"));
					user.setInterestedActivities(resultSet.getString("interested_activities"));
					user.setApplicableTimings(resultSet.getString("applicable_timings"));
					user.setPreference(resultSet.getString("Preference"));
					user.setStatus(resultSet.getInt("status"));
				}
			}

		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"ActivityDAO : getCompleteCentreDetails() - Error " + e);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);			
		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"ActivityDAO : getCompleteCentreDetails() ended");
		return user;
	}
}
