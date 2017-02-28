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
<!-- 让 IE 和chrome浏览器运行最新的渲染模式下 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="User login page" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>教学质量管理系统</title>

<!-- 导入bootstrap的css -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />

<!-- 导入fontawesome的css -->
<link rel="stylesheet"
	href="resources/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
<!-- text fonts -->
<link rel="stylesheet"
	href="resources/assets/fonts/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="resources/assets/css/ace.min.css" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
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

<!-- 导入layer.js -->
<script type="text/javascript" src="resources/layer/layer.js"></script>
</head>

<body>

	<div class="rows">
		用户名<input type="text" id="loginId" class="" /> <span id="error_1"></span>
	</div>
	<div class="rows">
		密码<input type="password" id="password" class="" /> <span id="error_2"></span>
	</div>
	<div class="rows">
		<!-- <button id="" value="登录"></button> -->
		<input type="submit" id="loginButton" value="登录" />
	</div>

</body>
<script type="text/javascript" src="pages/login/login.js"></script>
</html>