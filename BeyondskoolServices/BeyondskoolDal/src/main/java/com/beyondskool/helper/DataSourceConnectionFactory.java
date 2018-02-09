package com.beyondskool.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.exception.DataImportException;

public class DataSourceConnectionFactory {

	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");
	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	/**
	 * getDataSourceConnection.
	 * 
	 * @param lookupString
	 *            the header
	 * 
	 * @return con, if successful
	 * @throws Exception
	 */
	public static Connection getDataSourceConnection(String lookUpString)
			throws DataImportException {
		INFO_LOGGER.log(Level.INFO, "Inside getDataSourceConnection method");
		Connection con = null;
		final String driverClassName = "sun.jdbc.odbc.JdbcOdbcDriver";
		String dataSourceName = "";

		if (lookUpString.equalsIgnoreCase("Phoenix Informer")) {
			dataSourceName = "Phoenix_Informer";
		}
		
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection("jdbc:odbc:" + dataSourceName);
		} catch (Exception exception) {
			ERROR_LOGGER.log(Level.ERROR, "DataSourceConnectionFactory : getDataSourceConnection() - Error getting ODBC driver connection");
			throw new DataImportException(exception);
		}
		return con;
	}

	/**
	 * closeConnection.
	 * 
	 * @param conn
	 *            the connection
	 * @throws DataImportException 
	 */
	public static void closeConnection(Connection conn) throws DataImportException {
		{
			if (conn != null)
				try {
					conn.close();
					conn = null;
				} catch (SQLException exception) {
					ERROR_LOGGER.log(Level.ERROR, "DataSourceConnectionFactory : closeConnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}

	/**
	 * closeConnection.
	 * 
	 * @param rset
	 *            the rs
	 * @param psmt
	 *            the ps
	 * @param conn
	 *            the connection
	 * @throws DataImportException 
	 */
	//modified for closing the resultset, prepared statement
	public static void closeConnection(ResultSet rset, Statement psmt,
			 Connection conn) throws DataImportException {
		{
			if (conn != null)
				try {

					if (rset != null) {
						rset.close();
						rset = null;
					}
					if (psmt != null) {
						psmt.close();
						psmt = null;
					}
					//closeConnection(conn);
				} catch (SQLException exception) {
					ERROR_LOGGER.log(Level.ERROR, "DataSourceConnectionFactory : closeConnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}
}
