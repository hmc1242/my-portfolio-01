package com.servicecenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.servicecenter.dto.UserDTO;
import com.servicecenter.util.DBManager;

public class UserDAO {
	private UserDAO(){
		
	}
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	public UserDTO selectUser(String userid){
		UserDTO result = new UserDTO();
		String sql = "SELECT * FROM sc_user WHERE user_id = ?";
		Connection conn = null;
		PreparedStatement rstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			rstmt = conn.prepareStatement(sql);
			rstmt.setString(1, userid);
			rs = rstmt.executeQuery();
			if(rs.next()) {
				result.setUserid(rs.getString("user_id"));
				result.setUserpwd(rs.getString("user_pwd"));
				result.setUseremail(rs.getString("user_email"));
				result.setUsertel(rs.getString("user_tel"));
				result.setUserloc(rs.getString("user_loc"));
				result.setIsmanager(rs.getInt("is_manager"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, rstmt, rs);
		return result;
	}
	
	public boolean updateUser(UserDTO udto){
		boolean result = false;
		String sql = "UPDATE sc_user "
				+ "SET user_pwd = ?,"
				+ "user_email = ?,"
				+ "user_tel = ?,"
				+ "user_loc = ?"
				+ "WHERE user_id = ?";
		Connection conn = null;
		PreparedStatement rstmt = null;
		
		try {
			conn = DBManager.getConnection();
			rstmt = conn.prepareStatement(sql);
			rstmt.setString(1, udto.getUserpwd());
			rstmt.setString(2, udto.getUseremail());
			rstmt.setString(3, udto.getUsertel());
			rstmt.setString(4, udto.getUserloc());
			rstmt.setString(5, udto.getUserid());
			rstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, rstmt);
		result = true;
		return result;
	}
	
	public boolean insertUser(UserDTO udto){
		boolean result = false;
		String sql = "INSERT INTO sc_user "
				+ "VALUES(?, ?, ?, ?, ?, 0)";
		Connection conn = null;
		PreparedStatement rstmt = null;
		
		try {
			conn = DBManager.getConnection();
			rstmt = conn.prepareStatement(sql);
			rstmt.setString(1, udto.getUserid());
			rstmt.setString(2, udto.getUserpwd());
			rstmt.setString(3, udto.getUseremail());
			rstmt.setString(4, udto.getUsertel());
			rstmt.setString(5, udto.getUserloc());
			rstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, rstmt);
		result = true;
		return result;
	}
	
	public boolean deleteUser(String userid){
		boolean result = false;
		String sql = "DELETE sc_user "
				+ "WHERE user_id = ?,"
				+ "is_manager = 0";
		Connection conn = null;
		PreparedStatement rstmt = null;
		
		try {
			conn = DBManager.getConnection();
			rstmt = conn.prepareStatement(sql);
			rstmt.setString(1, userid);
			rstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, rstmt);
		result = true;
		return result;
	}
}
