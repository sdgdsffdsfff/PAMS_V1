package com.skynet.framework.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.dao.SkynetDao;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;

@InjectName("generatorService")
@IocBean
public class GeneratorService 
{
	@Inject
	Dao dao;
	
	public String getNextValue(Map map) throws Exception
	{
		String csql = (String) map.get("csql");
		String express = (String) map.get("express");

		String[] field_names = (String[]) map.get("field_names");
		String[] field_types = (String[]) map.get("field_types");
		String[] field_values = (String[]) map.get("field_values");

		Map fmap = new HashMap();
		
		Sql s = Sqls.create(csql);
		
		if (field_names != null)
		{
			for (int i = 0; i < field_names.length; i++)
			{
				fmap.put(field_names[i], field_values[i]);
				s.vars().set(field_names[i], field_values[i]);
			}
		}
		
		String text = ((SkynetDao)getDao()).queryForMap(csql).getFormatAttr("cno");
		
		int rsno = Types.parseInt(text, 0);

		map.put("rsno", String.valueOf(rsno + 1));

		return parseFormat(map);
	}

	public String parseFormat(Map map)
	{
		String express = (String) map.get("express");
		String rsno = (String) map.get("rsno");

		// 转换预定义参数值
		String ctime = (String) map.get("ctime");

		String timestr = StringToolKit.formatText(ctime, gen_time());
		express = express.replaceAll("\\$yyyy", timestr.substring(0, 4));
		express = express.replaceAll("\\$yy", timestr.substring(2, 4));
		express = express.replaceAll("\\$mm", timestr.substring(5, 7));
		express = express.replaceAll("\\$dd", timestr.substring(8, 10));

		String[] field_names = (String[]) map.get("field_names");
		String[] field_types = (String[]) map.get("field_types");
		String[] field_values = (String[]) map.get("field_values");

		if (field_names != null)
		{
			// 转换参数值
			for (int i = 0; i < field_names.length; i++)
			{
				express = express.replaceAll("\\$" + field_names[i], field_values[i]);
			}
		}

		// 转换流水号
		String str_pattern = "\\#+";

		Pattern p = Pattern.compile(str_pattern);
		Matcher m = p.matcher(express);

		if (m.find())
		{
			String str_result = m.group();
			if (str_result != null)
			{
				int len = str_result.length();
				express = express.replaceFirst(str_result, FormatKey.format(rsno, len));
			}
		}

		// 转换流水号，随机数规则
		str_pattern = "\\*+";

		p = Pattern.compile(str_pattern);
		m = p.matcher(express);

		if (m.find())
		{
			String str_result = m.group();
			if (str_result != null)
			{
				int len = str_result.length();
				Random random = new Random();
				String nsno = "";
				for (int i = 0; i < len; i++)
				{
					int r = random.nextInt(10);
					nsno += String.valueOf(r);
				}
				express = express.replace(str_result, FormatKey.format(nsno, len));
			}
		}

		return express;
	}

	public String gen_time()
	{
		Calendar ctime = new GregorianCalendar();

		String timestring = (ctime.get(Calendar.YEAR)) + "." + FormatKey.format(String.valueOf(ctime.get(Calendar.MONTH) + 1), 2) + "." + FormatKey.format(String.valueOf(ctime.get(Calendar.DATE)), 2) + "."
				+ FormatKey.format(String.valueOf(ctime.get(Calendar.HOUR_OF_DAY)), 2) + "." + FormatKey.format(String.valueOf(ctime.get(Calendar.MINUTE)), 2) + "." + FormatKey.format(String.valueOf(ctime.get(Calendar.SECOND)), 2) + "."
				+ (ctime.get(Calendar.MILLISECOND));

		return timestring;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
