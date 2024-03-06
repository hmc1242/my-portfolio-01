<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/products.css">
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
				<a href="/products">
					<span>제품</span>
				</a>
			</li>
		</ul>
	</div>
	<table id="products_box">
		<tr>
			<th><h2>보고싶은 제품을 선택하세요</h2></th>
		</tr>
		<tr id="products_select">
			<td class="products_select_boxes">
				<h3>모니터</h3>
				<div class="products_img_wraps">
					<div id="monitor_img" class="products_imgs"
						onclick="location.href='/Product.do?product_mode=monitor'"></div>
				</div>	
			</td>
			<td class="products_select_boxes">
				<h3>키보드</h3>
				<div class="products_img_wraps">
					<div id="keyboard_img" class="products_imgs"
						onclick="location.href='/Product.do?product_mode=keyboard'"></div>
				</div>
			</td>
			<td class="products_select_boxes">
				<h3>마우스</h3>
				<div class="products_img_wraps">
					<div id="mouse_img" class="products_imgs"
						onclick="location.href='/Product.do?product_mode=mouse'"></div>
				</div>
			</td>
		</tr>
	</table>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>