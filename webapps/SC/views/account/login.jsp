<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
	<div id="login_wrap">
		<form method="post" action="Account.do">
		<input type="hidden" name="user_mode" value="login">
		<h2>로그인</h2>
		<ul>
			<li>
				<input type="text" id="userid" class="login_inputs" name="userid" placeholder="아이디">
			</li>
			<li>
				<input type="password" id="userpwd" class="login_inputs" name="userpwd" placeholder="비밀번호">
			</li>
			<li>
				<input type="submit" id="login_button" class="login_inputs" value="로그인">
			</li>
			<li>
				<input type="button" id="register_button" class="login_inputs" value="회원가입" onclick="PopupDetect()">
			</li>
		</ul>
		</form>
	</div>
	<div id="bottom_copyright">
		<span>Copyright ©ELECTRONICS SERVICE CO., Ltd. All rights reserved.</span>
	</div>
	
	<script>
    function PopupDetect() {
      if(opener == null)
    	  location.href="/register";
      else{
    	  window.close();
		  opener.location.href="/register";
      }
      true;
    }
  	</script>
</body>
</html>