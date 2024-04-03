package com.servicecenter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servicecenter.dao.BoardDAO;
import com.servicecenter.dto.AnnounceDTO;
import com.servicecenter.dto.AskDTO;
import com.servicecenter.dto.BoardDTO;
import com.servicecenter.dto.ReserveDTO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
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
		BoardDAO bdao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = null;
		BoardDTO bdto = null;
		int count = 0;
		RequestDispatcher dispatcher = null;
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		writer.println("<script src='/js/alert.js'></script>");
		String boardmode = (String)request.getParameter("board_mode");
		switch(boardmode) {
			case "announce":
				boardlist = bdao.selectBoards("announce");
				dispatcher = request.getRequestDispatcher("/announce");
				request.setAttribute("boardlist", boardlist);
				break;
			case "ask":
				if(session.getAttribute("ismanager") != null) {
					if(((Integer)session.getAttribute("ismanager")).intValue() == 1 )
						boardlist = bdao.selectBoards("ask");
					else if(((Integer)session.getAttribute("ismanager")).intValue() == 0 ){
						boardlist = bdao.selectBoards("ask", (String)session.getAttribute("userid"));
					}
				}
				dispatcher = request.getRequestDispatcher("/myask");
				request.setAttribute("boardlist", boardlist);
				break;
			case "reserve":
				if(session.getAttribute("ismanager") != null) {
					if(((Integer)session.getAttribute("ismanager")).intValue() == 1 )
						boardlist = bdao.selectBoards("reserve");
					else if(((Integer)session.getAttribute("ismanager")).intValue() == 0 ){
						boardlist = bdao.selectBoards("reserve", (String)session.getAttribute("userid"));
					}
				}
				dispatcher = request.getRequestDispatcher("/myreserve");
				request.setAttribute("boardlist", boardlist);
				break;
			case "announce_form":
				dispatcher = request.getRequestDispatcher("/announceform");
				break;
			case "ask_form":
				dispatcher = request.getRequestDispatcher("/myaskform");
				break;
			case "reserve_form":
				dispatcher = request.getRequestDispatcher("/myreserveform");
				break;
			case "announce_detail":
				if(request.getParameter("picname") != null) {
					bdto = bdao.selectBoard("announce", request.getParameter("picname"));
					System.out.println(bdto);
				}
				else
					bdto = bdao.selectBoard("announce", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/announcedetail");
				request.setAttribute("board", bdto);
				break;
			case "ask_detail":
				bdto = bdao.selectBoard("ask", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/myaskdetail");
				request.setAttribute("board", bdto);
				break;
			case "reserve_detail":
				bdto = bdao.selectBoard("reserve", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/myreservedetail");
				request.setAttribute("board", bdto);
				break;
			case "announce_update":
				bdto = bdao.selectBoard("announce", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/announceform");
				request.setAttribute("board", bdto);
				request.setAttribute("isupdate", true);
				break;
			case "ask_update":
				bdto = bdao.selectBoard("ask", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/myaskform");
				request.setAttribute("board", bdto);
				request.setAttribute("isupdate", true);
				break;
			case "reserve_update":
				bdto = bdao.selectBoard("reserve", Integer.parseInt(request.getParameter("num")));
				dispatcher = request.getRequestDispatcher("/myreserveform");
				request.setAttribute("board", bdto);
				request.setAttribute("isupdate", true);
				break;
			case "announce_delete":
				count = bdao.deleteBoard("announce", Integer.parseInt(request.getParameter("num")));
				if(count <= 0) {
					writer.println("<script>failed('announce_delete');</script>");
				}else {
					writer.println("<script>success('announce_delete');</script>");
				}
				return;
			case "ask_delete":
				count = bdao.deleteBoard("ask", Integer.parseInt(request.getParameter("num")));
				if(count <= 0) {
					writer.println("<script>failed('ask_delete');</script>");
				}else {
					writer.println("<script>success('ask_delete');</script>");
				}
				return;
			case "reserve_delete":
				count = bdao.deleteBoard("reserve", Integer.parseInt(request.getParameter("num")));
				if(count <= 0) {
					writer.println("<script>failed('reserve_delete');</script>");
				}else {
					writer.println("<script>success('reserve_delete');</script>");
				}
				return;
			case "search":
				System.out.println("search 진입");
				List<BoardDTO> boardlist2 = null;
				List<BoardDTO> boardlist3 = null;
				if(session.getAttribute("ismanager") != null) {
					if(((Integer)session.getAttribute("ismanager")).intValue() == 1 ) {
						boardlist = bdao.selectBoards("announce", request.getParameter("search_text"), null);
						boardlist2 = bdao.selectBoards("ask", request.getParameter("search_text"), null);
						boardlist3 = bdao.selectBoards("reserve", request.getParameter("search_text"), null);
					}
					else if(((Integer)session.getAttribute("ismanager")).intValue() == 0 ){
						boardlist = bdao.selectBoards("announce", request.getParameter("search_text"), null);
						boardlist2 = bdao.selectBoards("ask", request.getParameter("search_text"), (String)session.getAttribute("userid"));
						boardlist3 = bdao.selectBoards("reserve", request.getParameter("search_text"), (String)session.getAttribute("userid"));
					}
				}else {
					boardlist = bdao.selectBoards("announce", request.getParameter("search_text"), null);
				}
				dispatcher = request.getRequestDispatcher("/search");
				request.setAttribute("boardlist", boardlist);
				request.setAttribute("boardlist2", boardlist2);
				request.setAttribute("boardlist3", boardlist3);
				request.setAttribute("search_text", request.getParameter("search_text"));
				break;
			case "visited":
				ReserveDTO rvdto = new ReserveDTO();
				rvdto.setNum(Integer.parseInt(request.getParameter("num")));
				count = bdao.updateBoard("visited", rvdto);
				if(count <= 0) {
					writer.println("<script>failed('visited');</script>");
				}else {
					writer.println("<script>success('visited');</script>");
				}
				return;
		}
		dispatcher.forward(request, response);
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
		BoardDAO bdao = BoardDAO.getInstance();
		int count = 0;
		
		String rdatestr = null;
		SimpleDateFormat sdf = null;
		Date rdate = null;
		writer.println("<script src='/js/alert.js'></script>");
		
		String boardmode = (String)request.getParameter("board_mode");
		switch(boardmode) {
			case "announce_insert":
				AnnounceDTO adto = new AnnounceDTO();
				adto.setTitle(request.getParameter("post_title"));
				adto.setContent(request.getParameter("post_content"));
				count = bdao.insertBoard("announce", adto);
				if(count <= 0) {
					writer.println("<script>failed('announce_insert');</script>");
				}else {
					writer.println("<script>success('announce_insert');</script>");
				}
				break;
			case "ask_insert":
				AskDTO askdto = new AskDTO();
				askdto.setTitle(request.getParameter("post_title"));
				askdto.setContent(request.getParameter("post_content"));
				askdto.setUserid((String)session.getAttribute("userid"));
				count = bdao.insertBoard("ask", askdto);
				if(count <= 0) {
					writer.println("<script>failed('ask_insert');</script>");
				}else {
					writer.println("<script>success('ask_insert');</script>");
				}
				break;
			case "reserve_insert":
				ReserveDTO rdto = new ReserveDTO();
				try {
					rdatestr = request.getParameter("reserve_date");
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					rdate = sdf.parse(rdatestr);
					System.out.println("rdate : "+rdate);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				rdto.setTitle(request.getParameter("post_title"));
				rdto.setContent(request.getParameter("post_content"));
				rdto.setReservedate(rdate);
				rdto.setUserid((String)session.getAttribute("userid"));
				rdto.setReserveloc(request.getParameter("post_content"));
				count = bdao.insertBoard("reserve", rdto);
				System.out.println("insert board 통과");
				System.out.println(count);
				if(count <= 0) {
					writer.println("<script>failed('reserve_insert');</script>");
				}else {
					writer.println("<script>success('reserve_insert');</script>");
				}
				break;
			case "announce_update":
				AnnounceDTO audto = new AnnounceDTO();
				audto.setNum(Integer.parseInt(request.getParameter("num")));
				audto.setTitle(request.getParameter("post_title"));
				audto.setContent(request.getParameter("post_content"));
				count = bdao.updateBoard("announce", audto);
				if(count <= 0) {
					writer.println("<script>failed('announce_update');</script>");
				}else {
					writer.println("<script>success('announce_update');</script>");
				}
				break;
			case "ask_update":
				System.out.println("ask update 진입");
				AskDTO askudto = new AskDTO();
				askudto.setNum(Integer.parseInt(request.getParameter("num")));
				askudto.setTitle(request.getParameter("post_title"));
				askudto.setContent(request.getParameter("post_content"));
				askudto.setUserid((String)session.getAttribute("userid"));
				count = bdao.updateBoard("ask", askudto);
				if(count <= 0) {
					System.out.println("ask update if 진입");
					writer.println("<script>failed('ask_update');</script>");
				}else {
					System.out.println("ask update else 진입");
					writer.println("<script>success('ask_update');</script>");
				}
				break;
			case "reserve_update":
				ReserveDTO rudto = new ReserveDTO();
				try {
					rdatestr = request.getParameter("reserve_date");
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					rdate = sdf.parse(rdatestr);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				rudto.setNum(Integer.parseInt(request.getParameter("num")));
				rudto.setTitle(request.getParameter("post_title"));
				rudto.setContent(request.getParameter("post_content"));
				rudto.setReservedate(rdate);
				rudto.setUserid((String)session.getAttribute("userid"));
				rudto.setReserveloc(request.getParameter("reserve_loc"));
				count = bdao.updateBoard("reserve", rudto);
				if(count <= 0) {
					writer.println("<script>failed('reserve_update');</script>");
				}else {
					writer.println("<script>success('reserve_update');</script>");
				}
				break;
			case "answer":
				AskDTO askadto = new AskDTO();
				askadto.setNum(Integer.parseInt(request.getParameter("num")));
				askadto.setAnswer(request.getParameter("answer"));
				count = bdao.updateBoard("answer", askadto);
				if(count <= 0) {
					writer.println("<script>failed('answer');</script>");
				}else {
					writer.println("<script>success('answer');</script>");
				}
				break;
		}
		
		
	}

}
