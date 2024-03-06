<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/board.css">
<link rel="stylesheet" type="text/css" href="/css/searchbar_mini.css">
<link rel="stylesheet" type="text/css" href="/css/unfold.css">
</head>
<body>
	<jsp:include page="/views/includes/header.jsp"></jsp:include>
	<div id="path_bar">
		<ul>
			<li>
				<a href="/home">
					<i id="home_icon"></i>
				</a>
			</li>
			<li class="path_bar_text_list">
				<a href="/Board.do?board_mode=announce">
					<span>공지사항</span>
				</a>
			</li>
		</ul>
	</div>
	<div class="board_wrap">
		<div class="board_title">
			<h2>공지사항</h2>
		</div>
		<table class="board_table">
			<tr class="board_header">
				<th>번호</th>
				<th>제목</th>
				<th>작성일자</th>
			</tr>
			<c:set var="pagenum" value="${1}"/>
			<c:forEach var="board" varStatus="status" items="${requestScope.boardlist}">
				<c:choose>
					<c:when test="${status.count%10 == 0}">
						<c:set var="pagenum" value="${pagenum+1}"/>
					</c:when>
				</c:choose>
				<tr class="board_lists">
					<td>${board.getNum()}</td>
					<td><a href="/Board.do?board_mode=announce_detail&num=${board.getNum()}">${board.getTitle()}</a></td>
					<td>${board.getPostdate()}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 
			<c:choose>
				<c:when test="${pagenum>1}">
					<div id="unfold_bar">
						<i id="unfold_down_icon"></i>
						<span>더보기</span>
					</div>
				</c:when>
			</c:choose>
		 -->
		 <c:choose>
			<c:when test="${sessionScope.ismanager == 1}">
				<input type="button" value="작성하기" class="board_insert" onclick="location.href='Board.do?board_mode=announce_form'">
			</c:when>
		</c:choose>
	</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>