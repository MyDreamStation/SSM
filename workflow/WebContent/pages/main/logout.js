$(function(){
	$('#signOut').click(function(){
		$.ajax({
			type:'post',
			url:'login/logout.do',
			dataType:'json',
			success:function(data){
				if(data.success == true){
					layer.alert(data.data);
					window.location.href='page/login/login.jsp';
				}
			}
		});
	});
});