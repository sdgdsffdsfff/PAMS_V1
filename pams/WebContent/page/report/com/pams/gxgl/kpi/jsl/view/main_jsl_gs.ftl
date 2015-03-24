<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/repinclude.ftl">
<script type="text/javascript">
jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})
</script>
<style type="text/css">
.sec1 {width:90%;float:left;margin-right:10px;}
</style>
</head>
<body><div id="gContainer">

<#import "/page/report/com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>
<@pub_macros.showheader repname="信息共享及时率指标统计" />

<#include "/page/report/com/ray/nwpn/itsm/report/common/view/navquery.ftl">

<div style="padding:10px;">
<form id="form_advsearch" method="post">
	<input type="hidden" name="begindate">
	<input type="hidden" name="enddate">
	<input type="hidden" name="internal" value="${obj.internal}">	
	<input type="hidden" name="orginternal" value="${obj.orginternal}">	
	<input type="hidden" name="reptype" value="${obj.reptype}">
</form>
</div>

<br />
<div class="section sec1">
<h2><span class="title tab_jsl">按公司统计</span><span class="more"></span></h2>
<div class="p8" id="tab_jsl">
</div>
</div>

<#include "/page/report/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_jsl_table()
{
	page_load_table("${base}/module/pams/gxgl/kpi/jsl/rep/tab_jsl_gs.action", "jsl", 0, new Array("internal","reptype"), new Array("${obj.internal}","${obj.reptype}"));
}

function page_load()
{
	page_load_jsl_table();
}

function page_query_export()
{
	form_advsearch.target = "_blank";
	form_advsearch.begindate.value = mform.begindate.value;
	form_advsearch.enddate.value = mform.enddate.value;
	form_advsearch.action = "${base}/module/pams/gxgl/kpi/jsl/rep/xls_jsl_gs.action";
	form_advsearch.submit();
}

page_load();

</script>

</div>
</body>
</html>
