<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1 문의 작성</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/board_detail.css">
<script src="/js/alert.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.board == null}">
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
				<a href="/Board.do?board_mode=announce">
					<span>공지사항</span>
				</a>
			</li>
		</ul>
	</div>
	<div id="board_detail_title"><h2>공지사항 상세</h2></div>
		<div id="board_detail_wrap">
			<table id="board_detail_table">
				<tr>
					<td class="td_header">글번호</td>
					<td>
						<p id="writer">${requestScope.board.getNum()}</p>
					</td>
					<td class="td_header">작성일자</td>
					<td>
						<p id="post_date">${requestScope.board.getPostdate()}</p>
					</td>
				</tr>
				<tr>
					<td class="td_header">제목</td>
					<td colspan="4">
						<p id="title">${requestScope.board.getTitle()}</p>
					</td>
				</tr>	
				<tr>
					<td class="td_header">내용</td>
					<td colspan="4">
						<p id="content">${requestScope.board.getContent()}</p>
					</td>
				</tr>
			</table>
			<div id="button_box">
				<c:choose>
					<c:when test="${sessionScope.ismanager == 1}">
						<input type="button" class="update_button" value="수정하기" 
							onclick="location.href='Board.do?board_mode=announce_update&num=${requestScope.board.getNum()}'">
						<input type="button" class="update_button" value="삭제하기" 
							onclick="if(ask('announce_delete')) location.href='Board.do?board_mode=announce_delete&num=${requestScope.board.getNum()}'">
					</c:when>
				</c:choose>
			</div>
		</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>