<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="/pams/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/pams/lib/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/pams/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/pams/css/animation.css">
<link rel="stylesheet" href="/pams/lib/slider.css">
<link rel="stylesheet" href="/pams/css/main.css">
<script src="/pams/lib/jquery-2.1.1.min.js"></script>
</head>
<body>

<style>
h1 {
font-family: Arial, sans-serif;
font-size: 12px;
color: #369;
padding-bottom: 14px;
text-align:left;
margin-left:10px
}
</style>

<script type="text/javascript">

var navigationJSON=[ {name:'共享管理',link:'/pams/module/irm/portal/portal/portal/portal_browse.action?ccate=gxgl'}, {name:'共享管理'}];

function initialTabNsHeight(){
	//console.log($('#gTabsContainterN .topTr').height())
	var avialHeight=$('body').height()-40;
	$('#gTabsContainterN iframe').css({height:avialHeight+'px'});
	$('.bottomTr').css({height:avialHeight-100+'px'})
}

$(function(){
/////////////////////////////////////////

//解决ie6 bottomTr 高度问题；ff iframe 100% 问题
initialTabNsHeight();
$(window).resize(function(){initialTabNsHeight();})



//radius border
$('#gTabsContainterN .topTr li').wrapInner('<span class="r"><span class="m"></span></span>')

$('.back2grid').click(function()
{
	window.close();
})

/////////////////////////////////////////
})

function page_select_tab(obj, oindex)
{
	$('.topTr li').removeClass('c')
	obj.style="c";
	
	$('.bottomTr li').removeClass('c')
	$('.bottomTr li:nth-child('+oindex+')').addClass('c');
	
	var oFrame=$('.bottomTr').find('iframe')
	
	// 点击标签页
	if(oindex==1)
	{
		// 组织机构
		var endactid = list_endacts[c_rindex];
		var url = '/pams/workflow/ui/selectownertoperson.action?actdefid=' + endactid;
		oFrame.attr('src',url);
	}
	else if(oindex==2)
	{ 
		// 组织机构
		oFrame.attr('src','about:blank');
	}
	else if(oindex==2)
	{ 
		// 角色岗位
		oFrame.attr('src','about:blank');
	}
	else if(oindex==3)
	{ 
		// 人员
		oFrame.attr('src','about:blank');
	}
}

</script>

<#assign txt_ctype="">
<#assign txt_split="">

<#if obj.bact.ctype=="NORMAL">
	<#assign txt_ctype="普通">
</#if>	
<#if obj.bact.split=="OR">
	<#assign txt_split="单一">
<#elseif obj.bact.split=="AND">
	<#assign txt_split="单一">
</#if>

<div class="formDiv">

<div id="fixedOp">
<button id="bt_forward" class="btn btn-primary" onclick="page_forward()">转发</button>
</div>

<form id="aform" method="POST">
<input type="hidden" id="vo" name="vo" value="">
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
	<tbody>
	<#list obj.routes as route>
	<#assign endact = obj.endacts[route_index]>
	<tr>
	<td width="20">&nbsp;</td>
	<td width="40" align="center">
	<#if obj.bact.split=="OR" || obj.routes?size==1>
		<input class="route" type="radio" id="rindex${route_index}" name="rindex" onclick="page_selectroute('${route_index}','${route.endactid}','${endact.ctype}')" acttype="${endact.ctype}">
	<#elseif obj.bact.split=="AND">
		<input class="route" type="checkbox" id="rindex${route_index}" name="rindex" checked onclick="page_selectroute('${route_index}','${route.endactid}','${endact.ctype}')" acttype="${endact.ctype}">
	</#if>
	</td>
	<td width="140">${route.cname}&nbsp;</td>
	
	<#if endact.ctype=="END">
	<td width="350">
	<input type="hidden" id="actowner${route_index}" name="actowner">
	</td>
	<td width="50"></td>
	<#else>
	<td width="350">
	<input readonly class="form-control" id="actowner${route_index}" name="actowner" style="width:300;">&nbsp;&nbsp;
	</td>
	<td  width="50">
	<a href="javascript:void(0)" onclick="page_selectroute('${route_index}','${route.endactid},'${endact.ctype}')">人员</a>
	</td>
	</#if>
	</td>
	</tr>
	</#list>
	</tbody>
	</table>
	</div>

	<h1 style="cursor:hand" onclick="page_toggle_persons()">3.选择转发人员 </h1>	
	<div id="fs_persons" style="display:none">
		<table id="gTabsContainterN" width="600">
		<tr class="topTr">
		<td>
			<ul>
				<li class="c" id="tab1" onclick="page_select_tab(this, 1)">流程人员</li>
				<#--
				<li id="tab2">组织机构</li>
			    <li id="tab3">岗位角色</li>
				<li id="tab4">组织人员</li>
				-->
			</ul>	
		</td>
		</tr>
		<tr class="bottomTr">
		<td height="100">
		<iframe id="frame_selectowner" name="frame_selectowner" src="" frameborder="0" width="600" height="100"></iframe>
		</td>
		</tr>
		</table>
	</div>
		
	</div>
