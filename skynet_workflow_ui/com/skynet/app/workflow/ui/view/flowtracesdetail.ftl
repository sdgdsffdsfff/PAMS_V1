<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<title>流程跟踪　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　</title>
<body>
	&nbsp;&nbsp;<a href="javascript:void(0)" onclick="page_viewprogress()">流程图</a>

<form action="" method="post">
<table width="100%" cellspacing="10" class="dataGrid" id="otable">
<tbody>
<#assign t_runactkey = "">
<#list obj.bean_traces as oneTraces>
	<#assign eventObj = oneTraces[0]>
	<#assign eventObj_Receiver = oneTraces[1]>
	<#assign eventtype = eventObj.eventtype>
	<#if eventtype == "FORWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<#if eventObj.endactcname == '结束' || eventObj.ctype == 'END'>
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 结束流程 </td>
		</tr>
		<#else>
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 转发到 <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
		</#if>
	<#elseif eventtype == "CFORWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 转给下一人到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "BACKWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 退回到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "CBACKWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 退回上一人到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>	
	<#elseif eventtype == "RESET">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 重定位到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>												
	<#elseif eventtype == "CONSIGN_HANDER">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 在 <b>${eventObj.actcname}</b> 转交办给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "CONSIGN_ASSORTER">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 在 <b>${eventObj.actcname}</b> 转协办给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "APPLY">
		<tr id="tr_${oneTraces_index}">
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 签收 <b>${eventObj.actcname}</b> 业务</td>
		</tr>
		<script>
		$.post("flowtraceopinion.action?runactkey=${eventObj.runactkey}", 
		function (data){
			$('#tr_${oneTraces_index}').after(data);
		});
		</script>
	<#elseif eventtype == "CREATE">
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 启动流程 </td>
		</tr>
	<#elseif eventtype == "SUSPEND">
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 挂起流程 </td>
		</tr>
	<#elseif eventtype == "RESUME">
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 恢复运行流程 </td>
		</tr>											
	<#elseif eventtype == "TERMINATE">
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: ${eventObj.eventercname} 终止流程 </td>
		</tr>
	<#elseif eventtype == "COMPLETE">
		<tr>
			<td align="left">${eventObj.eventtime.substring(0,16)}: 流程结束 </td>
		</tr>																																																																														
	</#if>
</#list>
</tbody>
</table>	
												
</form>
<script>
function page_viewprogress()
{
	url = "${base}/module/app/system/workflow/define/define_viewprogress.action?runflowkey=${arg.runflowkey}&actdefid=${arg.actdefid}";
	openwin(url, 'viewprogress', pub_width_mid,pub_height_mid);
}
</script>
	
</body>
</html>