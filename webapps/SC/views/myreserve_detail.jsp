<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 예약 상세</title>
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
				<a href="/Board.do?board_mode=reserve">
					<span>나의 예약</span>
				</a>
			</li>
		</ul>
	</div>
	<div id="board_detail_title"><h2>나의 예약 상세</h2></div>
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
								<span id="answer_ready">방문예정</span>
							</c:when>
							<c:otherwise>
								<span id="answer_complete">방문완료</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="td_header">작성일자</td>
					<td>
						<p id="post_date">${requestScope.board.getPostdate()}</p>
					</td>
					<td class="td_header">예약일자</td>
					<td>
						<p id="reserve_date">${requestScope.board.getReservedate()}</p>
					</td>
				</tr>
				<tr>
					<td class="td_header">방문주소</td>
					<td colspan="3">
						<p id="reserve_loc">${requestScope.board.getReserveloc()}</p>
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
			</table>
			<div id="button_box">
				<c:choose>
					<c:when test="${sessionScope.userid == requestScope.board.getUserid() && sessionScope.ismanager == 0}">
						<input type="button" class="update_button" value="수정하기" 
							onclick="location.href='Board.do?board_mode=reserve_update&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${sessionScope.userid == requestScope.board.getUserid() || sessionScope.ismanager == 1}">
						<input type="button" class="update_button" value="삭제하기"
							onclick="if(ask('reserve_delete')) location.href='Board.do?board_mode=reserve_delete&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${sessionScope.ismanager == 1 && requestScope.board.getState() == 0}">
						<input type="button" class="update_button" value="방문처리"
							onclick="if(ask('visited')) location.href='Board.do?board_mode=visited&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
			</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>