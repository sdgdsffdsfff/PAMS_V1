
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${base}/lib/font-awesome/css/font-awesome.min.css">
<link href="../logo60.png" rel="apple-touch-icon">
<link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet"
	href="${base}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${base}/css/animation.css">
<link rel="stylesheet" href="${base}/lib/slider.css">
<link rel="stylesheet" href="${base}/css/main.css">
<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="${base}/css/advplan2.css">
</head>
<body>

<table id="allContactTb" class="table personListTable hover">
<thead>
    <tr>
      <th class="check"></th>   
      <th>计划名称</th>
      <th>计划开始日期</th>
      <th>计划结束日期</th>      
      <th>计划人</th>
      <th>计划流程名称</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>

<script src="${base}/lib/jquery-ui.min.js"></script>
<script src="${base}/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/lib/moment.min.js"></script>
<script src="${base}/lib/moment.zh-cn.js"></script>
<script
	src="${base}/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="${base}/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${base}/lib/lodash.min.js"></script>
<script src="${base}/lib/bootstrap-slider.js"></script>
<script src="${base}/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${base}/js/main.js"></script>
<script>


jQuery(function($) {
	//获得数据
	$.ajax({
		url: '${base}/party/partydue/research/baseapprove/browseplan/json.action',
		dataType: 'json',
		success: function(d)
		{
			data = d;
			var trHtml = "";
			$.each(data,function(i,n)
			{
				trHtml += '<tr data-id="'+n.runactkey+'">';
				trHtml += '<td class="check"></td>';		
				trHtml += '<td><a href="${base}/party/readpage.action?id='+n.id+'"">'+n.cname+'</a></td>';
				trHtml += '<td>'+n.planstartdate+'</td>';
				trHtml += '<td>'+n.planenddate+'</td>';
				trHtml += '<td>'+n.creater+'</td>';				
				trHtml += '<td>'+n.flowdefid+'</td>';
				trHtml += '<td><button class="btn btn-primary" onclick="page_startplan(\''+n.id+'\',\''+n.flowdefid+'\')">启动</button></td>';
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
	window.location = "${base}/party/readpage.action?runactkey=" + runactkey;
}

//计划启动
function page_startplan(planid, flowdefid)
{
	url = "${base}/party/partydue/research/baseapprove/startplan.action?planid=" + planid + "&flowdefid=" + flowdefid;
	window.location = url;
}
		
</script>
</body>
</html>