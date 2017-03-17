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
<%-- <mx:index /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//res.layui.com/layui/build/css/layui.css"
	media="all">
<script src="//res.layui.com/layui/build/layui.js" charset="utf-8"></script>
</head>
<body>
	<div class="site-demo-upload">
		<!-- <img id="LAY_demo_upload" src="http://layer.layui.com/images/tong.jpg"> -->
		<div class="site-demo-upbar">
			<input name="file" class="layui-upload-file" id="test"
				lay-type="images" lay-ext="jpg|png|gif" lay-title="请上传一张图片吧亲"
				type="file">
		</div>
	</div>

	<!-- lay-showPercent显示百分比  layui-progress-big百分比在内侧，并且进度条为大号-->
	<div class="layui-progress layui-progress-big" lay-showPercent="yes" lay-filter="demo">
		<div class="layui-progress-bar layui-bg-green" lay-percent="20%" ></div>
	</div>


	<div class="site-demo-button"
		style="margin-top: 20px; margin-bottom: 0;">
		<button class="layui-btn site-demo-active" data-type="setPercent">设置50%</button>
		<button class="layui-btn site-demo-active" data-type="loading" >模拟loading</button>
	</div>
</body>
<script type="text/javascript">
	layui.use('upload', function() {
		var option = {
			elem : '.layui-upload-file',
			url : '',
			method : 'POST',
			before : function(input) {

			},
			success : function(res, input) {

			}
		}
		layui.upload(options);
	});

	//注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
	layui.use('element', function() {
		var $ = layui.jquery, element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
		//触发事件
		var active = {
			setPercent : function() {
				//设置50%进度
				element.progress('demo', '50%')
			},
			loading : function(othis) {
				var DISABLED = 'layui-btn-disabled';
				if (othis.hasClass(DISABLED))
					return;

				//模拟loading
				var n = 0, timer = setInterval(function() {
					n = n + Math.random() * 10 | 0;
					if (n > 100) {
						n = 100;
						clearInterval(timer);
						othis.removeClass(DISABLED);
					}
					layui.element().progress('demo', n + '%');
				}, 300 + Math.random() * 1000);

				othis.addClass(DISABLED);
			}
		};

		$('.site-demo-active').on('click', function() {
			var othis = $(this), type = $(this).data('type');
			active[type] ? active[type].call(this, othis) : '';
		});

	});
</script>
</html>