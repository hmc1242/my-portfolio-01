package com.servicecenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.servicecenter.dto.AnnounceDTO;
import com.servicecenter.dto.AskDTO;
import com.servicecenter.dto.BoardDTO;
import com.servicecenter.dto.ReserveDTO;
import com.servicecenter.util.DBManager;

public class BoardDAO {
	private BoardDAO(){
		
	}
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public ArrayList<BoardDTO> selectBoards(String flag){
		ArrayList<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String sql = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = null;
					sql = "SELECT * FROM sc_announce ORDER BY num DESC";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						adto = new AnnounceDTO();
						adto.setNum(rs.getInt("num"));
						adto.setTitle(rs.getString("title"));
						adto.setContent(rs.getString("content"));
						adto.setPostdate(rs.getDate("post_date"));
						boardlist.add(adto);
					}
					break;
				case "ask":
					AskDTO askdto = null;
					sql = "SELECT * FROM sc_ask ORDER BY num DESC";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						askdto = new AskDTO();
						askdto.setNum(rs.getInt("num"));
						askdto.setTitle(rs.getString("title"));
						askdto.setContent(rs.getString("content"));
						askdto.setPostdate(rs.getDate("post_date"));
						askdto.setUserid(rs.getString("user_id"));
						askdto.setState(rs.getInt("state"));
						askdto.setAnswer(rs.getString("answer"));
						boardlist.add(askdto);
					}
					break;
				case "reserve":
					ReserveDTO rdto = null;
					sql = "SELECT * FROM sc_reserve ORDER BY num DESC";
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						rdto =  new ReserveDTO();
						rdto.setNum(rs.getInt("num"));
						rdto.setTitle(rs.getString("title"));
						rdto.setContent(rs.getString("content"));
						rdto.setPostdate(rs.getDate("post_date"));
						rdto.setReservedate(rs.getDate("reserve_date"));
						rdto.setUserid(rs.getString("user_id"));
						rdto.setState(rs.getInt("state"));
						rdto.setReserveloc(rs.getString("reserve_loc"));
						boardlist.add(rdto);
					}
					break;
				default:
					break;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, stmt, rs);
		return boardlist;
	}
	
	
	
	public ArrayList<BoardDTO> selectBoards(String flag, String userid){
		ArrayList<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "ask":
					AskDTO askdto = null;
					sql = "SELECT * FROM sc_ask WHERE user_id = ? ORDER BY num DESC";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						askdto = new AskDTO();
						askdto.setNum(rs.getInt("num"));
						askdto.setTitle(rs.getString("title"));
						askdto.setContent(rs.getString("content"));
						askdto.setPostdate(rs.getDate("post_date"));
						askdto.setUserid(rs.getString("user_id"));
						askdto.setState(rs.getInt("state"));
						askdto.setAnswer(rs.getString("answer"));
						boardlist.add(askdto);
					}
					break;
				case "reserve":
					ReserveDTO rdto = null;
					sql = "SELECT * FROM sc_reserve WHERE user_id = ? ORDER BY num DESC";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						rdto =  new ReserveDTO();
						rdto.setNum(rs.getInt("num"));
						rdto.setTitle(rs.getString("title"));
						rdto.setContent(rs.getString("content"));
						rdto.setPostdate(rs.getDate("post_date"));
						rdto.setReservedate(rs.getDate("reserve_date"));
						rdto.setUserid(rs.getString("user_id"));
						rdto.setState(rs.getInt("state"));
						rdto.setReserveloc(rs.getString("reserve_loc"));
						boardlist.add(rdto);
					}
					break;
				default:
					break;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt, rs);
		return boardlist;
	}
	
	public ArrayList<BoardDTO> selectBoards(String flag, String searchword, String userid){
		ArrayList<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = null;
					sql = "SELECT * FROM sc_announce WHERE title LIKE '%'||?||'%' ORDER BY num DESC";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchword);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						adto = new AnnounceDTO();
						adto.setNum(rs.getInt("num"));
						adto.setTitle(rs.getString("title"));
						adto.setContent(rs.getString("content"));
						adto.setPostdate(rs.getDate("post_date"));
						boardlist.add(adto);
					}
					break;
				case "ask":
					AskDTO askdto = null;
					if(userid == null) {
						sql = "SELECT * FROM sc_ask WHERE title LIKE '%'||?||'%' ORDER BY num DESC";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchword);
					}
					else {
						sql = "SELECT * FROM sc_ask WHERE title LIKE '%'||?||'%' AND user_id = ? ORDER BY num DESC";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchword);
						pstmt.setString(2, userid);
					}

					rs = pstmt.executeQuery();
					while(rs.next()) {
						askdto = new AskDTO();
						askdto.setNum(rs.getInt("num"));
						askdto.setTitle(rs.getString("title"));
						askdto.setContent(rs.getString("content"));
						askdto.setPostdate(rs.getDate("post_date"));
						askdto.setUserid(rs.getString("user_id"));
						askdto.setState(rs.getInt("state"));
						askdto.setAnswer(rs.getString("answer"));
						boardlist.add(askdto);
					}
					break;
				case "reserve":
					ReserveDTO rdto = null;
					if(userid == null) {
						sql = "SELECT * FROM sc_reserve WHERE title LIKE '%'||?||'%' ORDER BY num DESC";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchword);
					}
					else {
						sql = "SELECT * FROM sc_reserve WHERE title LIKE '%'||?||'%' AND user_id = ? ORDER BY num DESC";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchword);
						pstmt.setString(2, userid);
					}
					rs = pstmt.executeQuery();
					while(rs.next()) {
						rdto =  new ReserveDTO();
						rdto.setNum(rs.getInt("num"));
						rdto.setTitle(rs.getString("title"));
						rdto.setContent(rs.getString("content"));
						rdto.setPostdate(rs.getDate("post_date"));
						rdto.setReservedate(rs.getDate("reserve_date"));
						rdto.setUserid(rs.getString("user_id"));
						rdto.setState(rs.getInt("state"));
						rdto.setReserveloc(rs.getString("reserve_loc"));
						boardlist.add(rdto);
					}
					break;
				default:
					break;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt, rs);
		return boardlist;
	}
	
	public BoardDTO selectBoard(String flag, int num){
		String sql = null;
		BoardDTO bdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = new AnnounceDTO();
					sql = "SELECT * FROM sc_announce WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						adto.setNum(rs.getInt("num"));
						adto.setTitle(rs.getString("title"));
						adto.setContent(rs.getString("content"));
						adto.setPostdate(rs.getDate("post_date"));
					}
					bdto = adto;
					break;
				case "ask":
					AskDTO askdto = new AskDTO();
					sql = "SELECT * FROM sc_ask WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						askdto.setNum(rs.getInt("num"));
						askdto.setTitle(rs.getString("title"));
						askdto.setContent(rs.getString("content"));
						askdto.setPostdate(rs.getDate("post_date"));
						askdto.setUserid(rs.getString("user_id"));
						askdto.setState(rs.getInt("state"));
						askdto.setAnswer(rs.getString("answer"));
					}
					bdto = askdto;
					break;
				case "reserve":
					ReserveDTO rdto = new ReserveDTO();
					sql = "SELECT * FROM sc_reserve WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						rdto.setNum(rs.getInt("num"));
						rdto.setTitle(rs.getString("title"));
						rdto.setContent(rs.getString("content"));
						rdto.setPostdate(rs.getDate("post_date"));
						rdto.setReservedate(rs.getDate("reserve_date"));
						rdto.setUserid(rs.getString("user_id"));
						rdto.setState(rs.getInt("state"));
						rdto.setReserveloc(rs.getString("reserve_loc"));
					}
					bdto = rdto;
					break;
				default:
					break;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt, rs);
		return bdto;
	}
	
	public BoardDTO selectBoard(String flag, String picname){
		String sql = null;
		BoardDTO bdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = new AnnounceDTO();
					sql = "SELECT * FROM sc_announce WHERE picname = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, picname);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						adto.setNum(rs.getInt("num"));
						adto.setTitle(rs.getString("title"));
						adto.setContent(rs.getString("content"));
						adto.setPostdate(rs.getDate("post_date"));
						bdto = adto;
					}
					break;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt, rs);
		return bdto;
	}
	
	public int updateBoard(String flag, BoardDTO bdto){
		String sql = null;
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = (AnnounceDTO)bdto;
					sql = "UPDATE sc_announce SET title = ?, content = ? WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, adto.getTitle());
					pstmt.setString(2, adto.getContent());
					pstmt.setInt(3, adto.getNum());
					break;
				case "ask":
					AskDTO askdto = (AskDTO)bdto;
					sql = "UPDATE sc_ask SET title = ?, content = ? WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, askdto.getTitle());
					pstmt.setString(2, askdto.getContent());
					pstmt.setInt(3, askdto.getNum());
					break;
				case "reserve":
					ReserveDTO rdto = (ReserveDTO)bdto;
					sql = "UPDATE sc_reserve SET title = ?, content = ?, reserve_date = TO_DATE(?, 'YYYY-MM-DD'), reserve_loc = ? WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, rdto.getTitle());
					pstmt.setString(2, rdto.getContent());
					pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(rdto.getReservedate()));
					pstmt.setString(4, rdto.getReserveloc());
					pstmt.setInt(5, rdto.getNum());
					break;
				case "answer":
					AskDTO askadto = (AskDTO)bdto;
					sql = "UPDATE sc_ask SET state = 1, answer = ?  WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, askadto.getAnswer());
					pstmt.setInt(2, askadto.getNum());
					break;
				case "visited":
					ReserveDTO rvdto = (ReserveDTO)bdto;
					sql = "UPDATE sc_reserve SET state = 1  WHERE num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rvdto.getNum());
					break;
				default:
					break;
			}
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt);
		return count;
	}
	
	public int insertBoard(String flag, BoardDTO bdto){
		String sql = "";
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					AnnounceDTO adto = (AnnounceDTO)bdto;
					sql = "INSERT INTO sc_announce VALUES (sc_announce_seq.NEXTVAL, ?, ?, sysdate, null)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, adto.getTitle());
					pstmt.setString(2, adto.getContent());
					break;
				case "ask":
					AskDTO askdto = (AskDTO)bdto;
					sql = "INSERT INTO sc_ask VALUES (sc_ask_seq.NEXTVAL, ?, ?, sysdate, ?, 0, null)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, askdto.getTitle());
					pstmt.setString(2, askdto.getContent());
					pstmt.setString(3, askdto.getUserid());
					break;
				case "reserve":
					ReserveDTO rdto = (ReserveDTO)bdto;
					sql = "INSERT INTO sc_reserve VALUES (sc_reserve_seq.NEXTVAL, ?, ?, sysdate, TO_DATE(?, 'YYYY-MM-DD'), ?, 0, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, rdto.getTitle());
					pstmt.setString(2, rdto.getContent());
					pstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(rdto.getReservedate()));
					System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(rdto.getReservedate()));
					pstmt.setString(4, rdto.getUserid());
					pstmt.setString(5, rdto.getReserveloc());
					System.out.println("DAO case 진입");	
					break;
				default:
					break;
			}
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt);
		return count;
	}
	
	public int deleteBoard(String flag, int num){
		String sql = null;
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(num);
		try {
			conn = DBManager.getConnection();
			switch(flag) {
				case "announce":
					sql = "Delete sc_announce WHERE num = ?";
					break;
				case "ask":
					sql = "Delete sc_ask WHERE num = ?";
					break;
				case "reserve":
					sql = "Delete sc_reserve WHERE num = ?";
					break;
				default:
					break;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBManager.close(conn, pstmt);
		return count;
	}

	
}
