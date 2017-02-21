$(function(){
	
	
	$.ajax({
		type:'get',
		url:'http://20.1.75.42:8081/bpm/myhasdoWeb.do',
		data:{
			param:{
				data:{
					start:0,
					pagesize:15
				}
			}
		},
		dataType:'json',
		success:function(data){
			console.log(data);
			alert("success");	
		}
	})
	
})