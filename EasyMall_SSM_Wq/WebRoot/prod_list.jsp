<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath}/css/prodList.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/head.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/foot.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${pageContext.request.contextPath}/product/findProductsByCondition">
				<span class="input_span">商品名：<input type="text" name="name"/></span>
				<span class="input_span">商品种类：<input type="text" name="category"/></span>
				<span class="input_span">商品价格区间：<input type="text" name="minprice"/> - <input type="text" name="maxprice"/></span>
				<input type="submit" value="查 询">
			</form>
		</div>
		<div id="prod_content">
		<c:forEach items="${list }" var="prod">
		
			<div class="prod_div">
				<a href ="${pageContext.request.contextPath}/product/findProductInfo?pid=${prod.id}" >
				<img src="${pageContext.request.contextPath}/product/findProductImgByUrl?imgurl=${prod.imgurl}"/>
				</a>
				<div id="prod_name_div">
				<a href ="${pageContext.request.contextPath}/product/findProductInfo?pid=${prod.id}" >
					${prod.name}
				</a>
				</div>
				<div id="prod_price_div">
					￥ ${prod.price} 元
				</div>
				<div>
					<div id="gotocart_div">
						<a href="${pageContext.request.contextPath}/cart/addProductToCart?pid=${prod.id}&buyNum=1">加入购物车</a>
					</div>					
					<div id="say_div">
						133人评价
					</div>					
				</div>
			</div>
			</c:forEach>
			<div style="clear: both"></div>
		</div>
	</div>
	<style type="text/css">
		#prod_name_div a{
			text-decoration: none;
			font-size: 1.3em;
			color: gray;
		}
		#prod_name_div a:hover{
			text-decoration: underline;
			font-size: 1.3em;
			color: orange;
		}
		
	</style>
	<%@ include file="_foot.jsp" %>
</body>
</html>
