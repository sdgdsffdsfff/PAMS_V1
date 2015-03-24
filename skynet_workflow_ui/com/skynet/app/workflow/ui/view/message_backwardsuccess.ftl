<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<title>流程提示信息</title>
</head>

<body id="pageLike">
<h1>流程提示信息</h1>

<#assign nameStr = "">
<#assign nums = obj.actowners?size>
<#list obj.actowners as aobj>
<#if aobj_index &lt; nums - 1>
	<#assign nameStr = nameStr + aobj.cname + ",">	
<#else>
	<#assign nameStr = nameStr + aobj.cname>	
</#if>
</#list>

<form name="form1">
<div id="chengangPage">
	<div class="pageSuccess">
	<span id="message">
	<p style="font-size:14px;">&nbsp;文档已退回至【${obj.actname}】环节，交给【${nameStr}】等人员，等待处理。</p>
	</span>
	</div>
</div>
</form>

<div style="text-align:center;padding-top:10px;">		
	<button onclick="closeWindow()">关闭</button>
</div>

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
