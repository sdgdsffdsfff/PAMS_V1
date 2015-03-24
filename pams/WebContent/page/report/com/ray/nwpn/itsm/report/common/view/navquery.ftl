<form id="mform" method="post">
<input type="hidden" name="ctype" id="ctype">
<input type="hidden" name="cdate" id="cdate">
<div id="site-nav">
<ul>
	<li class="changetype current c" onclick="page_settype('D')" class="current">日统计</li>
	<li class="changetype" onclick="page_settype('W')">周统计</li>
	<li class="changetype" onclick="page_settype('M')">月统计</li>
	<li class="changetype" onclick="page_settype('Q')">季统计</li>
	<li class="changetype" onclick="page_settype('HY')">半年统计</li>
	<li class="changetype" onclick="page_settype('Y')">年统计</li>
	<li class="arrow"><img src="${base}/themes/default/images/arrow_left.gif" onclick="page_pre_date()"></li>
	<li class="arrow"><img src="${base}/themes/default/images/arrow_right.gif" onclick="page_next_date()"></li>
	<li class="title" id="begindatetitle">起始日期：</li><li class="datesel"><input class="date" name="begindate" id="begindate" /></li>
	<li class="title" id="enddatetitle">结束日期：</li><li class="datesel"><input class="date" name="enddate" id="enddate" /></li>
	<li class="title"><button onclick="page_query_chart()"> 查询</button></li>
	<li class="title"><button onclick="page_query_export()">&nbsp;导&nbsp;出&nbsp;</button></li>
</ul>
</div>
</form>