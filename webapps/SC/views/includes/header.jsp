<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<body>
	<header>
		<div id="logo">
			<a href="home"><img src="/img/logo.png"></a>
		</div>
		<nav id="top_menu">
			<ul>
				<li><a href="/products" id="header_product">제품</a></li>
				<li><a href="/Board.do?board_mode=ask" id="header_ask">1대1 문의</a></li>
				<li><a href="/Board.do?board_mode=reserve" id="header_reserve">A/S 예약</a></li>
				<li><a href="/Board.do?board_mode=announce" id="header_announce">공지사항</a></li>
			</ul>
		</nav>
		<div id="sub_menu">
			<ul>
				<c:choose>
					<c:when test="${sessionScope.userid ne null}">
						<li><a href="/mypage"><img src="/img/user-solid.svg"></a></li>
						<li><a href="/Account.do?user_mode=logout"><img src="/img/arrow-right-from-bracket-solid.svg"></a></li>
						<c:choose>
							<c:when test="${sessionScope.ismanager == 0}">
								<br><sub>${sessionScope.userid}님 반갑습니다.</sub>
							</c:when>
							<c:otherwise>
								<br><sub>관리자님 반갑습니다.</sub>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<li><a href="/login">로그인</a></li>
						<li> / </li>
						<li><a href="/register">회원가입</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</header>
</body>
</html>