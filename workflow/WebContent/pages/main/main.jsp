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
<meta name="description" content="主页" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>教学质量管理信息系统</title>
</head>
<body>





	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="resources/assets/js/jquery.2.1.1.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="resources/assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='resources/assets/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='resources/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
</body>
<script type="text/javascript" src="pages/login/login.js"></script>
</html>