</div>
</form>

<script>

state_checkroute = "N";
state_checkroutemessage = "";

state_checkactowner = "N";
state_checkactownermessage = "";

var list_endacts = new Array(); // 可转发的目标活动
var c_rindex = 0; // 记录当前所选的目标活动;

var list_endactpersons = new Array();

<#list obj.routes as route>

list_endacts[${route_index}] = "${route.endactid}";
list_endactpersons[${route_index}] = new Array();

</#list>



function page_close()
{
	window.top.opener.top.location.reload();
	window.close();
}

function page_showflowdefine()
{
	url = "/pams/module/app/system/workflow/define/define_main.action?flowid=${obj.flowdefid}";
	openwinT(url,'define_viewprogress',pub_width_large,pub_height_large,null,'流程图');
}

// 选择路由
function page_selectroute(index, actdefid, ctype)
{
	c_rindex = index;
	
	// 如果是目标活动是结束节点，不需要选择人员
	if(ctype=="END")
	{
		$("#fs_persons").hide();
		return;
	}
	
	$("#fs_persons").show();
	$("#tab1").trigger("click");

}

function page_add_tr(pindex, obj)
{
	var len = $("#tb_persons tr").length;        
    var trHtml = "<tr>";
    trHtml += "<td><input class='checkbox' value='0' name='pindex' ownerctx='" + obj.ownerctx + "' cname='" + obj.cname + "' ctype='" + obj.ctype + "'></td>";
    trHtml += "<td>" + obj.cname + "</td>";
    trHtml += "</tr>";
    $("#tb_persons tbody").append(trHtml);
}

function page_clear()
{
	$("#tb_persons tbody").empty();
}

// 添加人员
function page_addperson()
{
	list_endactpersons[c_rindex] = new Array();
	var act = list_endactpersons[c_rindex];
	var num = 0;
	
	// 检查是否单选
	
	$("#tb_persons tbody tr", window.frames[0].document).each(function(j,k)
	{
		if($(this).attr("value")==1)
		{
			act[num] = new Array();
			act[num][0] = $(this).attr('ownerctx');
			act[num][1] = $(this).attr('ctype');
			act[num][2] = $(this).attr('cname'); 
			act[num][3] = $(this).attr('organid'); 
			act[num][4] = $(this).attr('organname'); 
			act[num][5] = $(this).attr('organtype'); 
			
			num++;
		}		
	})
	
	// 更新显示在人员列表
	$("#" + "actowner" + c_rindex).val("");
	
	var str_persons = "";
	for(i=0;i<act.length;i++)
	{
		str_persons += act[i][2];
		if(i<act.length-1)
		{
			str_persons += ",";
		}
	}
	
	$("#" + "actowner" + c_rindex).val(str_persons);
	
	$("#fs_persons", window.frames[0].document).hide();
		
}


function page_forward()
{
	var runactkey = "${obj.runactkey}";
	var user = "";
	var priority = "";
	var tableid = "${obj.tableid}";
	var dataid = "${obj.dataid}";
	var beginactdefid = "${obj.actdefid}";
	
	//添加完成转出后页面刷新功能参数
	var wf_formid = "";
	var wf_action = "";
	
	url = "/pams/module/app/system/workflow/ui/forward.action";
	
	// 检查路由选择规则是否正确
	page_checkroute();
	if(state_checkroute=="N")
	{
		alert(state_checkroutemessage);
		return;
	}
	
	// 检查目标节点人员选择规则是否正确
	page_checkactowner();
	if(state_checkactowner=="N")
	{
		alert(state_checkactownermessage);
		return;
	}

	// 循环检查路由是否选中
	// 选中路由的人员方可加入转发参数列表内
	var selectnum = 0;
	
	var json_forward = {runactkey:runactkey,endacts:[]};
	
	for(var i=0;i<list_endacts.length;i++)
	{
		if($("#tb_routes tbody .route:eq(" + i + ")").is(":checked")==false)
		{
			continue;
		}

		var act = list_endactpersons[i];
		
		json_forward.endacts[i] = {actdefid:list_endacts[i],actowners:[]};
		
		for(var j=0;j<act.length;j++)
		{
			json_forward.endacts[i].actowners[j] = {loginname:act[j][0], usertype:act[j][1], username:act[j][2], organid:act[j][3], organname:act[j][4], organtype:act[j][5]};
		}
		
		selectnum++;
	}
	
	if(selectnum==0)
	{
		return;
	}
	
	
	console.log(json_forward);
	
	console.log(JSON.stringify(json_forward));

		//获得数据
//	$.ajax({
//		url: '/pams/workflow/ui/forward.action',
//		type:'POST',
//		dataType: 'json',
//		data:JSON.stringify(json_forward),
//		success: function(d)
//		{
//			console.log("forward success.");
//		}
//	});
//	
	
	
	url = '/pams/workflow/ui/forward.action';
	aform.action = url;
	aform.vo.value = JSON.stringify(json_forward);
	// aform.target = "_blank";
	aform.submit();
	// window.open(url);
	
	// openwin(url,"forward",pub_width_mid,pub_height_mid);
	// window.location = url;
}

