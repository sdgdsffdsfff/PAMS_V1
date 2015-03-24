package com.skynet.pams.report.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.skynet.framework.common.generator.TimeGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;

public class RepHelper
{
	public static int _MAXSIZE = 8;

	public static String EQ = "=";

	public static String LE = "<=";

	public static String GE = ">=";

	public static String LT = "<";

	public static String GT = ">";
	
	public static String to_time(String value)
	{
		return "to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')";
	}
	
	public static String to_time_end(String value)
	{
		return "to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') + 1 ";
	}

	public static String date_begin(String field, String value)
	{
		String sql = " and " + field + " > to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
		return sql;
	}

	public static String date_begin_eq(String field, String value)
	{
		String sql = " and " + field + " >= to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
		return sql;
	}

	public static String date_end(String field, String value)
	{
		String sql = " and " + field + " < to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') + 1 ";
		return sql;
	}

	public static String date_end_eq(String field, String value)
	{
		String sql = " and " + field + " <= to_date('" + value + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') + 1 ";
		return sql;
	}

	public static String date_begin_field_field(String field1, String field2)
	{
		String sql = " and to_char(" + field1 + ", 'yyyy-mm-dd hh24:mi:ss') > concat(to_char(" + field2 + ", 'yyyy-mm-dd'), ' 00:00:00') ";
		return sql;
	}

	public static String date_begin_eq_field_field(String field1, String field2)
	{
		String sql = " and to_char(" + field1 + ", 'yyyy-mm-dd hh24:mi:ss') >= concat(to_char(" + field2 + ", 'yyyy-mm-dd'), ' 00:00:00') ";
		return sql;
	}

	public static String date_end_field_field(String field1, String field2)
	{
		String sql = " and to_char(" + field1 + ", 'yyyy-mm-dd hh24:mi:ss') < concat(to_char(" + field2 + " + 1, 'yyyy-mm-dd'), ' 00:00:00') ";
		return sql;
	}

	public static String date_end_eq_field_field(String field1, String field2)
	{
		String sql = " and to_char(" + field1 + ", 'yyyy-mm-dd hh24:mi:ss') <= concat(to_char(" + field2 + " + 1, 'yyyy-mm-dd'), ' 00:00:00') ";
		return sql;
	}
	
	// 日期字段进行比较
	public static String date_compare_field_field(String field1, String field2, String comp)
	{
		String sql = " to_char(" + field1 + ", 'yyyy-mm-dd') " + comp + " to_char(" + field2 + ", 'yyyy-mm-dd') ";
		return sql;		
	}
	
	// 日期字段进行比较
	public static String date_compare_field_value(String field, String value, String comp)
	{
		String sql = " to_char(" + field + ", 'yyyy-mm-dd') " + comp + " to_char(to_date('" + value + "', 'yyyy-mm-dd'), 'yyyy-mm-dd') ";
		return sql;		
	}
	
	
	public static String compare_sysdate(String enddate) throws Exception
	{
		String sql_cdate = " sysdate ";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		int daymillis = 24 * 60 * 60 * 1000;
		
	    if((df.parse(enddate).getTime() + daymillis) <System.currentTimeMillis())
    	{
	    	sql_cdate = RepHelper.to_time_end(enddate);
    	}
	    
	    return sql_cdate;
	}

	public static List<String> get_dates(String begindate, String enddate) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal_begin = new GregorianCalendar();
		cal_begin.setTime(df.parse(begindate));

		Calendar cal_end = new GregorianCalendar();
		cal_end.setTime(df.parse(enddate));

		List dates = new ArrayList();
		while (!cal_begin.after(cal_end))
		{
			dates.add(TimeGenerator.getDateStr(cal_begin));
			cal_begin.add(Calendar.DATE, 1);
		}

		return dates;
	}

	// 获取日期功能
	public static List<DynamicObject> get_dateobjs(String begindate, String enddate, String fieldname) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal_begin = new GregorianCalendar();
		cal_begin.setTime(df.parse(begindate));

		Calendar cal_end = new GregorianCalendar();
		cal_end.setTime(df.parse(enddate));

		List<DynamicObject> dates = new ArrayList();
		while (!cal_begin.after(cal_end))
		{
			DynamicObject aobj = new DynamicObject();
			aobj.setAttr(fieldname, TimeGenerator.getDateStr(cal_begin));
			dates.add(aobj);
			cal_begin.add(Calendar.DATE, 1);
		}

		return dates;
	}

	public static List<DynamicObject> get_dateobjs(String begindate, String enddate, String fieldname, String[] valuenames) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal_begin = new GregorianCalendar();
		cal_begin.setTime(df.parse(begindate));

		Calendar cal_end = new GregorianCalendar();
		cal_end.setTime(df.parse(enddate));

		List<DynamicObject> dates = new ArrayList();
		while (!cal_begin.after(cal_end))
		{
			DynamicObject aobj = new DynamicObject();
			aobj.setAttr(fieldname, TimeGenerator.getDateStr(cal_begin));
			for (int i = 0; i < valuenames.length; i++)
			{
				aobj.setAttr(valuenames[i], "0");
			}
			dates.add(aobj);
			cal_begin.add(Calendar.DATE, 1);
		}

		return dates;
	}

	public static void set_date_fields(List<DynamicObject> dates, List<DynamicObject> datas, String sdatefield, String ddatefield, String seriesname, String seriesvalue, String[] sfields, String[] dfields)
	{
		for (int j = 0; j < datas.size(); j++)
		{
			for (int i = j; i < dates.size(); i++)
			{
				DynamicObject aobj = dates.get(i);
				if (datas.get(j).getFormatAttr(sdatefield).equals(aobj.getFormatAttr(ddatefield)))
				{
					// 设置序列名及值
					aobj.setAttr(seriesname, seriesvalue);

					for (int k = 0; k < dfields.length; k++)
					{
						aobj.setAttr(dfields[k], datas.get(j).getFormatAttr(sfields[k]));
					}
					break;
				}
			}
		}
	}

	public static List get_sub_rows(List datas, int _maxsize)
	{
		if (_maxsize == -1)
		{
			return datas;
		}

		if (datas.size() > _maxsize)
		{
			datas = datas.subList(0, _maxsize);
		}

		return datas;
	}
	
	public static HSSFWorkbook poiexcel(String[] cnames, String[] pnames, List datas) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		
		 HSSFRow row = sheet.createRow(0); 	//创建第1行，也就是输出表头
		 HSSFCell cell;
		 for(int i=0;i<cnames.length;i++)
		 {
		   cell = row.createCell(i);
		   cell.setCellValue(new HSSFRichTextString(cnames[i]));
		 }
		 
		 for (int i = 0; i < datas.size(); i++) 
		 {
			   Map exp = (Map)datas.get(i);
			   row = sheet.createRow(i+1);//创建第i+1行
			   for(int j=0;j<cnames.length;j++)
			   {
				    cell=row.createCell(j);//创建第j列
				    Object obj = exp.get(pnames[j]);
				    cell.setCellValue(obj.toString());
			   }
		 }
	
		 return workbook;
	}
	
	public static void main(String[] args) throws Exception
	{
		List dates = RepHelper.get_dates("2011-01-01", "2011-12-31");
		for (int i = 0; i < dates.size(); i++)
		{
			System.out.println(i + ":" + dates.get(i));
		}
	}
}
