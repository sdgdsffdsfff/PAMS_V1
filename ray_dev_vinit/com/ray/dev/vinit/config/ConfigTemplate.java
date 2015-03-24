package com.ray.dev.vinit.config;

import java.util.List;

import com.headray.framework.common.generator.UUIDGenerator;
import com.headray.framework.services.db.SQLParser;
import com.headray.framework.services.db.dybeans.DynamicObject;
import com.headray.framework.services.function.StringToolKit;

public class ConfigTemplate
{
	public static int _cname = 0; // 属性中文名称

	public static int _pname = 1; // 属性英文名称

	public static int _configitemclass = 2; // 属性、配置子项、配置子项属性

	public static int _defvalue = 3; // 预设值

	public static int _width = 4; // 显示宽度

	public static void main(String[] args)
	{
		// out_zj();
		//out_yyxt();
		//out_dpjx();
		//out_wlsb();
		//out_ccsb();
		//out_zjj();
		//out_rjzc();
		//out_sjk();
		//out_xnzj();
		//out_xnpt();
		//out_yysl();
		//out_qtrj();
		//out_czxt();
		//out_sjksl();

		//imp_sx_zj();
		//imp_sx_yyxt();
		//imp_sx_dpjx();
		//imp_sx_wlsb();
		//imp_sx_ccsb();
		imp_sx_zjj();
		//imp_sx_rjzc();
		//imp_sx_sjk();
		//imp_sx_xnzj();
		//imp_sx_xnpt();
		//imp_sx_yysl();
		//imp_sx_qtrj();
		//imp_sx_czxt();
		//imp_sx_sjksl();
	}

	// 输出主机；
	public static void out_zj()
	{
		print(TemplateZJ.get_zj());
		print_pzzx(TemplateZJ.get_zj_gjsx_cpu());
		print_pzzx(TemplateZJ.get_zj_gjsx_nck());
		print_pzzx(TemplateZJ.get_zj_gjsx_yp());
		print_pzzx(TemplateZJ.get_zj_gjsx_dy());
		print_pzzx(TemplateZJ.get_zj_gjsx_wk());
		print_pzzx(TemplateZJ.get_zj_gjsx_hba());
		print_pzzx(TemplateZJ.get_zj_glsx_glzhxx());
	}

	// 导入主机属性；
	public static void imp_sx_zj()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36950e0c0136959846360005");
		obj.setAttr("ctemplateid", "402881ef3685db26013685dcda700000");

