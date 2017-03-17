<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//res.layui.com/layui/build/css/layui.css"
	media="all">
<script src="<%=request.getContextPath()%>/resources/assets/js/jquery.min.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>设定上传文件的格式</legend>
</fieldset>
 
<input name="file" class="layui-upload-file" type="file"> 
<input name="file1" lay-type="file" class="layui-upload-file" type="file"> 
<input name="file1" lay-type="audio" class="layui-upload-file" type="file"> 
<input name="file2" lay-type="video" class="layui-upload-file" type="file"> 
<blockquote class="layui-elem-quote" style="margin-top: 20px;">支持拖动文件上传</blockquote>
 
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>演示上传</legend>
</fieldset>
 
<div class="site-demo-upload">
  <img id="LAY_demo_upload" src="http://layer.layui.com/images/tong.jpg">
  <div class="site-demo-upbar">
    <input name="file" class="layui-upload-file" id="test" type="file">
  </div>
</div>
 
<p style="margin-top: 20px;">注：由于服务器资源有限，所以此处每次给你返回的是同一张图片</p>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>自定义文本</legend>
</fieldset>
 
<input name="file" class="layui-upload-file" lay-title="添加一个碉堡了的图片" type="file"> 
 
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>保留原始风格</legend>
</fieldset>
 
<input name="file" type="file">
 
<blockquote class="layui-elem-quote" style="margin-top: 20px;">即不改变input的样式，只提供上传功能</blockquote>               
          
<script src="//res.layui.com/layui/build/layui.js" charset="utf-8"></script>
<script>
layui.use('upload', function(){
  layui.upload({
    url: '' //上传接口
    ,success: function(res){ //上传成功后的回调
      console.log(res)
    }
  });
  
  layui.upload({
    url: '/test/upload.json'
    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
    ,method: 'get' //上传接口的http类型
    ,success: function(res){
      LAY_demo_upload.src = res.url;
    }
  });
});
</script>

</body>
</html>