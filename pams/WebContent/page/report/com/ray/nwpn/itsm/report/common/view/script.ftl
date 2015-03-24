<script>
<#--

<#assign optionsmonth = stack.findValue("@com.ray.app.query.function.view.SQLSelect@get_data(\"select new map(a.dvalue as cvalue, a.dtext as ctext) from Dictionary a where a.dkey = 'system.workdate.workmonth' \")") >
<#assign optionsweek = stack.findValue("@com.ray.app.query.function.view.SQLSelect@get_data(\"select new map(a.dvalue as cvalue, a.dtext as ctext) from Dictionary a where a.dkey = 'system.workdate.workweek' \")") >


// 系统初始化变量;
var _sys_month = Number("${optionsmonth[0].cvalue}");
var _sys_week = Number("${optionsweek[0].cvalue}");;

-->
var _sys_month = 26;
var _sys_week = 5;

var _cdate = new Date();   // 系统当前日期;
var _ctype = "D";          // 日期周期类型;                     
var _begindate = _cdate;    // 起始日期;
var _enddate = _cdate;      // 结束日期;  


// 如果页面传递日期，应记录之前的记录日期

if("${obj.begindate}"!="")
{
	_begindate = pub_string_to_date("${obj.begindate}");	
}

if("${obj.enddate}"!="")
{
	_enddate = pub_string_to_date("${obj.enddate}");	
}

var mform=document.getElementById('mform')

mform.ctype.value = _ctype;
mform.cdate.value = pub_date_format_value(_cdate);
mform.begindate.value = pub_date_format_value(_begindate);
mform.enddate.value = pub_date_format_value(_enddate);

function page_settype(ctype)
{
	page_settypevalue(ctype);
	
	page_load();
}

function page_settypevalue(ctype)
{
	
	_ctype = ctype;
	var mform=document.getElementById('mform')
	
	xdate = new Date(Date.parse(mform.cdate.value));
	ydate = new Date(Date.parse(mform.cdate.value));	

	if("D" == _ctype)
	{
		_begindate = _cdate;
		_enddate = _cdate;
	}
	
	if("W" == _ctype)
	{
		// 自然周日期
		_begindate = (pub_week_begin_date(_cdate, 0));
		_enddate = (pub_week_end_date(_cdate, 0));
		
		// 工作周日期
		_begindate = pub_work_week_begin_date(xdate, _sys_week);
		_enddate = pub_work_week_end_date(ydate, _sys_week);	
		
	}	
	
	if("M" == _ctype)
	{
		// 自然月日期
		_begindate = (pub_month_begin_date(_cdate, 0));
		_enddate = (pub_month_end_date(_cdate, 0));
		
		// 工作月日期
		_begindate = pub_work_month_begin_date(xdate, _sys_month);
		_enddate = pub_work_month_end_date(ydate, _sys_month);
	}
	
	if("Q" == _ctype)
	{
		//自然季度日期
		_begindate = (pub_quarter_begin_date(_cdate, 0));
		_enddate = (pub_quarter_end_date(_cdate, 0));
		
		// 工作季度日期
		_begindate = pub_work_quarter_begin_date(xdate, _sys_month);
		_enddate = pub_work_quarter_end_date(ydate, _sys_month);			
		
	}
	
	if("T" == _ctype)
	{
		if(mform.begindate.value!="")
		{
			_begindate = new Date(Date.parse(mform.begindate.value));
		}
		if(mform.enddate.value!="")
		{
			_enddate = pub_string_to_date(mform.enddate.value);
		}
		
	}
	
	if("HY" == _ctype)
	{
		//自然半年日期
		_begindate = (pub_halfyear_begin_date(_cdate, 0));
		_enddate = (pub_halfyear_end_date(_cdate, 0));	
		
		// 工作半年日期
		_begindate = pub_work_halfyear_begin_date(xdate,_sys_month);
		_enddate = pub_work_halfyear_end_date(ydate,_sys_month);
	}
	
	if("Y" == _ctype)
	{
		//自然年日期
		_begindate = (pub_year_begin_date(_cdate, 0));
		_enddate = (pub_year_end_date(_cdate, 0));	
		
		// 工作年日期
		_begindate = pub_work_year_begin_date(xdate,_sys_month);
		_enddate = pub_work_year_end_date(ydate,_sys_month);
	}

	mform.ctype.value = _ctype;
	mform.begindate.value = pub_date_format_value(_begindate);
	mform.enddate.value = pub_date_format_value(_enddate);

}

function page_pre_date()
{
	if("D" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "D", -1);
		_enddate = pub_date_add(_enddate, "D", -1);
	}
	
	if("W" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "D", -7);
		_enddate = pub_date_add(_enddate, "D", -7);
	}
	
	if("M" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", -1);
		_enddate = pub_date_add(_enddate, "M", -1);
	}
	
	if("Q" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", -3);
		_enddate = pub_date_add(_enddate, "M", -3);
	}
		
	if("HY" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", -6);
		_enddate = pub_date_add(_enddate, "M", -6);
	}

	if("Y" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", -12);
		_enddate = pub_date_add(_enddate, "M", -12);
	}

	mform.begindate.value = pub_date_format_value(_begindate);
	mform.enddate.value = pub_date_format_value(_enddate);	
	
	page_load();
}

