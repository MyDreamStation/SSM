<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">
<mx:commonLinks />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 让 IE 和chrome浏览器运行最新的渲染模式下 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="User login page" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>教学质量管理系统</title>
<!--[if lte IE 9]>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/assets/css/ace-part2.min.css" />
	<![endif]-->

<!--[if lte IE 9]>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/assets/css/ace-ie.min.css" />
	<![endif]-->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
    <script src="<%=request.getContextPath()%>/resources/forIE/html5shiv.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/forIE/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<button id="start" type="button">启动</button>
<input type="text" id="content"/>
<button id="submit" type="button">提交</button>
</body>
<script type="text/javascript" src="pages/test/testExpression/testExpression.js"></script>
</html>