// 检查路由
// 多选一路由仅允许选择一条路由
// 全选路由必须全部选中
// 不允许未选择路由
function page_checkroute()
{
	state_checkroute = "N";
	state_checkroutemessage = "";
	var split = "${obj.bact.split}";
	var routenum = "${obj.routes?size}";
	var selectnum = 0;
	
	$("#tb_routes tbody .route").each(function(j,k)
	{
		if($(k).is(":checked"))
		{
			selectnum++;		
		}
	});
	
	if(routenum==0)
	{
		state_checkroute = "N"; //异常
		state_checkroutemessage = "流程异常，当前业务环节没有任何可选择的后续业务环节，请您联系系统管理人员。";
		return;
	}

	if(selectnum == 0)
	{
		state_checkroute = "N"; //异常
		state_checkroutemessage = "未选择要转发的后续业务环节，请您重新选择。";
		return;
	}
	
	if(split=="OR")
	{
		if(selectnum > 1)
		{
			state_checkroute = "N"; //异常
			state_checkroutemessage = "当前业务环节仅允许选择一项要转发的后续业务环节，请您重新选择。";
			return;
		}
	}
	
	if(split=="AND")
	{
		if(selectnum < routenum)
		{
			state_checkroute = "N"; //异常
			state_checkroutemessage = "当前业务环节必须选择所有的后续业务环节，请您重新选择。";
			return;	
		}
	}
	
	state_checkroute = "Y";
}

// 检查路由目标活动人员是否正确
// 普通活动必须选择人员
// 结束活动不用指定人员
function page_checkactowner()
{
	state_checkactowner = "N";
	state_checkactownermessage = "";
	
	var selectnum = 0;
	
	$("#tb_routes tbody .route").each(function(j,k)
	{
		var actctype = $("#tb_routes tbody .route:eq(" + j + ")").attr("acttype");
		
		if($(k).is(":checked"))
		{
			if(actctype=="END")
			{
				return true; 
			}
			
			if($("#tb_routes tbody .text:eq(" + j + ")").val()=="")
			{
				selectnum++;
			}
		}
	});
	
	if(selectnum>0)
	{
		state_checkactowner = "N"; //异常
		state_checkactownermessage = "后续业务环节必须指定人员，请您重新选择。";
		return;
	}
	
	state_checkactowner = "Y";
}


function page_load_actowner(actdefid)
{
	page_clear();
	
	$.ajax({
		url:'/pams/workflow/ui/selectownertoperson.action',
		type:'POST',
		data: {actdefid:actdefid},
		dataType: "json", 
		success:function(d)
		{
			$.each(d,function(key,obj)
			{
				page_add_tr(key, obj);
			});		
		}
	});
}

function page_toggle_node()
{
	$("#fs_node").toggle();
}

function page_toggle_routes()
{
	$("#fs_routes").toggle();
}

function page_toggle_persons()
{
	$("#fs_persons").toggle();
	if($("#fs_persons").is(":visible"))
	{
		$("#tab1").trigger("click");
	}
}

function page_load()
{
	$("#tb_routes .route:eq(0)").trigger("click");
	
	// 如果选择的节点是结束，自动转发.
	$("#tb_routes .route").length == 1
	{
		if ($("#tb_routes .route:eq(0)").attr("acttype") == "END")
		{
			page_forward();
		}
	}
}

page_toggle_node();
page_load();

</script>

</body>
</html>