		imp_sx(obj, TemplateZJ.get_zj());
	}

	// 输出应用系统；
	public static void out_yyxt()
	{
		print(TemplateYYXT.get_yyxt());
		print_pzzx(TemplateYYXT.get_yyxt_glsx_glxx());
	}
	
	// 导入应用系统属性；
	public static void imp_sx_yyxt()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36950e0c0136951601170000");
		obj.setAttr("ctemplateid", "4028818a3685fe81013686372adc0000");

		imp_sx(obj, TemplateYYXT.get_yyxt());
	}
	
	// 输出刀片机箱；
	public static void out_dpjx()
	{
		print(TemplateDPJX.get_dpjx());
		print_pzzx(TemplateDPJX.get_dpjx_gjsx_dy());
		print_pzzx(TemplateDPJX.get_dpjx_gjsx_jhj());
		print_pzzx(TemplateDPJX.get_dpjx_gjsx_gl());
		print_pzzx(TemplateDPJX.get_dpjx_glsx_glzhxx());
	}
	
	// 导入刀片机箱属性；
	public static void imp_sx_dpjx()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36950e0c013695ad2a1d0007");
		obj.setAttr("ctemplateid", "4028810e3695d987013696b1a0a40028");

		imp_sx(obj, TemplateDPJX.get_dpjx());
	}
	
	// 输出网络设备；
	public static void out_wlsb()
	{
		print(TemplateWLSB.get_wlsb());
		print_pzzx(TemplateWLSB.get_wlsb_gjsx_jkipdz());
		print_pzzx(TemplateWLSB.get_wlsb_gjsx_ywb());
		print_pzzx(TemplateWLSB.get_wlsb_gjsx_dy());
		print_pzzx(TemplateWLSB.get_wlsb_glsx_glzhxx());
	}
	
	// 导入网络设备属性；
	public static void imp_sx_wlsb()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695b3d2d30009");
		obj.setAttr("ctemplateid", "402881b23686469f013686537ae80001");

		imp_sx(obj, TemplateWLSB.get_wlsb());
	}
	
	// 输出存储设备；
	public static void out_ccsb()
	{
		print(TemplateCCSB.get_ccsb());
		print_pzzx(TemplateCCSB.get_ccsb_gjsx_sanjhj());
		print_pzzx(TemplateCCSB.get_ccsb_gjsx_kzq());
		print_pzzx(TemplateCCSB.get_ccsb_gjsx_dy());
		print_pzzx(TemplateCCSB.get_ccsb_glsx_glzhxx());
	}
	
	// 导入存储设备属性；
	public static void imp_sx_ccsb()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695b65d56000d");
		obj.setAttr("ctemplateid", "4028811b36862f5d0136867da9cb0016");

		imp_sx(obj, TemplateCCSB.get_ccsb());
	}
	
	// 输出中间件；
	public static void out_zjj()
	{
		print(TemplateZJJ.get_zjj());
		print_pzzx(TemplateZJJ.get_zjj_gjsx_dk());
		print_pzzx(TemplateZJJ.get_zjj_glsx_glzhxx());
	}
	
	// 导入中间件属性；
	public static void imp_sx_zjj()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36aabf980136aace627d0010");
		obj.setAttr("ctemplateid", "4028818a36b0028d0136b01278d70001");

		imp_sx(obj, TemplateZJJ.get_zjj());
	}

	
	// 输出软件资产；
	public static void out_rjzc()
	{
		print(TemplateRJZC.get_rjzc());
	}
	
	// 导入软件资产属性；
	public static void imp_sx_rjzc()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695b7fe06000e");
		obj.setAttr("ctemplateid", "4028818a36965acb013696ad8b4a0010");

		imp_sx(obj, TemplateRJZC.get_rjzc());
	}
	
	// 输出数据库；
	public static void out_sjk()
	{
		print(TemplateSJK.get_sjk());
		print_pzzx(TemplateSJK.get_sjk_gjsx_dk());
		print_pzzx(TemplateSJK.get_sjk_glsx_glzhxx());
	
	}
	
	// 导入数据库属性；
	public static void imp_sx_sjk()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36aabf980136aacc9bb7000d");
		obj.setAttr("ctemplateid", "4028811b36aae18f0136ab220b300004");

		imp_sx(obj, TemplateSJK.get_sjk());
	}

	
	// 输出虚拟主机；
	public static void out_xnzj()
	{
		print(TemplateXNZJ.get_xnzj());
		print_pzzx(TemplateXNZJ.get_xnzj_glsx_glzhxx());
		
	}
	
	// 导入虚拟主机属性；
	public static void imp_sx_xnzj()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695c439300019");
		obj.setAttr("ctemplateid", "4028811b36aae18f0136ab0933d00001");

		imp_sx(obj, TemplateXNZJ.get_xnzj());
	}
	
	 // 输出应用实例；
	public static void out_yysl()
	{
		print(TemplateYYSL.get_yysl());
		print_pzzx(TemplateYYSL.get_yysl_gjsx_dk());
		print_pzzx(TemplateYYSL.get_yysl_jksx_jksx());
		print_pzzx(TemplateYYSL.get_yysl_glsx_glzhxx());
		
	}
	
	// 导入应用实例属性；
	public static void imp_sx_yysl()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695c1bd530016");
		obj.setAttr("ctemplateid", "402881b23686469f013686810c6f004c");

		imp_sx(obj, TemplateYYSL.get_yysl());
	}
	
	// 输出其他软件；
	public static void out_qtrj()
	{
		print(TemplateQTRJ.get_qtrj());
		print_pzzx(TemplateQTRJ.get_qtrj_gjsx_dk());
		print_pzzx(TemplateQTRJ.get_qtrj_glsx_glzhxx());
		
	}
	
	// 导入其他软件属性；
	public static void imp_sx_qtrj()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695be5e0b0013");
		obj.setAttr("ctemplateid", "4028818a36a937d80136aac2740b0003");

		imp_sx(obj, TemplateQTRJ.get_qtrj());
	}
	
	// 输出虚拟平台；
	public static void out_xnpt()
	{
		print(TemplateXNPT.get_xnpt());
		print_pzzx(TemplateXNPT.get_xnpt_gjsx_dk());
		print_pzzx(TemplateXNPT.get_xnpt_glsx_glzhxx());
	}
	
	// 导入虚拟平台属性；
	public static void imp_sx_xnpt()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36aabf980136aacfd8060012");
		obj.setAttr("ctemplateid", "4028811b36aefc550136af0f8c2c003e");

		imp_sx(obj, TemplateXNPT.get_xnpt());
	}


	
	// 输出数据库实例；
	public static void out_sjksl()
	{
		print(TemplateSJKSL.get_sjksl());
		print_pzzx(TemplateSJKSL.get_sjksl_glsx_glzhxx());
	}
	
	// 导入数据库实例属性；
	public static void imp_sx_sjksl()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e3695ae70013695c13edc0015");
		obj.setAttr("ctemplateid", "4028811b36aae18f0136aaf69b400000");

		imp_sx(obj, TemplateSJKSL.get_sjksl());
	}
	
	// 输出操作系统；
	public static void out_czxt()
	{
		print(TemplateCZXT.get_czxt());
		print_pzzx(TemplateCZXT.get_czxt_gjsx_dk());
		print_pzzx(TemplateCZXT.get_czxt_glsx_glzhxx());
	}
	
	// 导入操作系统属性；
	public static void imp_sx_czxt()
	{
		// 由开发人员指定;

		DynamicObject obj = new DynamicObject();
		obj.setAttr("cclassid", "4028810e36aabf980136aaca8163000a");
		obj.setAttr("ctemplateid", "4028818a36a937d80136ab86a18b0012");

		imp_sx(obj, TemplateCZXT.get_czxt());
	}
	
	// 输出属性表格；
	public static void print(List items)
	{
		for (int i = 0; i < items.size(); i++)
		{
			String[] item = (String[]) items.get(i);
			StringBuffer sql = new StringBuffer();
			if (i % 2 == 0)
			{
				sql.append("<tr>").append("\n");
			}

			String style = "";
			if (!StringToolKit.isBlank(item[2]))
			{
				style = "style=\"width:" + item[2] + "em;\"";
			}

			sql.append("<td class=\"r\">");
			sql.append("<label for=\"\">" + item[0] + "：</label></td>");
			sql.append("<td><input type=\"hidden\" name=\"pname\" value=\"" + item[1] + "\">");

			sql.append("<input class=\"text\" name=\"cvalue\" value=\"\" " + style + ">");
			sql.append("</td>");
			if (i % 2 == 1)
			{
				sql.append("\n").append("</tr>");
			}
			System.out.println(sql.toString());
		}
	}

	// 输出输出子项列表；
	public static void print_pzzx(List items)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("<th class=\"toggleCheckboxAll\" width=\"1\">");
		sql.append("<input class=\"checkbox\" /></th>");

		for (int i = 0; i < items.size(); i++)
		{
			String[] item = (String[]) items.get(i);
			sql.append("<th>" + item[0] + "</th>");
			System.out.println(sql.toString());
		}
	}

	public static void imp_sx(DynamicObject obj, List items)
	{

		String cclassid = obj.getFormatAttr("cclassid");
		String ctemplateid = obj.getFormatAttr("ctemplateid");

		StringBuffer sql = new StringBuffer();

		// 记录当前配置子项标识
		String current_configsubkeytemplate_id = "";

		for (int i = 0; i < items.size(); i++)
		{
			sql = new StringBuffer();
			String[] item = (String[]) items.get(i);

			if ("".equals(item[_configitemclass]))
			{
				String configitem_id = UUIDGenerator.getInstance().getNextValue();
				sql.append(" insert into t_app_configtemplateitem (id, ctemplateid, cname, pname, ctype, cvalue) ");
				sql.append(" values(");
				sql.append(SQLParser.charValueEnd(configitem_id));
				sql.append(SQLParser.charValueEnd(ctemplateid));
				sql.append(SQLParser.charValueEnd(item[_cname]));
				sql.append(SQLParser.charValueEnd(item[_pname]));
				sql.append(SQLParser.charValueEnd(""));
				sql.append(SQLParser.charValue(item[_defvalue]));
				sql.append(");");
			}

			// 如果当前属性是配置子项;
			if ("1".equals(item[_configitemclass]))
			{

				current_configsubkeytemplate_id = UUIDGenerator.getInstance().getNextValue();
				sql.append(" insert into t_app_configsubkeytemplate (id, cclassid, ctemplateid, supid, cname) ");

				sql.append(" values(");
				sql.append(SQLParser.charValueEnd(current_configsubkeytemplate_id));
				sql.append(SQLParser.charValueEnd(cclassid));
				sql.append(SQLParser.charValueEnd(ctemplateid));
				sql.append(SQLParser.charValueEnd(ctemplateid));
				sql.append(SQLParser.charValue(item[_cname]));
				sql.append(");").append("\n");
				

				String configitem_id = UUIDGenerator.getInstance().getNextValue();
				sql.append(" insert into t_app_configtemplateitem (id, ctemplateid, cname, pname, ctype, cvalue, csktemplateid, csktemplatename) ");
				sql.append(" values(");
				sql.append(SQLParser.charValueEnd(configitem_id));
				sql.append(SQLParser.charValueEnd(ctemplateid));
				sql.append(SQLParser.charValueEnd(item[_cname]));
				sql.append(SQLParser.charValueEnd(item[_pname]));
				sql.append(SQLParser.charValueEnd(""));
				sql.append(SQLParser.charValueEnd(item[_defvalue]));
				sql.append(SQLParser.charValueEnd(current_configsubkeytemplate_id));
				sql.append(SQLParser.charValue(item[_cname]));
				sql.append(");");

			
			}

			if ("2".equals(item[_configitemclass]))
			{
				String configitem_id = UUIDGenerator.getInstance().getNextValue();
				sql.append(" insert into t_app_configsubkeytemplateitem (id, csktemplateid, cname, pname, ctype, cvalue) ");
				sql.append(" values(");
				sql.append(SQLParser.charValueEnd(configitem_id));
				sql.append(SQLParser.charValueEnd(current_configsubkeytemplate_id));
				sql.append(SQLParser.charValueEnd(item[_cname]));
				sql.append(SQLParser.charValueEnd(item[_pname]));
				sql.append(SQLParser.charValueEnd(""));
				sql.append(SQLParser.charValue(item[_defvalue]));
				sql.append(");");
			}

			System.out.println(sql.toString());
		}
	}

}
