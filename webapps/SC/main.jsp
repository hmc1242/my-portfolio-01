<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자제품 서비스센터</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/searchbox.css">
<script src="/js/swipebox.js"></script>
</head>
<body>
	<jsp:include page="/views/includes/header.jsp"></jsp:include>
	<div id="swipe_banner">
		<div id="swipe_img1" class="swipe_imgs"
			onclick="location.href='Board.do?board_mode=announce_detail&picnum=1'"></div>
		<div id="swipe_img2" class="swipe_imgs" 
			onclick="location.href='Board.do?board_mode=announce_detail&picnum=2'"></div>
		<div id="swipe_img3" class="swipe_imgs" 
			onclick="location.href='Board.do?board_mode=announce_detail&picnum=3'"></div>
			<div id="left_arrow_btn" class="arrow_btn" onclick="swipe_prev()">
				<img src="/img/left_arrow.png">
			</div>
			<div id="swiper_line">
				<div id="swiper_line1" class="swiper_lines"></div>
				<div id="swiper_line2" class="swiper_lines"></div>
				<div id="swiper_line3" class="swiper_lines"></div>
			</div>
			<div id="right_arrow_btn" class="arrow_btn" onclick="swipe_next()">
				<img src="/img/right_arrow.png">
			</div>
	</div>
	<jsp:include page="/views/includes/searchbox.jsp"></jsp:include>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>