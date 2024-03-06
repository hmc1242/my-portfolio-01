<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/mypage.css">
<script src='/js/alert.js'></script>
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
		</ul>
	</div>
	<div id="mypage_wrap">
		<div id="mypage_title">
			<h2>마이페이지</h2>
		</div>
		<div class="mypage_lines">
			<c:choose>
				<c:when test="${sessionScope.ismanager == 0}">
					<div id="my_ask" class="mypage_boxes" onclick="location.href='/Board.do?board_mode=ask'">
						<span>나의 문의</span>
					</div>
					<div id="my_reserve" class="mypage_boxes" onclick="location.href='/Board.do?board_mode=reserve'">
						<span>나의 예약</span>
					</div>
				</c:when>
				<c:otherwise>
					<div id="my_ask" class="mypage_boxes" onclick="location.href='/Board.do?board_mode=ask'">
						<span>문의 관리</span>
					</div>
					<div id="my_reserve" class="mypage_boxes" onclick="location.href='/Board.do?board_mode=reserve'">
						<span>예약 관리</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="mypage_lines">
			<c:choose>
				<c:when test="${sessionScope.ismanager == 0}">
					<div id="user_edit" class="mypage_boxes" onclick="location.href='/Account.do?user_mode=useredit'">
						<span>회원정보 수정</span>
					</div>
					<div id="user_delete" class="mypage_boxes" onclick="ask('withdraw');">
						<span>회원 탈퇴</span>
					</div>
				</c:when>
				<c:otherwise>
					<div id="announce" class="mypage_boxes" onclick="location.href='/Board.do?board_mode=announce'">
						<span>공지사항 관리</span>
					</div>
					<div id="user_edit" class="mypage_boxes" onclick="location.href='/Account.do?user_mode=useredit'">
						<span>관리자정보 수정</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="logout_box" onclick="location.href='/Account.do?user_mode=logout'">
			<span>로그아웃</span>
		</div>
	</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>