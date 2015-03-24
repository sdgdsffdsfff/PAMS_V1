<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<title>流程提示信息</title>
</head>
<body id="pageLike">

<h1>流程提示信息</h1>

<form name="form1">

<div id="chengangPage">
	<div class="pageSuccess">
	<span id="message">
		<p style="font-size:14px;">文档已收回至【${obj.endactname}】环节，等待处理。</p>	
		<br/>
	</span>
	</div>
</div>	

<div style="text-align:center;padding-top:10px;">		
	<button onclick="closeWindow()">关闭</button>
</div>

</form>

<script>

function page_load()
{
	window.top.opener.top.location.reload();
}

function closeWindow()
{
	window.close();
	return;
}

jQuery(function($)
{
	page_load();
})

</script>

</html>