function page_next_date()
{
	if("D" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "D", 1);
		_enddate = pub_date_add(_enddate, "D", 1);
	}
	
	if("W" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "D", 7);
		_enddate = pub_date_add(_enddate, "D", 7);
	}
	
	if("M" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", 1);
		_enddate = pub_date_add(_enddate, "M", 1);
	}
	
	if("Q" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", 3);
		_enddate = pub_date_add(_enddate, "M", 3);
	}
		
	if("HY" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", 6);
		_enddate = pub_date_add(_enddate, "M", 6);
	}

	if("Y" == _ctype)
	{
		_begindate = pub_date_add(_begindate, "M", 12);
		_enddate = pub_date_add(_enddate, "M", 12);
	}			

	mform.begindate.value = pub_date_format_value(_begindate);
	mform.enddate.value = pub_date_format_value(_enddate);
	
	page_load();	
}


function pub_work_halfyear_begin_date(xdate, sys_month)
{
	adate = xdate;
	if(xdate.getMonth() >= 5)
	{
		if((xdate.getMonth()==11)&&(xdate.getDate() > sys_month))
		{
			adate.setDate(_sys_month);
			adate.setMonth(11);
		}
		else
		{
			adate.setDate(_sys_month);
			adate.setMonth(5);
		}
	}
	else
	{
		if((xdate.getMonth()==5)&&(xdate.getDate() > sys_month))
		{
			adate.setDate(_sys_month);
			adate.setMonth(5);
		}
		else
		{
			adate.setDate(_sys_month);
			adate.setYear(adate.getFullYear()-1);
			adate.setMonth(11);
		}
	}
	return adate;
}

function pub_work_halfyear_end_date(xdate, sys_month)
{
	adate = xdate;
	if(xdate.getMonth() >= 5)
	{
		if((xdate.getMonth()==11)&&(xdate.getDate() > sys_month))
		{
			adate.setDate(_sys_month-1);
			adate.setYear(xdate.getFullYear()+1);
			adate.setMonth(5);
		}
		else
		{
			adate.setDate(_sys_month-1);
			adate.setMonth(11);
		}
	}
	else
	{	
		if((xdate.getMonth()==5)&&(xdate.getDate() >= sys_month))
		{
			adate.setDate(_sys_month-1);
			adate.setMonth(11);
		}
		else
		{
			adate.setDate(_sys_month-1);
			adate.setMonth(5);
		}
	}
	return adate;
}

function pub_work_quarter_begin_date(xdate, sys_month)
{
	xdate = pub_quarter_begin_date(xdate, 0);
	adate = xdate;
	
	if(xdate.getMonth() == 0)
	{
		adate.setDate(sys_month);
		adate.setYear(xdate.getFullYear() - 1);
		adate.setMonth(11);
	}
	else
	if(xdate.getMonth() == 3)
	{
		adate.setDate(sys_month);
		adate.setMonth(2);
	}
	else
	if(xdate.getMonth() == 6)
	{
		adate.setDate(sys_month);
		adate.setMonth(5);
	}
	else
	if(xdate.getMonth() == 9)
	{
		adate.setDate(sys_month);
		adate.setMonth(8);
	}

	return adate;
}

function pub_work_quarter_end_date(xdate, sys_month)
{
	xdate = pub_quarter_end_date(xdate, 0);
	adate = xdate;
		
	if(xdate.getMonth() == 2)
	{
		adate.setDate(sys_month - 1);
	}
	else
	if(xdate.getMonth() == 5)
	{
		adate.setDate(sys_month - 1);
	}
	else
	if(xdate.getMonth() == 8)
	{
		adate.setDate(sys_month - 1);
	}
	else
	if(xdate.getMonth() == 11)
	{
		adate.setDate(sys_month - 1);
	}	
	
	return adate;
}

function pub_work_year_begin_date(xdate,sys_month)
{
	adate = xdate;
	if((xdate.getMonth()==11)&&(xdate.getDate() > sys_month))
	{
		adate.setDate(sys_month);
	}
	else
	{
		adate.setDate(sys_month);
		adate.setMonth(-1);
	}
	return adate;
}


function pub_work_year_end_date(xdate,sys_month)
{
	adate = xdate;

	if((xdate.getMonth()==11)&&(xdate.getDate() > sys_month))
	{
		adate.setDate(sys_month-1);
		adate.setMonth(12);
	}
	else
	{
		adate.setDate(sys_month-1);
		adate.setMonth(11);
	}
	
	return adate;
}

