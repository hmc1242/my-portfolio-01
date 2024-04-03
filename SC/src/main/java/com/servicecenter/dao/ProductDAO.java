package com.servicecenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.servicecenter.dto.ProductDTO;
import com.servicecenter.util.DBManager;

public class ProductDAO {
	private ProductDAO(){
		
	}
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public ArrayList<ProductDTO> selectProducts(String ptype){
		ArrayList<ProductDTO> productlist = new ArrayList<ProductDTO>();
		ProductDTO pdto = null;
		String sql = "SELECT * FROM sc_product sp, sc_product_type spt"
				+ " WHERE p_type_name = ? AND"
				+ " sp.p_type = spt.p_type"
				+ " ORDER BY p_id DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("selectproducts 변수 선언 지남");
		System.out.println(ptype);
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptype);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("rs 넘어감");
				pdto = new ProductDTO();
				pdto.setPid(rs.getString("p_id"));
				pdto.setPname(rs.getString("p_name"));
				pdto.setUnitprice(rs.getInt("unit_price"));
				pdto.setPdesc(rs.getString("p_desc"));
				pdto.setPicname(rs.getString("p_picname"));
				pdto.setPtype(rs.getString("p_type_name"));
				productlist.add(pdto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt, rs);
		return productlist;
	}
	
}
