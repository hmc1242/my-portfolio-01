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
<link rel="stylesheet" type="text/css" href="/css/board_insert_form.css">
<script src="/js/alert.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.ismanager == 0}">
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
			<li class="path_bar_text_list">
				<a href="#">
					<c:choose>
						<c:when test="${requestScope.board == null}">
							<span>공지사항 작성</span>
						</c:when>
						<c:otherwise>
							<span>공지사항 수정</span>
						</c:otherwise>
					</c:choose>	
				</a>
			</li>
		</ul>
	</div>
	<div id="board_insert_title">
		<c:choose>
			<c:when test="${requestScope.board == null}">
				<h2>공지사항 작성</h2>
			</c:when>
			<c:otherwise>
				<h2>공지사항 수정</h2>
			</c:otherwise>
		</c:choose>	
	</div>
	<c:choose>
		<c:when test="${requestScope.isupdate}">
			<form method="post" action="Board.do" name="frm">
			<input type="hidden" name="board_mode" value="announce_update">
			<input type="hidden" name="num" value="${requestScope.board.getNum()}">
		</c:when>
		<c:otherwise>
			<form method="post" action="Board.do" name="frm">
			<input type="hidden" name="board_mode" value="announce_insert">
		</c:otherwise>
	</c:choose>
		<div id="board_insert_wrap">
			<table id="board_insert_table">
				<tr>
					<td>제목</td><tr>
					<td>
						<c:choose>
							<c:when test="${requestScope.board == null}">
								<input type="text" name="post_title" id="post_title">
							</c:when>
							<c:otherwise>
								<input type="text" name="post_title" id="post_title" value="${requestScope.board.getTitle()}">
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
				<tr>
					<td>내용</td><tr>
					<td>
						<c:choose>
							<c:when test="${requestScope.board == null}">
								<textarea name="post_content" id="post_content"></textarea>
							</c:when>
							<c:otherwise>
								<textarea name="post_content" id="post_content">${requestScope.board.getContent()}</textarea>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>	
			</table>
			<div id="button_box">
				<c:choose>
					<c:when test="${requestScope.board == null}">
						<input type="submit" id="submit_button" value="작 성" onclick="return board_alert('announce')">
					</c:when>
					<c:otherwise>
						<input type="submit" id="submit_button" value="수 정" onclick="return board_alert('announce')">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>