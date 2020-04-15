<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	  <!--   
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		
		//param1: 当前节点id
		//param2: 父节点id
		//param3: 节点上的文字描述
		//param4: 跳转路径
		//param5: 提示信息
		//param6: 发生变化的frame的name属性值

		d.add('0104','01','商品管理');
		d.add('010401','0104','商品管理','${pageContext.request.contextPath}/adminPro/findAllProductsWithPage.do?num=1','提示信息','mainFrame');
		
		
		d.add('0105','01','订单管理');
		d.add('010502','0105','未付款的订单','${pageContext.request.contextPath}/adminOrder/findAllOrdersWithPage.do?num=1&statu=0','','mainFrame');
		d.add('010503','0105','已付款订单','${pageContext.request.contextPath}/adminOrder/findAllOrdersWithPage.do?num=1&statu=1','','mainFrame');
		d.add('010504','0105','已发货的订单','${pageContext.request.contextPath}/adminOrder/findAllOrdersWithPage.do?num=1&statu=2','','mainFrame');
		d.add('010505','0105','已完成的订单','${pageContext.request.contextPath}/adminOrder/findAllOrdersWithPage.do?num=1&statu=3','','mainFrame');
		
		d.add('0106','01','用户管理');
		d.add('010601','0106','用户列表','${pageContext.request.contextPath}/adminUser/findAllUsersWithPage.do?num=1','','mainFrame');
		
		document.write(d);
	    -->
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
