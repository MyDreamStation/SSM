$(function() {

	$("#loginButton").click(function() {
		login();
	});
})

function login() {
	// 将错误信息清空
	$('#error_1').html("");
	$('#error_2').html("");

	var loginId = $('#loginId').val();// 登录用户名
	var password = $('#password').val();// 登录密码

	var flag;// 校验标志
	// 校验
	if (loginId == "") {
		$('#error_1').html("<font color='red'>请输入用户名</font>")
		return ;
	}
	if(password == ''){
		$('#error_2').html("<font color='red'>请输入密码</font>");
		return ;
	}

	$.ajax({
		type : 'post',
		url : "login/login.do",
		data : {
			loginId : loginId,
			password : password
		},
		dataType : 'json',
		success : function(data) {
			var status = data.status;
			if (status == '0' || status == '-1') {
				layer.alert(data.message);
			}else if (status == '1'){
				window.location.href="pages/main/main.jsp";
			}
		},
		failure : function(data) {

		}
	});

}