package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.Booking;
import com.beyondskool.domain.Payment;
import com.beyondskool.domain.Wallet;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;

public class WalletDAO extends BaseDAO {

	public WalletDAO() throws DataImportException {
		super();		
	}

	/*public List<Booking> getBookingsList() throws DataImportException{
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
	*/
	
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

	public Wallet retrieveWalletInfoForParent(String parentEmail) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "BookingDAO : getBookingsList() started");
		Connection conn = null;
		Wallet wallet = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_WALLET_DATA_FOR_PARENT");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				
				while (resultSet.next()) {
					wallet = new Wallet();
					wallet.setTotalPurchase(resultSet.getInt("total_purchase"));
					wallet.setBalanceRemaining(resultSet.getInt("balance_remaining"));
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
		return wallet;
	}
}