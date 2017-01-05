$(function(){
	
	$.ajax({
		type:'post',
		url:'user/add.do',
		data:{userName:'huahua'},
		dataType:'json',
		success:function(){
			alert('success');
		},
		failure:function(){
			
		}
		
	});
	
	
})