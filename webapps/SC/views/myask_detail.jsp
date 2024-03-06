<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1 문의</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/board_detail.css">
<script src="/js/alert.js"></script>
</head>
<body>
	<%
		if(session.getAttribute("userid") == null){
			response.sendRedirect("/Account.do?user_mode=login");
		}
	%>
	<c:choose>
		<c:when test="${requestScope.board == null || requestScope.board.getUserid() != sessionScope.userid && sessionScope.ismanager != 1}">
			<script>access_alert('wrong');</script>
		</c:when>
	</c:choose>
	<jsp:include page="/views/includes/header.jsp"></jsp:include>
	<div id="path_bar">
		<ul>
			<li>
				<a href="/home">
					<i id="home_icon"></i>
				</a>
			</li>
			<li class="path_bar_text_list">
				<a href="/mypage">
					<span>마이페이지</span>
				</a>
			</li>
			<li class="path_bar_text_list">
				<a href="/Board.do?board_mode=ask">
					<span>1대1 문의</span>
				</a>
			</li>
		</ul>
	</div>
	<div id="board_detail_title"><h2>1대1 문의 상세</h2></div>
		<div id="board_detail_wrap">
			<table id="board_detail_table">
				<tr>
					<td class="td_header">작성자</td>
					<td>
						<p id="writer">${requestScope.board.getUserid()}</p>
					</td>
					<td class="td_header">상태</td>
					<td colspan="3">
						<c:choose>
							<c:when test="${board.getState()<=0}">
								<span id="answer_ready">답변대기</span>
							</c:when>
							<c:otherwise>
								<span id="answer_complete">답변완료</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="td_header">작성일자</td>
					<td colspan="3">
						<p id="post_date">${requestScope.board.getPostdate()}</p>
					</td>
				</tr>
				<tr>
					<td class="td_header">제목</td>
					<td colspan="3">
						<p id="title">${requestScope.board.getTitle()}</p>
					</td>
				</tr>	
				<tr>
					<td class="td_header">내용</td>
					<td colspan="3">
						<p id="content">${requestScope.board.getContent()}</p>
					</td>
				</tr>
				<c:choose>
					<c:when test="${requestScope.board.getState() == 1 && sessionScope.ismanager == 0}">
						<tr>
							<td class="td_header">답변</td>
							<td colspan="3">
								<p id="answer_content">${requestScope.board.getAnswer()}</p>
							</td>
						</tr>
					</c:when>
				</c:choose>
			</table>
			<c:choose>
				<c:when test="${sessionScope.ismanager == 1}">
					<form name="frm" id="frm" method="post" 
						action="/Board.do?board_mode=answer&num=${requestScope.board.getNum()}">
						<table id="answer_table">
							<tr>
								<td class="td_header">답변</td>
							</tr>
							<tr>
								<td>
									<textarea name="answer" id="answer">${requestScope.board.getAnswer()}</textarea>
								</td>
							</tr>
							<tr>
								<td>
									<input type="submit" id="answer_button" value="문의답변하기" onclick="return board_alert('answer')">
								</td>
							</tr>
						</table>
					</form>
				</c:when>
			</c:choose>
			<div id="button_box">
				<c:choose>
					<c:when test="${sessionScope.userid == requestScope.board.getUserid() && sessionScope.ismanager == 0}">
						<input type="button" class="update_button" value="수정하기" 
							onclick="location.href='Board.do?board_mode=ask_update&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${sessionScope.userid == requestScope.board.getUserid() || sessionScope.ismanager == 1}">
						<input type="button" class="update_button" value="삭제하기" 
							onclick="if(ask('ask_delete')) location.href='Board.do?board_mode=ask_delete&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
			</div>
		</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>