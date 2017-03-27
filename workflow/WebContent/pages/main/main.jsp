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
<mx:index />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 让 IE 和chrome浏览器运行最新的渲染模式下 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description" content="index page of system" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>教学质量管理信息系统</title>
</head>
<style type="text/css">
.fa {
	line-height:30px;
}
</style>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header header header-demo">
			<div class="layui-main">
				<div class="admin-login-box">
					<a class="logo" style="left: 0;" href="#"> <span
						style="font-size: 22px;">教学质量管理系统</span>
					</a>
					<div class="admin-side-toggle">
						<i class="fa fa-bars" aria-hidden="true"></i>
					</div>
				</div>
				<ul class="layui-nav admin-header-item">
					<!-- <li class="layui-nav-item"><a href="javascript:;">清除缓存</a></li>
					<li class="layui-nav-item"><a href="javascript:;">浏览网站</a></li>
					<li class="layui-nav-item" id="video1"><a href="javascript:;">视频</a>
					</li> -->
					<li class="layui-nav-item"><a href="javascript:;"
						class="admin-header-user"> <img src="resources/images/0.jpg" /> <span>beginner</span>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;"><i class="fa fa-user-circle"
									aria-hidden="true"></i> 个人信息</a>
							</dd>
							<dd>
								<a href="javascript:;"><i class="fa fa-gear"
									aria-hidden="true"></i> 设置</a>
							</dd>
							<dd id="lock">
								<a href="javascript:;"> <i class="fa fa-lock"
									aria-hidden="true"
									style="padding-right: 3px; padding-left: 1px;"></i> 锁屏 (Alt+L)
								</a>
							</dd>
							<dd>
								<a href="#" id="signOut"><i class="fa fa-sign-out"
									aria-hidden="true"></i> 注销</a>
							</dd>
						</dl></li>
				</ul>
				<ul class="layui-nav admin-header-item-mobile">
					<li class="layui-nav-item"><a href="login.html"><i
							class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
				</ul>
			</div>
		</div>
		<div class="layui-side layui-bg-black" id="admin-side">
			<div class="layui-side-scroll" id="admin-navbar-side"
				lay-filter="side"></div>
		</div>
		<div class="layui-body"
			style="bottom: 0; border-left: solid 2px #1AA094;" id="admin-body">
			<div class="layui-tab admin-nav-card layui-tab-brief"
				lay-filter="admin-tab">
				<ul class="layui-tab-title">
					<li class="layui-this"><i class="fa fa-dashboard"
						aria-hidden="true"></i> <cite>控制面板</cite></li>
				</ul>
				<div class="layui-tab-content"
					style="min-height: 150px; padding: 0px 0 0 0;">
					<div class="layui-tab-item layui-show">
						<iframe src="pages/main/demo.jsp"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-footer footer footer-demo" id="admin-footer">
			<div class="layui-main">
				<p>
					2017 &copy; <a href="#">qt.bjtu.edu.cn</a> 
				</p>
			</div>
		</div>
		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>

		<!--锁屏模板 start-->
		<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="resources/images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
		<!--锁屏模板 end -->

		<script>
			layui.use('layer', function() {
				var $ = layui.jquery, layer = layui.layer;

				/* $('#video1').on('click', function() {
					layer.open({
						title : 'Video',
						maxmin : true,
						type : 2,
						content : 'video.html',
						area : [ '900px', '600px' ]
					});
				}); */
				

			});
		</script>
	</div>
</body>
<script type="text/javascript" src="pages/main/logout.js"></script>
</html>