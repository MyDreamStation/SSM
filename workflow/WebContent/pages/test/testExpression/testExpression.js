$(function(){
	
	var pid = '';
	$('#start').click(function(){
		alert('启动流程！');
		
		$.ajax({
			type : 'post',
			url : 'activiti/startProcess',
			data : {
				id:'TestExpression'
			},
			dataType : 'json',
			success : function(data) {

				console.log(data);
				pid = data.data;
			},
			failure : function() {

			}
		});
	});
	
	$('#submit').click(function(){
		var content = $('#content').val();
		alert('提交');
		
		$.ajax({
			type : 'post',
			url : 'activiti/submitParam',
			data : {
				id:pid,
				content:content
				
			},
			dataType : 'json',
			success : function() {
				alert('提交成功');
			},
			failure : function() {
				alert('提交失败');
			}
		});
		
	});
})