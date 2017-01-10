<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 让 IE 浏览器运行最新的渲染模式下 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>教学质量管理系统</title>

<!-- 导入bootstrap的css -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="resources/forIE/html5shiv.min.js"></script>
      <script src="resources/forIE/respond.min.js"></script>
    <![endif]-->

<!-- 导入jQuery.js -->
<script type="text/javascript" src="resources/jquery.js"></script>

<!-- 导入bootstrap.js -->
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>



<body>

	<div class="rows">
		<span>123456</span>
	</div>
	<div class="rows"></div>

</body>
</html>