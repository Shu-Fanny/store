<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>下订单</title>
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
            .with{
                line-height: 34px;
				width: 50%;
            }
			.hello{
				display:block;width:40%;height:34px;padding:6px 12px;font-size:14px;line-height:1.42857143;color:#555;background-color:#fff;background-image:none;border:1px solid #ccc;border-radius:4px;-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);box-shadow:inset 0 1px 1px rgba(0,0,0,.075);-webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;-o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s
			}
		</style>
	</head>

	<body>

	<%@ include file="/jsp/header.jsp" %>


		<div class="container">
		
		 <form id="orderForm" method="post" action="${pageContext.request.contextPath}/order/updateOrderStatuByOid.do">
			<div class="row">

				<div style="margin:0 auto;margin-top:10px;width:950px;">
					<strong>订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th colspan="5">订单编号:${oid}</th>
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
						<c:forEach items="${orderItems}" var="item">
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
								<td width="10%">
									${item.quantity}
								</td>
								<td width="15%">
									<span class="subtotal">￥${item.total_price}</span>
								</td>
							</tr>
						  </c:forEach>
						</tbody>
					</table>
				</div>

				<div style="text-align:right;margin-right:120px;">
					商品金额: <strong style="color:#ff6600;">￥${total_price }元</strong>

				</div>

			</div>

			<div>

				<hr/>
                <div class="form-horizontal" style="margin-left:150px;">
					<div class="form-group">
						<label for="username" class="col-sm-1 control-label with">地址:</label>
                        <input type="text" name="adress" id="username"  class="hello" placeholder="请输入收货地址">
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-1 control-label with">收货人:</label>
                        <input type="text" name="name" class="hello" id="inputPassword3" placeholder="请输收货人">
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-1 control-label with">邮箱:</label>
                        <input type="text" name="email" class="hello" id="confirmpwd" placeholder="请输入联系方式">
                        <input type="hidden" name="oid" value="${oid}"/>
						<input type="hidden" name="statu" value="1"/>
						<input type="hidden" name="total_price" value="${total_price}"/>
					</div>
                </div>
				<hr/>

				<div style="margin-top:5px;margin-left:150px;">
					<hr/>
					<p style="text-align:right;margin-right:100px;">
						<a href="javascript:document.getElementById('orderForm').submit();">
							<img src="${pageContext.request.contextPath}/img/finalbutton.gif" width="204" height="51" border="0" />
						</a>
					</p>
					<hr/>

				</div>
			</div>
		  </form>
		</div>

		
		
		<%@ include file="/jsp/footer.jsp" %>
		
	</body>

</html>