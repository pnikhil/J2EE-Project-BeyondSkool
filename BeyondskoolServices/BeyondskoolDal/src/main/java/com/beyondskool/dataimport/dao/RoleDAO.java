package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.beyondskool.dataimport.ExceptionConstants;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.vo.RoleVO;

public class RoleDAO extends BaseDAO {

	public RoleDAO() throws DataImportException {
		super();

	}
	public List<RoleVO> getRolesForLogin() throws DataImportException {

		INFO_LOGGER.log(Level.INFO, "RoleDAO: getRoles method started");

		List<RoleVO> roleList = new ArrayList<RoleVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet roleInfoRS = null;

		try {
			conn = DBConnection.getDbconnection();
			String retrieveRolesQuery = this.prop
					.getProperty("FETCH_ROLE_RECORDS_FOR_LOGIN");
			pstmt = conn.prepareStatement(retrieveRolesQuery);
			roleInfoRS = pstmt.executeQuery();
			RoleVO role = null;			
			while (roleInfoRS.next()) {
				role = new RoleVO();
				role.setRoleName(roleInfoRS.getString("role_name"));				
				roleList.add(role);				
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR, e.getMessage(), e);
			throw new DataImportException(ExceptionConstants.SQLEXCEPTION, e);
		} finally {

			DBConnection.closeConnection(roleInfoRS, pstmt, conn);
		}
		INFO_LOGGER.log(Level.INFO, "RoleDAO: getRoles method ended");
		INFO_LOGGER.log(Level.INFO, "RoleDAO: getRoles method ended");

		return roleList;
	}


}
