
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
</head>
<body>

<#if obj.isapply == true>
<button id="bt_apply" class="btn btn-primary">签收</button>
</#if>

<#if obj.isforward == true>
<button id="bt_forward" class="btn btn-primary">转发</button>
</#if>

<#if obj.iscallback == true>
<button id="bt_callback" class="btn btn-primary">收回</button>
</#if>

<#if obj.isbackward == true>
<button id="bt_backward" class="btn btn-primary">退回</button>
</#if>

<input type="text" id="cname" name="cname" value="${obj.partydue.cname}"><br/>
<input type="text" id="cno" name="cno" value="${obj.partydue.cno}"><br/>

<script type="text/javascript">
$("#bt_apply").click(function() {page_apply()});
$("#bt_forward").click(function() {page_forward()});
$("#bt_callback").click(function() {page_callback()});
$("#bt_backward").click(function() {page_backward()});

// 签收
function page_apply()
{
	url = "${base}/party/apply.action?runactkey=${obj.runactkey}";	 
	window.location = url;
}

// 转发
function page_forward()
{
	var url = "${base}/workflow/ui/forwardselect.action";
	url += "?runactkey=${obj.runactkey}";
	// openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);
	// window.open(url);	
	window.location = url;
}

// 收回
function page_callback() 
{
	url = "${base}/module/app/system/workflow/ui/callback.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&tableid=${obj.tableid}";

	// openwin(url,"callback",pub_width_mid,pub_height_mid);	
	window.location = url;
}

// 退回
function page_backward()
{
	if (confirm("确定退回操作吗？")==true)
	{
		url = "${base}/module/app/system/workflow/ui/backward.action";
		url += "?runactkey=${obj.runactkey}";
		url += "&tableid=${obj.tableid}";
		
		// openwin(url,"backward",pub_width_mid,pub_height_mid);
		window.location = url;
	}				   
}
</script>

</body>
</html>