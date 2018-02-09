package com.beyondskool.dataimport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.beyondskool.domain.Area;
import com.beyondskool.domain.City;
import com.beyondskool.exception.DataImportException;
import com.beyondskool.helper.DBConnection;
import com.beyondskool.util.CommonUtil;

public class AreaDAO extends BaseDAO{
	
	public AreaDAO() throws DataImportException {
		super();

	}
	public List<Area> getAreasList() throws DataImportException{
		DEBUG_LOGGER.log(Level.DEBUG, "AreaDAO : getAreasList() started");
		Connection conn = null;
		Area area = null;
		List<Area> areaList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_AREAS");
			pstmt = conn.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				areaList = new ArrayList<Area>();
				while (resultSet.next()) {
					area = new Area();
					area.setId(resultSet.getInt("AreaId"));
					area.setCityId(resultSet.getInt("CityId"));
					area.setAreaName(resultSet.getString("area_name"));
					area.setUpdatedAt(String.valueOf(resultSet.getTimestamp("updated_at")));
					area.setStatus(resultSet.getInt("status"));
					area.setCityName(resultSet.getString("city_name"));
					areaList.add(area);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"AreaDAO : getAreasList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		
		DEBUG_LOGGER.log(Level.DEBUG, "AreaDAO : getAreasList() ended");
		return areaList;
	}
	
	public boolean addArea(int cityId, String areaName) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("ADD_AREA");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, areaName);
			pstmt.setInt(2, cityId);			
			pstmt.setTimestamp(3,CommonUtil.getTimeStamp());
			pstmt.setInt(4, 1);
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
	
	public boolean deleteArea(int areaId)  throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("DELETE_AREA");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, areaId);			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}	

	public boolean updateArea(int cityId, int areaId, String areaName) throws DataImportException{
		Connection conn = null;		
		PreparedStatement pstmt = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("UPDATE_AREA");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, areaName);
			pstmt.setInt(2, cityId);
			pstmt.setInt(3, areaId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(pstmt, conn);
		}
		return false;
	}
	public List<Area> getAreasForCity(int cityId) throws DataImportException{
		Connection conn = null;
		Area area = null;
		List<Area> areaList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_AREAS_FOR_CITY");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cityId);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				areaList = new ArrayList<Area>();
				while (resultSet.next()) {
					area = new Area();
					area.setId(resultSet.getInt("AreaId"));
					area.setCityId(resultSet.getInt("CityId"));
					area.setAreaName(resultSet.getString("area_name"));
					area.setUpdatedAt(String.valueOf(resultSet.getTimestamp("updated_at")));
					area.setStatus(resultSet.getInt("status"));
					area.setCityName(resultSet.getString("city_name"));
					areaList.add(area);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"AreaDAO : getAreasList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		
		DEBUG_LOGGER.log(Level.DEBUG, "AreaDAO : getAreasList() ended");
		return areaList;
	}
	
	public List<Area> getAreasForCityName(String cityName) throws DataImportException{
		Connection conn = null;
		Area area = null;
		List<Area> areaList= null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String query = null;
			conn = DBConnection.getDbconnection();
			query = this.prop.getProperty("FETCH_AREAS_FOR_CITY_NAME");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cityName);
			resultSet = pstmt.executeQuery();
			if (null != resultSet) {
				areaList = new ArrayList<Area>();
				while (resultSet.next()) {
					area = new Area();
					area.setId(resultSet.getInt("AreaId"));
					area.setCityId(resultSet.getInt("CityId"));
					area.setAreaName(resultSet.getString("area_name"));
					area.setUpdatedAt(String.valueOf(resultSet.getTimestamp("updated_at")));
					area.setStatus(resultSet.getInt("status"));
					area.setCityName(resultSet.getString("city_name"));
					areaList.add(area);
				}
			}
		} catch (SQLException e) {
			ERROR_LOGGER.log(Level.ERROR,
					"AreaDAO : getAreasList() - Error " + e);
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(resultSet, pstmt, conn);
		}
		
		DEBUG_LOGGER.log(Level.DEBUG, "AreaDAO : getAreasList() ended");
		return areaList;
	}
}