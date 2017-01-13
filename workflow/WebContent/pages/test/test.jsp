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
<title>测试界面</title>
<!-- bootstrap.css -->
<link rel="stylesheet" 
	href="resources/bootstrap/css/bootstrap.css" />

<!-- layer.css -->
<link rel="stylesheet" href="resources/layer/mobile/need/layer.css">

<!-- JQuery.js -->
<script type="text/javascript" src="resources/jquery.js"></script>

<!-- bootstrap.js -->
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>
</head>

<!-- layer.js -->
<script type="text/javascript" src="resources/layer/layer.js"></script>

<body>
	<div class="row col-">
		<div class="input-group">
			<span class="input-group-addon">用户id</span> <input type="text"
				class="form-control" placeholder="用户id" id="id">
		</div>
		<div class="input-group">
			<span class="input-group-addon">用户名</span> <input type="text"
				class="form-control" placeholder="用户名" id="id">
		</div>
	</div>

	<div class="input-group">
		<span class="input-group-addon">$</span> <input type="text"
			class="form-control"> <span class="input-group-addon">.00</span>
	</div>
</body>
<script type="text/javascript" src="js/test/test.js"></script>
</html>