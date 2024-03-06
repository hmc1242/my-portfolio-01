<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 변경</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/useredit.css">
</head>
<body>
	<%
		if(session.getAttribute("userid") == null){
			response.sendRedirect("/Account.do?user_mode=login");
		}
	%>
	<c:choose>
		<c:when test="${sessionScope.user == NULL}">
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
				<a href="/mypage/useredit">
					<span>회원정보수정</span>
				</a>
			</li>
		</ul>
	</div>
	<form method="post" action="/Account.do" name="frm">
		<input type="hidden" name="user_mode" value="update">
		<div id="useredit_wrap">
			<div id="useredit_title">
				<h2>회원 정보 수정</h2>
				<p><sup>*</sup>(필수입력)</p>
			</div>
			<table id="useredit_table">
				<tr>
					<td>비밀번호*</td>
					<td>
						<input type="password" name="userpwd" required>
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인*</td>
					<td>
						<input type="password" name="userpwdr" required>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" name="useremail" size="40" placeholder="@를 반드시 포함시켜주세요." value="${requestScope.user.getUseremail()}">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" name="usertel" placeholder="000-0000-0000" value="${requestScope.user.getUsertel()}">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<input type="text" name="userloc" size="50" value="${requestScope.user.getUserloc()}">
					</td>
				</tr>
			</table>
			<input type="submit" value="변경" onclick="return register_alert()">
		</div>
	</form>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>