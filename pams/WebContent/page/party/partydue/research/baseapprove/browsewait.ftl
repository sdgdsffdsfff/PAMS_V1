
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高层计划</title>
<link rel="stylesheet" href="/pams/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/pams/lib/font-awesome/css/font-awesome.min.css">
<link href="../logo60.png" rel="apple-touch-icon">
<link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet"
	href="/pams/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/pams/css/animation.css">
<link rel="stylesheet" href="/pams/lib/slider.css">
<link rel="stylesheet" href="/pams/css/main.css">
<script src="/pams/lib/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="/pams/css/advplan2.css">
</head>
<body>

<table id="allContactTb" class="table personListTable hover">
<thead>
    <tr>
      <th class="check min"></th>   
      <th>编号</th>
      <th>标题</th>     
      <th>节点</th>
      <th>处理人</th>      
      <th>处理状态</th>

    </tr>
  </thead>
  <tbody>
  </tbody>
</table>

<script src="/pams/lib/jquery-ui.min.js"></script>
<script src="/pams/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/pams/lib/moment.min.js"></script>
<script src="/pams/lib/moment.zh-cn.js"></script>
<script
	src="/pams/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="/pams/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/pams/lib/lodash.min.js"></script>
<script src="/pams/lib/bootstrap-slider.js"></script>
<script src="/pams/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="/pams/js/main.js"></script>
<script>


jQuery(function($) {
	//获得数据
	$.ajax({
		url: '${base}/party/partydue/research/baseapprove/browsewait/json.action',
		dataType: 'json',
		success: function(d)
		{
			data = d;
			var trHtml = "";
			$.each(data,function(i,n)
			{
				trHtml += '<tr data-id="'+n.runactkey+'">';
				trHtml += '<td class="check"></td>';		
				trHtml += '<td><a href="${base}/party/partydue/research/baseapprove/readpage.action?runactkey='+n.runactkey+'">'+n.cno+'</a></td>';
				trHtml += '<td>'+n.cname+'</td>';
				trHtml += '<td>'+n.bactcname+'</td>';
				trHtml += '<td>'+n.username+'</td>';				
				trHtml += '<td>'+n.ractstate+'</td>';
		
				trHtml += '</tr>';
			});
			console.log(trHtml);
			$('#allContactTb tbody').append(trHtml);
		}
	});
	
	var allContactTbody=$('#allContactTb').find('tbody');
	allContactTbody.on('click','tr td.check',function(){
		console.log($(this));
		var sid=$(this).parent().attr('data-id');
		// $(this).toggleClass('selected');
		if(!$(this).parent().hasClass('selected')){
			$(this).parent().addClass('selected');
		}else{
			
			$(this).parent().removeClass('selected');
		}
	})

	
});

function page_locate(runactkey)
{
	console.log("locate.begin.");
	window.location = "${base}/party/partydue/research/baseapprove/readpage.action?runactkey=" + runactkey;
}

		
</script>
</body>
</html>