package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.beyondskool.domain.Review;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;

public class ReviewsDAO extends BaseDAO {

	public ReviewsDAO() throws DataImportException {
		super();		
	}

	public List<Review> getCentreReviews(String centreName) throws DataImportException {
		DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() started");
		Connection conn = null;
		Review review = null;
		List<Review> centreReview= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("GET_CENTRE_REVIEW");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, centreName);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				centreReview = new ArrayList<Review>();
				while (resultSet.next()) {
					review = new Review();
					review.setReview(resultSet.getString("review"));
					review.setRating(resultSet.getInt("rating"));
					review.setUserName(resultSet.getString("parent_name"));
					review.setCentreName(resultSet.getString("center_name"));
					review.setDate(resultSet.getString("updated_at"));
					centreReview.add(review);
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
		return centreReview;
	}

	public int addReview(String parentEmail, int centreId, String review, int rating, String currentTime) throws DataImportException{
		Connection conn = null;
		int parentId = 0;
		PreparedStatement pstmt = null, pstmt2 = null,pstmt3 = null;
		String query = null, query2 = null, query3=null;
		ResultSet resultSet = null, resultSet3 = null;
		try {			
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("CHECK_IF_REVIEW_EXISTS");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, parentEmail);
			pstmt.setInt(2, centreId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {				
				while (resultSet.next()) {
					return 2;
				}
			}
			
			query3 = this.prop.getProperty("GET_USER_ID");
			pstmt3 = conn.prepareStatement(query3);
			pstmt3.setString(1, parentEmail);			
			resultSet3 = pstmt3.executeQuery();
			if (null != resultSet3) {				
				while (resultSet3.next()) {
					parentId = resultSet3.getInt("user_id");
				}
			}
			
			query2 = this.prop.getProperty("ADD_PARENT_REVIEW");
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, review);
			pstmt2.setInt(2, rating);
			pstmt2.setInt(3, parentId);
			pstmt2.setString(4, currentTime);
			pstmt2.setInt(5, centreId);
			pstmt2.executeUpdate();
			pstmt2.close();
			return 1;
		
		} catch (SQLException e) {			
			e.printStackTrace();
			return 0;
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
			DBConnection.closeConnection(resultSet3, pstmt3);
		}
	}

	public List<Review> getReviews() throws DataImportException{		
			DEBUG_LOGGER.log(Level.DEBUG, "PaymentDAO : getPaymentsList() started");
			Connection conn = null;
			Review review = null;
			List<Review> reviewList= null;
			PreparedStatement pstmt = null;
			ResultSet resultSet = null;
			try {
				String query = null;
				conn = DBConnection.getDbconnection();
				query = this.prop.getProperty("GET_ALL_REVIEWS");
				pstmt = conn.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				if (null != resultSet) {
					reviewList = new ArrayList<Review>();
					while (resultSet.next()) {
						review = new Review();
						review.setId(resultSet.getInt("id"));
						review.setRating(resultSet.getInt("rating"));
						review.setCentreName(resultSet.getString("center_name"));
						review.setUserName(resultSet.getString("parent_name"));
						review.setReview(resultSet.getString("review"));
						review.setDate(resultSet.getString("updated_at"));
						reviewList.add(review);
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
			return reviewList;
		
	}

	public boolean deleteReview(int reviewId) throws DataImportException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_REVIEW");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewId);
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