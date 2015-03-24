<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script>
function showBottomFrame(my_rows)
{
 	window.opener.parent.document.getElementsByTagName("frameset")[0].rows = my_rows;
}

function closeWindow()
{
	window.top.opener.top.location.reload();
	window.close();
}
</script>
<title>转发成功提示　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　</title>
</head>
<body bgcolor="#ffffff" scroll="no">
<#assign nameStr = "">
<#list Request.bean_user as aobj>
	<#if aobj &lt; aobj_index - 1>
		<#assign nameStr = nameStr + aobj.cname>
	<#else>
		<#assign nameStr = nameStr + aobj.cname + ", ">
	</#if>
</#list>

<form name="form1">

<table width="100%" height="90%" border="0" cellpadding="3" cellspacing="0" style="width: 100%; border: outset 1px; border-top: none" bgcolor="#eeeeee" align="center">
	<tr>
		<td>
			<table align="center" >
				<tr>
					<td colspan="2">
						<p>&nbsp;文档已经转发给[<font color="red">${nameStr}</font>]等候处理！</p>		
					</td>
				</tr>				
				<tr>
					<td><p><br></p></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class="oSpan" align="right">

<table width="100%"  border="0" cellpadding="3" cellspacing="0" style="width:100%;">
	<tr>
	<td></td><td></td><td></td>
	<td align="right">		
		<button onclick="closeWindow()">&nbsp;&nbsp;关闭&nbsp;&nbsp;</button>
	</td>	
	</tr>
</table>
</div>
</form>
</body>

</html>
