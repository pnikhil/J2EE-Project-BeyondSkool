package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import com.beyondskool.domain.Area;
import com.beyondskool.domain.Payment;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;

public class PaymentDAO extends BaseDAO {

	public PaymentDAO() throws DataImportException {
		super();		
	}

	public List<Payment> getPaymentsList() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() started");
		Connection conn = null;
		Payment payment = null;
		List<Payment> paymentList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_PAYMENT_DATA");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				paymentList = new ArrayList<Payment>();
				while (resultSet.next()) {
					payment = new Payment();
					payment.setId(resultSet.getInt("id"));
					payment.setAmount(resultSet.getInt("amount"));
					payment.setStatus(resultSet.getString("status"));
					payment.setPackageName(resultSet.getString("package"));
					payment.setPaymentMethod(resultSet.getString("method"));
					payment.setContactNo(resultSet.getString("contact"));
					payment.setDate(resultSet.getString("createdAt"));
					payment.setUserName(resultSet.getString("parent_name"));
					payment.setCity(resultSet.getString("city"));
					payment.setEmail(resultSet.getString("email"));
					paymentList.add(payment);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"PaymentDAO : getPaymentsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() ended");
		return paymentList;
	}

	public List<Payment> getPaymentsListForParent(String parentEmail) throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() started");
		Connection conn = null;
		Payment payment = null;
		List<Payment> paymentList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_PAYMENT_DATA_FOR_PARENT");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentEmail);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				paymentList = new ArrayList<Payment>();
				while (resultSet.next()) {
					payment = new Payment();
					payment.setId(resultSet.getInt("id"));
					payment.setAmount(resultSet.getInt("amount"));
					payment.setStatus(resultSet.getString("status"));
					payment.setPackageName(resultSet.getString("package"));
					payment.setPaymentMethod(resultSet.getString("method"));
					payment.setContactNo(resultSet.getString("contact"));
					payment.setDate(resultSet.getString("createdAt"));
					paymentList.add(payment);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"PaymentDAO : getPaymentsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() ended");
		return paymentList;
	}

	public boolean capturePayment(List<Payment> paymentInfo) throws DataImportException{		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Connection conn2 = null;
		PreparedStatement pstmt2 = null;
		int amount = 0;
		String query = null;
		String query2 = null;
		ResultSet resultSet = null;
		String userEmail = null;
		try {			
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_PAYMENT_DATA");
			pstmt = conn.prepareStatement(query);
			for(Payment payment : paymentInfo){				
				pstmt.setString(1, payment.getPaymentId());
				amount = payment.getAmount();
				pstmt.setInt(2, amount);
				pstmt.setString(3,payment.getCurrency());
				pstmt.setString(4,payment.getMethod());
				//pstmt.setInt(5, payment.getCaptured());
				pstmt.setString(5, payment.getStatus());
				pstmt.setString(6, payment.getPackageName());
				userEmail = payment.getEmail();
				pstmt.setString(7, userEmail);
				pstmt.setString(8, payment.getContactNo());						
				pstmt.setString(9, payment.getCreatedAt());
				pstmt.executeUpdate();
				if(payment.getStatus().equalsIgnoreCase("failed")){
					return false;
				}
				else{
					query2 = this.prop.getProperty("ADD_TO_PARENT_WALLET");
					pstmt2 = conn.prepareStatement(query2);
					pstmt2.setInt(1, amount);
					pstmt2.setInt(2, amount);
					pstmt2.setString(3, userEmail);
					pstmt2.executeUpdate();
					pstmt2.close();
					return true;
				}
			}
			
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR, "PaymentDAO : getPaymentsList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);			
		}
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() ended");
		return false;
	}
}