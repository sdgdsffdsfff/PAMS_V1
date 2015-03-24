// 常量定义 

pub_maxyear = 2100;
pub_minyear = 1900;

pub_year = 0;
pub_month = 1;
pub_date = 2;
pub_hour = 3;
pub_minute = 4;
pub_second = 5;

// 缺省界面(16v9)
pub_width_mini = 160;
pub_height_mini = 90;

pub_width_small = 320;
pub_height_small = 180;

pub_width_mid = 480;
pub_height_mid = 270;

pub_width_large = 640;
pub_height_large = 360;

pub_width_extend = 800
pub_height_extend = 450;

// 16v9界面
pub_width_mini_16v9 = 160;
pub_height_mini_16v9 = 90;

pub_width_small_16v9 = 320;
pub_height_small_16v9 = 180;

pub_width_mid_16v9 = 480;
pub_height_mid_16v9 = 270;

pub_width_large_16v9 = 640;
pub_height_large_16v9 = 360;

pub_width_extend_16v9 = 800
pub_height_extend_16v9 = 450;

// 5v3界面
pub_width_mini_5v3 = 200;
pub_height_mini_5v3 = 120;

pub_width_small_5v3 = 400;
pub_height_small_5v3 = 240;

pub_width_mid_5v3 = 600;
pub_height_mid_5v3 = 360;

pub_width_large_5v3 = 800;
pub_height_large_5v3 = 480;

pub_width_extend_5v3 = 1000;
pub_height_extend_5v3 = 600;

// 5v2界面
pub_width_mini_5v2 = 200;
pub_height_mini_5v2 = 80;

pub_width_small_5v2 = 400;
pub_height_small_5v2 = 160;

pub_width_mid_5v2 = 600;
pub_height_mid_5v2 = 240;

pub_width_large_5v2 = 800;
pub_height_large_5v2 = 320;

pub_width_extend_5v2 = 1000;
pub_height_extend_5v2 = 400;

// 新加6v2界面
pub_width_small_6v2 = 800;
pub_height_small_6v2 = 400;

pub_width_large_6v2 = 980;
pub_height_large_6v2 = 500;

pub_width_extend_6v2 = 1090;
pub_height_extend_6v2 = 580;

pub_webroot = "/ray_nwpn_itsm";
pub_workflow_webroot = "/ray_nwpn_itsm";

// 数值运算小数精确位数 货币
pub_precision_money = 2;

// 数值运算小数精确位数 计量
pub_precision_weight = 3;

// 公用函数定义

// 计算指定年月最大天数
function pub_days_max(cyear, cmonth) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if ((cyear % 400 == 0) || (cyear % 4 == 0 && cyear % 100 != 0)) {
		days[1] = 29;
	}
	return days[cmonth - 1];
}

// 货币大小写转换
function pub_chinese(num) {

	num = num / 1000.0;

	if (!/^\d*(\.\d*)?$/.test(num))
		throw (new Error(-1, "Number is wrong!"));

	var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖");

	var BB = new Array("", "拾", "佰", "仟", "萬", "億", "点", "");

	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = "";

	for ( var i = a[0].length - 1; i >= 0; i--) {
		switch (k) {
		case 0:
			re = BB[7] + re;
			break;
		case 4:
			if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$")
					.test(a[0]))
				re = BB[4] + re;
			break;
		case 8:
			re = BB[5] + re;
			BB[7] = BB[5];
			k = 0;
			break;
		}
		if (k % 4 == 2 && a[0].charAt(i) == "0" && a[0].charAt(i + 2) != "0")
			re = AA[0] + re;

		if (a[0].charAt(i) != 0)
			re = AA[a[0].charAt(i)] + BB[k % 4] + re;
		k++;

	}
	if (a.length > 1) {
		re += BB[6];
		for ( var i = 0; i < a[1].length; i++) {
			re += AA[a[1].charAt(i)];
			if (i == 2)
				break;

		}
		if (a[1].charAt(0) == "0" && a[1].charAt(1) == "0") {
		}
	} else {
	}
	return re;
}

// 窗口
function pub_openunresizablewindow(page, width, height) {
	var pheight = screen.height;
	var pwidth = screen.width;
	var newwidth = width;
	var newheight = height;
	var x = (pwidth - newwidth) / 2 - 10;
	var y = (pheight - newheight) / 2 - 25;
	mywin = window
			.open(
					page,
					'_blank',
					'height='
							+ newheight
							+ ', width='
							+ newwidth
							+ ', left='
							+ x
							+ ', top='
							+ y
							+ ', toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no');
	mywin.focus();
}

// 通用查询排序函数
function page_sort(action, tag, field) {
	if (document.form_view._sortfield.value != field) {
		tag = "desc";
	} else {
		if (tag == "desc") {
			tag = "asc";
		} else {
			tag = "desc";
		}
	}

	document.form_view.action = action;
	document.form_view._sorttag.value = tag;
	document.form_view._sortfield.value = field;
	document.form_view.submit();
}

// 通用查询 翻页函数
function pub_query_selectpage(pagenum) {
	document.all.form_view._currentpage.value = pagenum;
}

