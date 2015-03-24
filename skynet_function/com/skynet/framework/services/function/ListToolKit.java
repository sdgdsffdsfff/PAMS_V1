package com.skynet.framework.services.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.services.db.dybeans.DynamicObject;

public class ListToolKit
{

	public static void main(String[] args)
	{
		List a = new ArrayList();
		a.add("2");
		a.add("1");
		a.add("1");
		a.add("1");
		a.add("2");
		a.add("2");
		a.add("2");
		a.add("1");
		a.add("2");
		a.add("2");
		a.add("2");
		a.add("2");
		a.add("2");
		a.add("2");
		a.add("3");
		a.add("3");
		a.add("3");
		a.add("3");
		a.add("3");
		System.out.println(UniqueList(a));

	}

	// 矩阵行列转换
	public static List transRow2Col(List datas, String sname, String tname, String vname, List tvalues)
	{
		List newdatas = new ArrayList();
		Map smap = new HashMap();

		DynamicObject tobj = new DynamicObject();
		for (int i = 0; i < datas.size(); i++)
		{
			DynamicObject sobj = (DynamicObject) datas.get(i);
			String skey = sobj.getFormatAttr(sname);
			if (smap.get(skey) == null)
			{
				tobj = new DynamicObject();

				for (int j = 0; j < tvalues.size(); j++)
				{
					// tobj.setAttr((String)tvalues.get(j), "");
					tobj.setAttr(tname + j, "");
				}

				smap.put(skey, tobj);
				tobj.setAttr(sname, sobj.getFormatAttr(sname));
				newdatas.add(tobj);
			}
			else
			{
				tobj = (DynamicObject) smap.get(skey);
			}

			int num = 0;
			for(int j=0;j<tvalues.size();j++)
			{
				if(sobj.getFormatAttr(tname).equals((String)tvalues.get(j)))
				{
					num = j;
				}
			}
			
			// tobj.setAttr(sobj.getFormatAttr(tname), sobj.getFormatAttr(vname));
			tobj.setAttr(tname + num, sobj.getFormatAttr(vname));
		}
		return newdatas;
	}

	public static List UniqueList(List srclist)
	{
		List tarlist = new ArrayList();

		boolean isUnique;

		for (int j = 0; j < srclist.size(); j++)
		{
			isUnique = true;
			for (int k = 0; k < tarlist.size(); k++)
			{
				if (srclist.get(j).equals(tarlist.get(k)))
				{
					isUnique = false;
				}
			}

			if (isUnique)
			{
				tarlist.add(srclist.get(j));
			}

		}

		return tarlist;
	}
}
