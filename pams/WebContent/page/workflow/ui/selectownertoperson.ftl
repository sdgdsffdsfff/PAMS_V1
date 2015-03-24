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
<#--
<div id="fixedOp">
<button id="bt_addperson" class="btn2">确定</button>
</div>
-->

<table id="tb_persons" class="table personListTable hover">
<thead>
	<tr>
	<th width="1"></th>
	<th>部门</th>
	<th>姓名</th>
	<th>办公电话</th>
	<th>职务</th>
	</tr>
</thead>
<tbody>
	<#list obj.owners as p>
	<tr name="pindex" ownerctx="${p.ownerctx}" cname="${p.cname}" ctype="${p.ctype}" organid="${p.organid}" organname="${p.organname}" organtype="${p.organtype}" value="0">
	<td class="check"></td>
	<td>${p.organname}</td>
	<td>${p.cname}</td>
	<td></td>
	<td></td>
	</tr>
	</#list>
</tbody>
</table>

<script>

$("#tb_persons tbody").on("click","tr", function(){
	
	if(!$(this).hasClass('selected'))
	{
		$(this).addClass('selected');
		$(this).attr("value", 1);
	}
	else
	{
		$(this).removeClass('selected');
		$(this).attr("value", 0);
	}
	
	page_addperson();
})

$("#bt_addperson").click(function() {page_addperson()});

function page_addperson()
{
	window.parent.page_addperson();
}

// 自动加人
function page_autoaddperson()
{
	if($("#tb_persons tbody tr").length>=1)
	{
		// alert($("#tb_persons tbody tr:eq(0) .checkbox").attr("ownerctx"));
		$("#tb_persons tbody tr:eq(0)").addClass("selected");
		$("#tb_persons tbody tr:eq(0)").attr("value", "1");
		window.parent.page_addperson();
	}
}

page_autoaddperson();

</script>

</body>
</html>