package com.beyondskool.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.exception.DataImportException;

public class DBConnection {
	public static final Logger INFO_LOGGER = Logger.getLogger("infologging");

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");

	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	static final ResourceBundle DB_RESOURCE_BUNDLE = ResourceBundle
			.getBundle("DB");

	public static Connection getDbconnection() {
		{
			String connectionDecider = (DB_RESOURCE_BUNDLE
					.getString("JNDI_CONNECTION")).trim();

			Connection conn = null;

			if (connectionDecider.equalsIgnoreCase("TRUE")) {
				javax.naming.Context ctx1;
				String jndiCntxLookup = DB_RESOURCE_BUNDLE
						.getString("JNDI_CNTX_LOOKUP");
				try {
					ctx1 = new javax.naming.InitialContext();
					javax.sql.DataSource ds = (javax.sql.DataSource) ctx1
							.lookup(jndiCntxLookup);
					conn = ds.getConnection();
				} catch (NamingException | SQLException e) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in getting connection",
									e);
					throw new RuntimeException(e);
				}
			} else {
				String driverClassName = DB_RESOURCE_BUNDLE
						.getString("DRIVER_CLASSNAME");
				String dbUrl = DB_RESOURCE_BUNDLE.getString("DB_URL");
				String dbName = DB_RESOURCE_BUNDLE.getString("DB_NAME");
				String userName = DB_RESOURCE_BUNDLE.getString("DB_USER");
				String password = DB_RESOURCE_BUNDLE.getString("DB_PASSWORD");

				try {
					Class.forName(driverClassName);
					conn = DriverManager.getConnection(dbUrl + dbName,
							userName, password);
					conn.setAutoCommit(true);
				} catch (ClassNotFoundException | SQLException exception) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in getting connection",
									exception);
					throw new RuntimeException(exception);
				}
			}
			return conn;
		}
	}

	public static void closeConnection(Connection conn)
			throws DataImportException {
		{
			if (conn != null)
				try {
					conn.close();
					conn = null;
				} catch (SQLException exception) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}

	public static void closeConnection(Statement ps, Connection conn)
			throws DataImportException {
		{
			if (conn != null)
				try {
					if (ps != null) {
						ps.close();
						ps = null;
					}
					closeConnection(conn);
				} catch (SQLException exception) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}

	public static void closeConnection(ResultSet rs, Statement ps)
			throws DataImportException {
		{

			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (rs != null)
					rs.close();
				rs = null;

			} catch (SQLException exception) {
				ERROR_LOGGER
						.log(Level.ERROR,
								"DBConnection : getDbconnection() - Error in closing connection");
				throw new DataImportException(exception);
			}
		}

	}

	public static void closeConnection(ResultSet rs, Connection conn)
			throws DataImportException {
		{
			if (conn != null)
				try {
					if (rs != null)
						rs.close();
					rs = null;
					closeConnection(conn);
				} catch (SQLException exception) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}

	public static void closeConnection(ResultSet rs, Statement ps,
			Connection conn) throws DataImportException {
		{
			if (conn != null)
				try {
					if (rs != null)
						rs.close();
					rs = null;
					closeConnection(ps, conn);
				} catch (SQLException exception) {
					ERROR_LOGGER
							.log(Level.ERROR,
									"DBConnection : getDbconnection() - Error in closing connection");
					throw new DataImportException(exception);
				}
		}

	}

}