function pub_query_go2page(url) {
	document.all.form_view.action = url;
	document.all.form_view.submit();
}

function pub_query_jump2page(pagenum, url) {
	if (event.keyCode != 13) {
		return;
	}

	document.all.form_view._currentpage.value = pagenum;
	document.all.form_view.action = url;
	document.all.form_view.submit();
}

function pub_void() {
}

// 弹出式窗口
function pub_popwin(url, width, height, title, path) {
	gPopWin(url, width, height, title, path);
}

// 日期函数
function pub_date_format_value(cdate) {
	year = cdate.getFullYear();
	month = cdate.getMonth() + 1;
	day = cdate.getDate();

	if (month < 10) {
		month = "0" + month;
	}

	if (day < 10) {
		day = "0" + day;
	}

	adate = year + "-" + month + "-" + day;
	return adate;
}

// 字符串转换为日期
function pub_string_to_date(cvalue) {
	fcvalue = cvalue.replace(/-/g, "/");
	adate = new Date(Date.parse(fcvalue));
	return adate;
}

// 计算指定时差日期
function pub_date_add(cdate, field, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate());

	if (field == "D") {
		adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate()
				+ nums);
		return adate;
	}

	if (field == "M") {
		adate = new Date(adate.getFullYear(), adate.getMonth() + (nums), adate
				.getDate());
		return adate;
	}

	if (field == "Y") {
		adate = new Date(adate.getFullYear() + nums, adate.getMonth(), adate
				.getDate());
		return adate;
	}
}

// 获得天的开始日期
function pub_day_begin_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() + nums);
	return adate;
}

// 获得天的开始日期
function pub_day_end_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate() + nums);
	return adate;
}

// 获得周的开始日期
function pub_week_begin_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate()
			- cdate.getDay() + 1);
	adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate()
			+ (nums * 7));
	return adate;
}

// 获得周的结束日期
function pub_week_end_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), cdate.getDate()
			+ (6 - cdate.getDay()) + 1);
	adate = new Date(adate.getFullYear(), adate.getMonth(), adate.getDate()
			+ (nums * 7));
	return adate;
}

// 获得月的开始日期
function pub_month_begin_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth(), 1);
	adate = new Date(adate.getFullYear(), adate.getMonth() + (nums), adate
			.getDate());
	return adate;
}

// 获得月的结束日期
function pub_month_end_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), cdate.getMonth() + (nums), 1);
	maxday = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
	adate = new Date(adate.getFullYear(), adate.getMonth(), maxday);
	return adate;
}

// 获得季度的开始日期
function pub_quarter_begin_date(cdate, nums) {
	adate = new Date(cdate.getFullYear(), get_quarter_begin_month(cdate), 1);
	adate = new Date(adate.getFullYear(), adate.getMonth() + (nums * 3), 1);
	return adate;
}

// 获得季度的结束日期
function pub_quarter_end_date(cdate, nums) {
	quarter_end_month = get_quarter_begin_month(cdate) + 2;
	adate = new Date(cdate.getFullYear(), quarter_end_month, 1);
	adate = new Date(adate.getFullYear(), adate.getMonth() + (nums * 3), 1);

	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
	adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);
	return adate;
}

// 获得半年的开始日期
function pub_halfyear_begin_date(cdate, nums) {
	cmonth = 0;
	if (cdate.getMonth() >= 6) {
		cmonth = 6;
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 6), 1);
	return adate;
}

// 获得半年的结束日期
function pub_halfyear_end_date(cdate, nums) {
	cmonth = 5;
	if (cdate.getMonth() > 5) {
		cmonth = 11;
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 6), 1);

	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
	adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);

	return adate;
}

// 获得年的开始日期
function pub_year_begin_date(cdate, nums) {
	cmonth = 0;
	if (cdate.getMonth() >= 12) {
		cmonth = 12;
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 12), 1);
	return adate;
}

// 获得年的结束日期
function pub_year_end_date(cdate, nums) {
	cmonth = 12;
	if (cdate.getMonth() > 12) {
		cmonth = 0;
	}

	adate = new Date(cdate.getFullYear(), cmonth + (nums * 12), 1);

	max_day = pub_max_day_of_month(adate.getFullYear(), adate.getMonth());
	adate = new Date(adate.getFullYear(), adate.getMonth(), max_day);

	return adate;
}

// 计算本月的最大天数
function pub_max_day_of_month(cyear, cmonth) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((cyear % 4 == 0) && (cyear % 100 != 0)) || (cyear % 400 == 0)) {
		days[1] = "29";
	}
	return days[cmonth];
}

// 计算本季度的开始月份
function get_quarter_begin_month(cdate) {
	now_month = cdate.getMonth();

	var quarter_begin_month = 0;

	if (now_month < 3) {
		quarter_begin_month = 0;
	}
	if (2 < now_month && now_month < 6) {
		quarter_begin_month = 3;
	}
	if (5 < now_month && now_month < 9) {
		quarter_begin_month = 6;
	}
	if (now_month > 8) {
		quarter_begin_month = 9;
	}
	return quarter_begin_month;
}