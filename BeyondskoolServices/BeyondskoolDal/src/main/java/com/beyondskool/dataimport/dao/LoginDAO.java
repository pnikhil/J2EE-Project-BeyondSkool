package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import org.apache.log4j.Level;

import com.beyondskool.dataimport.ExceptionConstants;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.EncryptionUtil;


// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class LoginDAO extends BaseDAO {

	/**
	 * Instantiates a new user dao.
	 * 
	 * @throws DataImportException
	 *             the data import exception
	 */
	public LoginDAO() throws DataImportException {
		super();
	}

	/**
	 * Login.
	 * 
	 * @param user
	 *            the user
	 * @param pass
	 *            the pass
	 * @param role
	 *            the role
	 * @return true, if successful
	 * @throws DataImportException
	 *             the data import exception
	 */
	public int login(String user, String pass, String role)
			throws DataImportException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = this.prop.getProperty("LOGIN_VALIDATION");
		try {
			con = DBConnection.getDbconnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, EncryptionUtil.encryptString(pass));
			/*ps.setString(3, role);*/
			rs = ps.executeQuery();
			if (rs.next())
			{
				return 1; 									
								 
			} else {
				return -1; // -1 for login failed
			}
		} catch (Exception ex) {
			ERROR_LOGGER.log(Level.ERROR,
					"UserDAO : login() - Error validating the user.");
			throw new DataImportException(ExceptionConstants.SQLEXCEPTION, ex);
		} finally {
			DBConnection.closeConnection(rs, ps, con);
		}
	}

	public int centreLogin(String username, String password, String userRole) throws DataImportException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = this.prop.getProperty("CENTRE_LOGIN_VALIDATION");
		try {
			con = DBConnection.getDbconnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, EncryptionUtil.encryptString(password));
			/*ps.setString(3, role);*/
			rs = ps.executeQuery();
			if (rs.next())
			{
			return 1; // return 1 for locked account										
								 
			} else {
				return -1; // -1 for login failed
			}
		} catch (Exception ex) {
			ERROR_LOGGER.log(Level.ERROR,
					"UserDAO : login() - Error validating the user.");
			throw new DataImportException(ExceptionConstants.SQLEXCEPTION, ex);
		} finally {
			DBConnection.closeConnection(rs, ps, con);
		}
	}

	public int parentLogin(String username, String password, String userRole) throws DataImportException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = this.prop.getProperty("PARENT_LOGIN_VALIDATION");
		try {
			con = DBConnection.getDbconnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, EncryptionUtil.encryptString(password));
			/*ps.setString(3, role);*/
			rs = ps.executeQuery();
			if (rs.next())
			{
			return 1; // return 1 for locked account										
								 
			} else {
				return -1; // -1 for login failed
			}
		} catch (Exception ex) {
			ERROR_LOGGER.log(Level.ERROR,
					"UserDAO : login() - Error validating the user.");
			throw new DataImportException(ExceptionConstants.SQLEXCEPTION, ex);
		} finally {
			DBConnection.closeConnection(rs, ps, con);
		}
	}

	/*
	public boolean forgetPassword(String userName, int secretQuestion, String secretAnswer) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "UserDAO: forgetPassword method started");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String unlockUserAccount = this.prop.getProperty("SECURITY_ANSWER_CHECK");
			conn = DBConnection.getDbconnection();
			pstmt = conn.prepareStatement(unlockUserAccount);
			pstmt.setString(1, userName);
			pstmt.setInt(2, secretQuestion);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				if (result!= null) {
					String ans = result.getString(1);
					if(ans.equalsIgnoreCase(secretAnswer)){
						return true;
					}					
				}
			}
		} catch (SQLException exception) {
			ERROR_LOGGER
					.log(Level.ERROR, "UserDAO : forgetPassword() - Error updating user details.");			
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		INFO_LOGGER.log(Level.INFO, "UserDAO: forgetPassword method ended");
		return false;
	}

	public boolean changePassword(String userName, String password) throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "UserDAO: changePassword method started");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String changePassword = this.prop.getProperty("CHANGE_PASSWORD");
			conn = DBConnection.getDbconnection();
			pstmt = conn.prepareStatement(changePassword);
			pstmt.setString(1, EncryptionUtil.encryptString(password));
			pstmt.setString(2, userName);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			ERROR_LOGGER
					.log(Level.ERROR, "UserDAO : forgetPassword() - Error updating user details.");			
		} finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		INFO_LOGGER.log(Level.INFO, "UserDAO: changePassword method ended");
		return false;
	}*/
}