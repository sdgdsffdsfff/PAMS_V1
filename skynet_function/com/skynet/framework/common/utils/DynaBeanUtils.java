/*
 * 作者: 蒲剑
 * 
 * 创建日期: 2006-8-3
 * 
 * 邮件: skynetbird@126.com
 * 
 * MSN: skynetbird@hotmail.com
 * 
 * 版权：陕西汉瑞科技信息有限公司
 *
 * -----------------------------------------
 * Hello, i'm skynetbird, software designer.
 * ----------------------------------------- 
 *
 */

package com.skynet.framework.common.utils;

import java.util.List;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;

public class DynaBeanUtils
{
	public static void copyProperties(Object dest, DynamicObject orig) throws Exception
	{
		if (dest == null)
		{
			throw new Exception("No destination bean specified");
		}
		if (orig == null)
		{
			throw new Exception("No origin bean specified");
		}

		List attrnames = orig.getAttrNames();
		for (int i = 0; i < attrnames.size(); i++)
		{
			String attrname = (String) attrnames.get(i);
			String attrvalue = (String) orig.getObj(attrname);
			Class attrclass = dest.getClass().getDeclaredField(attrname).getClass();

			dest.getClass().getField(attrname).set(dest, Types.parse(attrclass, attrvalue));
		}
	}

}
