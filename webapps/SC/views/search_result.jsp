<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과</title>
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
				<a href="#">
					<span>검색결과</span>
				</a>
			</li>
		</ul>
	</div>
	<jsp:include page="/views/includes/searchbox.jsp"></jsp:include>
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
			<c:choose>
				<c:when test="${requestScope.boardlist == null ||
					requestScope.boardlist.isEmpty()}">
					<tr class="board_lists">
						<td colspan="3">검색결과가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="board" varStatus="status" items="${requestScope.boardlist}">
						<tr class="board_lists">
							<td>${board.getNum()}</td>
							<td><a href="/Board.do?board_mode=announce_detail&num=${board.getNum()}">${board.getTitle()}</a></td>
							<td>${board.getPostdate()}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
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
			<c:choose>
				<c:when test="${requestScope.boardlist2 == null ||
					requestScope.boardlist2.isEmpty()}">
					<tr class="board_lists">
						<td colspan="4">검색결과가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="board" varStatus="status" items="${requestScope.boardlist2}">
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
				</c:otherwise>
			</c:choose>
			
		</table>
	</div>
	<div class="board_wrap">
		<div class="board_title">
			<h2>A/S 예약</h2>
		</div>
		<table class="board_table">
			<tr class="board_header">
				<th>번호</th>
				<th>제목</th>
				<th>신청일자</th>
				<th>방문일자</th>
				<th>상태</th>
			</tr>
			<c:choose>
				<c:when test="${requestScope.boardlist3 == null ||
					requestScope.boardlist3.isEmpty()}">
					<tr class="board_lists">
						<td colspan="5">검색결과가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="board" varStatus="status" items="${requestScope.boardlist3}">
						<tr class="board_lists">
							<td>${board.getNum()}</td>
							<td><a href="/Board.do?board_mode=reserve_detail&num=${board.getNum()}">${board.getTitle()}</a></td>
							<td>${board.getPostdate()}</td>
							<td>${board.getReservedate()}</td>
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
				</c:otherwise>
			</c:choose>
			

		</table>
	</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>