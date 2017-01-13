<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>计数: <output id="result"></output></p>
<button onclick="startWorker()">开始 Worker</button> 
<button onclick="stopWorker()">停止 Worker</button>
<br /><br />

<script>
var w;

function startWorker()
{
if(typeof(Worker)!=="undefined")
  {
  if(typeof(w)=="undefined")
  {
  w=new Worker("http://localhost:8080/workflow/pages/test/demo_workers.js");
  }
  w.onmessage = function (event) {
    document.getElementById("result").innerHTML=event.data;
    };
  }
else
  {
  document.getElementById("result").innerHTML="Sorry, your browser does not support Web Workers...";
  }
}

function stopWorker()
{ 
w.terminate();
}
</script>
</body>
</html>