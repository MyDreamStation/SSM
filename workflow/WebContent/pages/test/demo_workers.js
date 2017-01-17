
	var i=0;

	var onmessage=function (event)
	{
	    // 将数据提取出来。
	    var data = JSON.parse(event.data);
	    // 取出start参数
	    var start = data.start;
	    // 取出end参数
	    var end = data.end;
	postMessage("start:"+start+" end:"+end);
//		postMessage(event.data);
	}

//	onmessage();
