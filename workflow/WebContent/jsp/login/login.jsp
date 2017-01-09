<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学质量管理系统</title>
</head>
<!-- 导入jQuery -->
<script type="text/javascript" src="resources/jquery.js"></script>

<!-- 导入bootstrap -->
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>

<!-- 导入bootstrap的css -->
<!-- <link type="text/css" rel="stylesheet"/> -->
<body>
这是主页！
</body>
</html>