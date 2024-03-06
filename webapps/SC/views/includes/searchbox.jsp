<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/searchbox.css">
</head>
<body>
	<form method="get" action="/Board.do">
		<input type="hidden" name="board_mode" value="search">
		<div id="search_box">
			<h2>제품, 증상 등을 검색하세요</h2>
			<div id="search_box_middle">
				<input type="text" id="search_bar" name="search_text"
					value="${requestScope.search_text}">
				<input type="image" src="/img/magnifying-glass-solid.svg" id="search_img">
			</div>
		</div>
	</form>
</body>
</html>