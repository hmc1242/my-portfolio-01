<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모니터</title>
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/header.css">
<link rel="stylesheet" type="text/css" href="/css/footer.css">
<link rel="stylesheet" type="text/css" href="/css/pathbar.css">
<link rel="stylesheet" type="text/css" href="/css/product.css">
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
			<li class="path_bar_text_list">
				<a href="#">
					<span>${requestScope.product_type}</span>
				</a>
			</li>
		</ul>
	</div>
	<div id="product_list">
		<h1>${requestScope.product_type}</h1>
		<ul>
			<c:forEach var="product" varStatus="status" items="${requestScope.productlist}">
				<li>
					<table class="product_box">
						<tr>
							<td>
								<img src="/img/${product.getPtype()}/${product.getPid()}.jpg">
							</td>
							<td>
								<table class="product_text_box">
									<tr>
										<td class="product_text_title">제품명</td>
										<td class="product_text_content">${product.getPname()}</td>
									</tr>
									<tr>
										<td class="product_text_title">가격</td>
										<td class="product_text_content">${product.getUnitprice()}</td>
									</tr>
									<tr>
										<td class="product_text_title">제품설명</td>
										<td class="product_text_content">${product.getPdesc()}</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</li>
			</c:forEach>

		</ul>
	</div>
	<jsp:include page="/views/includes/footer.jsp"></jsp:include>
</body>
</html>