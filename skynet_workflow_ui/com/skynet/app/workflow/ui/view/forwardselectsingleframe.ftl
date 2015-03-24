<html>
<head>
<meta charset="utf-8" />
<title></title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
</head>
<body>

<div class="formDiv">

<div id="fixedOp">
<button onclick="page_forward()">转发</button>
<button onclick="page_showflowdefine()">流程</button>
<button onclick="page_close()">关闭</button>
</div>

<form id="aform">
<div class="formDiv">
	<h1 style="cursor:hand" onclick="page_toggle_node()">1.当前节点  </h1>
	<div id="fs_node">
	<table id="tb_node" class="formGrid" width="600">
	<tbody>
	<tr>
	<td width="20">&nbsp;</td>
	<td width="80" align="right">节点名：</td>
	<td width="100" align="left">${obj.bact.cname}</td>
	<td width="400"></td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	<td>节点类型：</td>
	<td>${txt_ctype}</td>
	<td></td>
	</tr>
	<tr>
	<td></td>
	<td>转出方式：</td>
	<td>${txt_split}</td>
	<td align="left" style="color:#3355aa">在后续节点为多项时，仅能选择其中一项后续节点。</td>
	</tr>
	</tbody>
	</table>
	</div>
	
	<h1 style="cursor:hand" onclick="page_toggle_routes()">2.选择转发节点</h1>
	<div id="fs_routes">
	<table id="tb_routes" class="formGrid" width="600">
	// 动态增加
	<tbody>
	</tbody>
	</table>
	</div>

	<h1 style="cursor:hand" onclick="page_toggle_persons()">3.选择转发人员 </h1>	
	<div id="fs_persons" style="display:none">
		<table id="gTabsContainterN">
		<tr class="topTr">
		<td>
			<ul>
				<li class="c" id="tab1" onclick="page_select_tab(this, 1)">流程人员</li>
			</ul>	
		</td>
		</tr>
		<tr class="bottomTr">
		<td height="100">
		<iframe id="frame_selectowner" name="frame_selectowner" src="" frameborder="1" height="100" ></iframe>
		</td>
		</tr>
		</table>
	</div>
		
	</div>
</div>
</form>

</body>
</html>