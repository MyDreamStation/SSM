$(function(){
	$('#submit').click(function(){
		var info=$("#info").val();
		alert(info);
		
		$.ajax({
			type:'post',
			url:'activiti/test.do',
			data:{
				info:info
			},
			dataType:'json',
			success:function(){
				alert("success!");
			},
			failure:function(){
				alert('failure!');
			}
		});
	});
})