package com.servicecenter.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servicecenter.dao.UserDAO;
import com.servicecenter.dto.UserDTO;

/*
 * 계정 처리 서블릿
 * - 로그인, 로그아웃
 * - 게시물 조회 시 참조
 */
@WebServlet("/Account.do")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		writer.println("<script src='/js/alert.js'></script>");
		
		if(request.getParameter("user_mode").equals("login")) {
			writer.println("<script>access_alert('login');</script>");
		}else if(request.getParameter("user_mode").equals("logout")) {
			session.removeAttribute("userid");
			session.removeAttribute("userpwd");
			session.removeAttribute("ismanager");
			writer.println("<script>success('logout');</script>");
		}else if(request.getParameter("user_mode").equals("withdraw")) {
			if(session.getAttribute("userid") != null) {
				String userid = (String)session.getAttribute("userid");
				UserDAO udao = UserDAO.getInstance();
				if(udao.deleteUser(userid)) {
					session.removeAttribute("userid");
					session.removeAttribute("userpwd");
					session.removeAttribute("ismanager");
					writer.println("<script>success('withdraw');</script>");
				}else {
					writer.println("<script>failed('withdraw');</script>");
				}
				
			}else {
				writer.println("<script>access_alert('wrong');</script>");
			}
		}
			
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		UserDAO udao = UserDAO.getInstance();
		UserDTO udto = null;
		String userid = null;
		String userpwd = null;
		writer.println("<script src='/js/alert.js'></script>");
		
		String userMode = (String)request.getParameter("user_mode");
		switch(userMode) {
			case "login":
				userid = (String)request.getParameter("userid");
				userpwd = (String)request.getParameter("userpwd");
				udto = udao.selectUser(userid);
				
				if(udto.getUserid() == null) {
					writer.println("<script>login_alert('id');</script>");
				}
				else if(!userpwd.equals(udto.getUserpwd())) {
					writer.println("<script>login_alert('pwd');</script>");
				}else {
					session.setAttribute("userid", userid);
					session.setAttribute("userpwd", userpwd);
					session.setAttribute("ismanager", udto.getIsmanager());
					session.setAttribute("userloc", udto.getUserloc());
					System.out.println("userlic 저장완료 : " + session.getAttribute("userloc"));
					writer.println("<script>success('login');</script>");
				}
				break;
			case "register":
				udto = new UserDTO();
				udto.setUserid(request.getParameter("userid"));
				udto.setUserpwd(request.getParameter("userpwd"));
				udto.setUseremail(request.getParameter("useremail"));
				udto.setUsertel(request.getParameter("usertel"));
				udto.setUserloc(request.getParameter("userloc"));
				
				if(udao.insertUser(udto)) {
					writer.println("<script>success('register');</script>");
				}else {
					writer.println("<script>failed('register');</script>");
				}
				break;
			case "update":
				udto = new UserDTO();
				udto.setUserid(request.getParameter("userid"));
				udto.setUserpwd(request.getParameter("userpwd"));
				udto.setUseremail(request.getParameter("useremail"));
				udto.setUsertel(request.getParameter("usertel"));
				udto.setUserloc(request.getParameter("userloc"));
				if(session.getAttribute("ismanager") == null) {
					udto.setIsmanager(0);
				}else {
					udto.setIsmanager(1);
				}
				
				if(udao.insertUser(udto)) {
					writer.println("<script>success('register');</script>");
				}else {
					writer.println("<script>failed('register');</script>");
				}
				break;
		}//switch
		writer.close();
	}//dopost

}
