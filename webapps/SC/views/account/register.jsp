<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/register.css">
<script src="/js/alert.js"></script>
</head>
<body>
	<div id="register_wrap">
		<form name="frm" method="post" action="/Account.do">
			<input type="hidden" name="user_mode" value="register">
			<h2>회원가입</h2>
			<ul>
				<li>
					<input type="text" id="userid" class="register_inputs" name="userid" placeholder="아이디" required>
				</li>
				<li>
					<input type="password" id="userpwd" class="register_inputs" name="userpwd" placeholder="비밀번호" required>
				</li>
				<li>
					<input type="password" id="userpwdr" class="register_inputs" name="userpwdr" placeholder="비밀번호 확인" required>
				</li>
				<li>
					<input type="email" id="useremail" class="register_inputs" name="useremail" placeholder="이메일">
				</li>
				<li>
					<input type="text" id="usertel" class="register_inputs" name="usertel" placeholder="전화번호">
				</li>
				<li>
					<input type="text" id="userloc" class="register_inputs" name="userloc" placeholder="주소" size="50">
				</li>
				<li>
					<input type="submit" id="register_button" class="register_inputs" value="회원가입" onclick="return register_alert()">
				</li>
			</ul>
		</form>
	</div>
	<div id="bottom_copyright">
		<span>Copyright ©ELECTRONICS SERVICE CO., Ltd. All rights reserved.</span>
	</div>
</body>
</html>