package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.beyondskool.domain.City;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;

public class CityDAO extends BaseDAO{
	
	public CityDAO() throws DataImportException {
		super();

	}

	public List<City> getCitiesList() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "CityDAO : getCitiesList() started");
		Connection conn = null;
		City city = null;
		List<City> cityList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_CITIES");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				cityList = new ArrayList<City>();
				while (resultSet.next()) {
					city = new City();
					city.setCityName(resultSet.getString("city_name"));
					city.setId(resultSet.getInt("id"));
					city.setStatus(resultSet.getInt("status"));
					city.setUpdatedAt(String.valueOf(resultSet.getTimestamp("updated_at")));
					cityList.add(city);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"CityDAO : getCitiesList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		DEBUG_LOGGER.log(Level.DEBUG, "CityDAO : getCitiesList() ended");
		return cityList;
	}

	public boolean addCity(String cityName) throws DataImportException {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_CITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cityName);
			pstmt.setTimestamp(2,CommonUtil.getTimeStamp());
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public boolean deleteCity(int cityId) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_CITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cityId);			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

	public boolean updateCity(String cityName, int cityId) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_CITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cityName);
			pstmt.setInt(2, cityId);			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}

}
