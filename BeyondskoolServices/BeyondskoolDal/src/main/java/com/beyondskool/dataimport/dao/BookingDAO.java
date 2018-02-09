package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Level;

import com.beyondskool.domain.Area;
import com.beyondskool.domain.Booking;
import com.beyondskool.domain.Payment;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.MailUtil;

public class BookingDAO extends BaseDAO {

	public BookingDAO() throws DataImportException {
		super();		
	}

	public List<Booking> getBookingsList() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() started");
		Connection conn = null;
		Booking booking = null;
		List<Booking> bookingList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_BOOKING_DATA");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				bookingList = new ArrayList<Booking>();
				while (resultSet.next()) {
					booking = new Booking();
					booking.setId(resultSet.getInt("book_id"));
					booking.setBookingDate(resultSet.getString("date"));
					booking.setAlreadyAttended(resultSet.getString("attended"));
					booking.setActivityCost(resultSet.getInt("amount"));
					booking.setCentreName(resultSet.getString("center_name"));
					booking.setUserEmail(resultSet.getString("email"));
					booking.setUserName(resultSet.getString("parent_name"));
					booking.setActivityName(resultSet.getString("activites_name"));
					bookingList.add(booking);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"BookingDAO : getBookingsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}		
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() ended");
		return bookingList;
	}
	
	public List<Booking> getBookingsListForParent(String parentEmail) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() started");
		Connection conn = null;
		Booking booking = null;
		List<Booking> bookingList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_BOOKING_DATA_FOR_PARENT");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				bookingList = new ArrayList<Booking>();
				while (resultSet.next()) {
					booking = new Booking();
					booking.setId(resultSet.getInt("book_id"));
					booking.setBookingDate(resultSet.getString("date"));
					booking.setAlreadyAttended(resultSet.getString("attended"));
					booking.setActivityCost(resultSet.getInt("amount"));
					booking.setCentreName(resultSet.getString("center_name"));
					booking.setActivityName(resultSet.getString("activites_name"));
					booking.setCentreAddress(resultSet.getString("address"));
					booking.setCentrePhoneNo(resultSet.getString("contact_no"));
					bookingList.add(booking);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"BookingDAO : getBookingsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
			
		}		
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() ended");
		return bookingList;
	}

	public List<Booking> getBookingsListForCentre(String centreEmail) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() started");
		Connection conn = null;
		Booking booking = null;
		List<Booking> bookingList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_BOOKING_DATA_FOR_CENTRE");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				bookingList = new ArrayList<Booking>();
				while (resultSet.next()) {
					booking = new Booking();
					booking.setId(resultSet.getInt("book_id"));
					booking.setBookingDate(resultSet.getString("date"));
					booking.setAlreadyAttended(resultSet.getString("attended"));
					booking.setActivityCost(resultSet.getInt("amount"));
					booking.setUserEmail(resultSet.getString("email"));
					booking.setUserName(resultSet.getString("parent_name"));
					booking.setChildName(resultSet.getString("child_name"));
					booking.setFatherMobileNo(resultSet.getString("father_mobile_no"));
					booking.setArea(resultSet.getString("location"));
					booking.setActivityName(resultSet.getString("activites_name"));
					bookingList.add(booking);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"BookingDAO : getBookingsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}		
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() ended");
		return bookingList;
	}

	public boolean changeBookingStatus(String choice, int bookingId) throws DataImportException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("CHANGE_BOOKING_STATUS");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, choice);
			pstmt.setInt(2, bookingId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public int bookAClass(String bookingSlot, int centreActivityId, int bookingCost, String userEmail) throws DataImportException, MessagingException{
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() started");
		Connection conn = null;	
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		String query = null;
		String query2 = null;
		String query3 = null;
		String query4 = null;
		String query5 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		int remainingBalance = 0;
		String centreEmail = null, address=null,centreName =null,contactNo = null,ownerName=null,parentName =null,activityName=null;
		int userId = 0;
		try {			
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("CHECK_PARENT_BALANCE");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userEmail);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
					remainingBalance = resultSet.getInt("balance_remaining");
					userId = resultSet.getInt("user_id");
			}
			if(remainingBalance < bookingCost){
					return -1; // -1 when balance is not sufficient to book the class
			}
			else{
				
				query2 = this.prop.getProperty("BOOK_SLOT_FOR_PARENT");
				pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, bookingSlot);
				pstmt2.setInt(2,centreActivityId);
				pstmt2.setInt(3, userId);
				pstmt2.setTimestamp(4, CommonUtil.getTimeStamp());
				pstmt2.setInt(5, 1);
				pstmt2.executeUpdate();
				pstmt2.close();
				
				query3 = this.prop.getProperty("DEDUCT_AMOUNT_FROM_WALLET");
				pstmt3 = conn.prepareStatement(query3);
				pstmt3.setInt(1, bookingCost);
				pstmt3.setInt(2,userId);				
				pstmt3.executeUpdate();
				pstmt3.close();
				
				query5 = this.prop.getProperty("REDUCE_SLOT");
				pstmt5 = conn.prepareStatement(query5);
				pstmt5.setInt(1, centreActivityId);				
				pstmt5.executeUpdate();
				pstmt5.close();	
				
				query4 = this.prop.getProperty("GET_CENTRE_DETAILS_FOR_ACTIVITY_MAIL");
				pstmt4 = conn.prepareStatement(query4);
				pstmt4.setInt(1, userId);
				pstmt4.setInt(2, centreActivityId);
				pstmt4.setString(3, bookingSlot);
				resultSet2 = pstmt4.executeQuery();
				while (resultSet2.next()) {
						centreEmail = resultSet2.getString("center_email");
						address = resultSet2.getString("address");
						centreName = resultSet2.getString("center_name");
						contactNo = resultSet2.getString("contact_no");
						ownerName = resultSet2.getString("owner_name");
						parentName = resultSet2.getString("parent_name");
						activityName = resultSet2.getString("activites_name");
				}
				new MailUtil().sendEmailForBooking(centreEmail,userEmail,bookingSlot,bookingCost,parentName,ownerName,contactNo,centreName,address,activityName);
				pstmt4.close();
			}
			return 1; // success
		} catch (SQLException e) {
			e.printStackTrace();
			return -2; //something went wrong
		} finally {
			DBConnection.closeConnection(resultSet,pstmt, conn);
			DBConnection.closeConnection(resultSet2,pstmt2);
		}
	}
}