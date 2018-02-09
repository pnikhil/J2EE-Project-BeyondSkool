package com.beyondskool.dataimport.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;

import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;
import com.beyondskool.util.EncryptionUtil;
import com.beyondskool.util.MailUtil;

public class SignupDAO extends BaseDAO {

	public SignupDAO() throws DataImportException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*public static String memberIdGenerator() throws SQLException {
		StringBuffer ch = new StringBuffer("BS2017");
		DecimalFormat df = new DecimalFormat("0000");
		Connection conn = null;		
		PreparedStatement pstmt = null;
		String query = null;
		query = this.prop.getProperty("MAX_USER_INDEX");
		PreparedStatement ps = 
		ResultSet rs = ps.executeQuery();
		rs.next();
		int val = rs.getInt(1);
		ch.append(df.format(val+1));
		return ch.toString();
	}*/

	public boolean centreRegister(String name, String email, String password, String contact, int ImageDirId) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("REGISTER_CENTRE");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, EncryptionUtil.encrypt(password));
			pstmt.setString(4, contact);
			pstmt.setTimestamp(5, CommonUtil.getTimeStamp());
			pstmt.setString(6,"Inactive");
			pstmt.setInt(7, ImageDirId);
			pstmt.executeUpdate();			
			return true;
			
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}		
	}
	
	public boolean userRegister(String name, String email, String password, String contact, String childName, int childAge, String childSchool) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		boolean mailSent = false;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("REGISTER_USER");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, EncryptionUtil.encrypt(password));
			pstmt.setString(4, contact);
			pstmt.setString(5, childName);
			pstmt.setInt(6, childAge);
			pstmt.setString(7, childSchool);
			pstmt.setTimestamp(8, CommonUtil.getTimeStamp());
			pstmt.setInt(9,2);
			pstmt.executeUpdate();
			try {
				mailSent = new MailUtil().sendEmailOnUserRegister(name, email, childName, childAge, childSchool,password);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mailSent;
		} catch (SQLException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
	}

	public boolean centreEmailExists(String email) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("CHECK_EMAIL");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				while (resultSet.next()) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public boolean centreParentEmailExists(String email) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("CHECK_EMAIL_PARENT");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				while (resultSet.next()) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}
}