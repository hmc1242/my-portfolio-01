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
<link rel="stylesheet" type="text/css" href="/css/board.css">
<link rel="stylesheet" type="text/css" href="/css/searchbar_mini.css">
</head>
<body>
	<%
		if(session.getAttribute("userid") == null){
			response.sendRedirect("/Account.do?user_mode=login");
		}
	%>
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
	<div class="board_wrap">
		<div class="board_title">
			<h2>1대1 문의</h2>
		</div>
		<table class="board_table">
			<tr class="board_header">
				<th>번호</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>상태</th>
			</tr>
			<c:forEach var="board" varStatus="status" items="${requestScope.boardlist}">
				<tr class="board_lists">
					<td>${board.getNum()}</td>
					<td><a href="/Board.do?board_mode=ask_detail&num=${board.getNum()}">${board.getTitle()}</a></td>
					<td>${board.getPostdate()}</td>
					<td>
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
			</c:forEach>
		</table>
		<c:choose>
			<c:when test="${sessionScope.ismanager == 0}">
				<input type="button" value="문의하기" class="board_insert" onclick="location.href='Board.do?board_mode=ask_form'">
			</c:when>
		</c:choose>
	</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>