function pub_work_month_begin_date(xdate, sys_month)
{
	adate = xdate;
	if(xdate.getDate() < sys_month)
	{
		days = xdate.getMonth() - 1;
		adate.setDate(sys_month);
		adate.setMonth(days);
	}
	else
	{
		adate.setDate(sys_month);
	}
	return adate;
}

function pub_work_month_end_date(xdate, sys_month)
{
	adate = xdate;
	if(xdate.getDate() >= sys_month)
	{
		days = xdate.getMonth() + 1;
		adate.setDate(sys_month-1);
		adate.setMonth(days);
	}
	else
	{
		adate.setDate(sys_month-1);
	}
	return adate;
}

function pub_work_week_begin_date(xdate, sys_week)
{
	adate = xdate;
	if(xdate.getDay() < sys_week)
	{
		days = xdate.getDate() - xdate.getDay() - 2;
		adate.setDate(days);
	}
	else
	{
		days = xdate.getDate() - xdate.getDay() + 5;
		adate.setDate(days);
	}

	return adate;
}

function pub_work_week_end_date(xdate, sys_week)
{
	adate = xdate;
	if(xdate.getDay() < sys_week)
	{
		days = xdate.getDate() + (6 - xdate.getDay()) - 2;
		adate.setDate(days);
	}
	else
	{
		days = xdate.getDate() + (6 - xdate.getDay()) + 5;
		adate.setDate(days);
	}
	
	return adate;
}


function page_query_chart()
{
	page_settypevalue("T");
	page_load();
}

function page_load_chart(chartname, chartid, nums, charttype, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	url = "${base}/chart/chart_main.action";
	url += "?_chartname=" + chartname;
	url += "&_div=" + chartid;
	url += "&_type=" + charttype;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	// window.open(url);
	
	$('#rep_' + chartid).load(url);
	
}

function page_load_charttest(chartname, chartid, nums, charttype, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	url = "${base}/chart/chart_main.action";
	url += "?_chartname=" + chartname;
	url += "&_div=" + chartid;
	url += "&_type=" + charttype;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	// window.location = url;
	$('#rep_' + chartid).load(url);
}

function page_load_charthis(chartname, chartid, nums, charttype, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	url = "${base}/chart/charthis_main.action";
	url += "?_chartname=" + chartname;
	url += "&_div=" + chartid;
	url += "&_type=" + charttype;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	// window.location = url;
	$('#rep_' + chartid).load(url);
}

function page_load_chartct(chartname, chartid, nums, charttype, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	field = "D";
	snums = -1;
	
	field = _ctype;
	
	if(_ctype == "W")
	{
		field = "D";
		snums = snums * 7;
	}
	
	if(_ctype == "Q")
	{
		field = "M";
		snums = snums * 3;
	}

	if(_ctype == "HY")
	{
		field = "M";
		snums = snums * 6;	
	}
	
	begindate1 = pub_date_format_value(pub_date_add(_begindate, field, snums));
	enddate1 = pub_date_format_value(pub_date_add(_enddate, field, snums));
	
	url = "${base}/chart/chartct_main.action";
	url += "?_chartname=" + chartname;
	url += "&_div=" + chartid;
	url += "&_type=" + charttype;　　
	
	url += "&random=" + Math.random();
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;
	url += "&begindate1=" + begindate1;
	url += "&enddate1=" + enddate1;

	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	$('#rep_' + chartid).load(url);
}

function page_load_table(url, chartid, nums, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	url += "?_div=" + chartid;
	url += "&random=" + Math.random();	
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;

	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	/*$('#tab_' + chartid).load(url,function(d){
		console.log($(d));
	});
	*/
	$.ajax({
		url:url,
		success:function(d){
			var tb=$(d);
			$('#tab_' + chartid).empty().append(tb);
			
			//如果不是 repgGrid table 不自动处理前10 tr
			if(!tb.hasClass('repgGrid')){return false;}
			
			if(tb.find('tr').length>10){
				tb.find('tr:gt(9)').addClass('morethan10');
				tb.after('<a class="top10More" href="javascript:void(0);">显示更多</a>');
				
			}
		}
	})
	
}

function page_open_load_table(url, chartid, nums, names, values)
{
	begindate = pub_date_format_value(_begindate);
	enddate = pub_date_format_value(_enddate);
	
	url += "?_div=" + chartid;
	url += "&random=" + Math.random();	
	url += "&begindate=" + begindate;
	url += "&enddate=" + enddate;
	
	for(i=0;i<names.length;i++)
	{
		url += "&" + names[i] + "=" + values[i];
	}
	
	//$('#tab_' + chartid).load(url);
	//window.open(url);
	openwin(url,'',pub_width_large,pub_height_large)
}

jQuery(function($){
///////////////////////////////////

$('#site-nav li').click(function(){
	if(!$(this).hasClass('changetype')){return false;}
	$('#site-nav li').removeClass('c');
	$(this).addClass('c')
})



///////////////////////////////////
})
</script>
