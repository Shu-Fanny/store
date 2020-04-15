<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户订单</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	<!-- 引入自定义css文件 style.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

	<style>
		body {
			margin-top: 20px;
			margin: 0 auto;
		}

		.carousel-inner .item img {
			width: 100%;
			height: 300px;
		}
	</style>
</head>

<body>
<div class="container">
	<div class="row">

		<div style="margin:0 auto; margin-top:10px;width:950px;">
			<strong>此用户的商品浏览记录</strong>
			<table class="table table-bordered">
				<tr class="success">
				</tr>
				<tr class="warning">
					<th>图片</th>
					<th>商品</th>
					<th>价格</th>
					<th>浏览次数</th>
				</tr>
				<c:forEach items="${pageModel.list}" var="item">
					<tbody>
						<tr class="active">
							<td width="60" width="40%">
								<input type="hidden" name="id" value="22">
								<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
							</td>
							<td width="30%">
								<a target="_blank">${item.product.pname}</a>
							</td>
							<td width="20%">
								￥${item.product.price}
							</td>
							<td width="15%">
								<span class="subtotal">${item.total}次</span>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<%@include file="/admin/order/page.jsp" %>
		</div>
	</div>

</div>
</body>

</html>