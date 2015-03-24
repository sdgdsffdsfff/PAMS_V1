/*
 * 作者: 蒲剑
 * 
 * 创建日期: 2006-11-9
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

package com.skynet.framework.services.function;

import java.util.List;
import java.util.Map;

import com.skynet.framework.services.db.dybeans.DynamicObject;

public class DynamicToolKit
{
	public static DynamicObject find(List list, String key, String value)
	{
		DynamicObject returnobj = new DynamicObject();
		for(int i=0;i<list.size();i++)
		{
			DynamicObject obj = (DynamicObject)list.get(i);
			if(obj.getFormatAttr(key).equals(value))
			{
				returnobj = obj;
				break;
			}
		}

		return returnobj;
	}

	public static DynamicObject getIndex(List list, int index)
	{
		DynamicObject returnobj = new DynamicObject();
		if(list!=null)
		{
			if(index>=0 && list.size()>index)
			{
				// returnobj.putAll((Map)list.get(index));
				returnobj = (DynamicObject)list.get(index);
			}
		}
		
		return returnobj;
	}

}
