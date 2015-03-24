package com.pams.gxgl.kpi.zxsc.action;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.pams.gxgl.kpi.zxsc.service.TabZXSC_BM;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_GS;
import com.pams.gxgl.kpi.zxsc.service.TabZXSC_RY;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
@InjectName("repAction_ZXSC")
@At("/module/pams/gxgl/kpi/zxsc/rep")
public class RepAction extends BaseAction
{
	private String _searchname;

	@Inject
	private Dao dao;

	@At("/mainframe")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/mainframe.ftl")
	public String mainframe() throws Exception
	{
		return "mainframe";
	}

	// 集团公司统计报表
	@At("/main_zxsc_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_gs.ftl")
	public Map main_zxsc_gs() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter(
				"begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		
		// return "main_zxsc_gs";
		return ro;
	}

	// 本公司统计报表
	@At("/main_local_zxsc_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_gs.ftl")
	public Map main_local_zxsc_gs() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);

//		return "main_zxsc_gs";
		return ro;
	}

	@At("/tab_zxsc_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/tab_zxsc_gs.ftl")
	public Map tab_zxsc_gs() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setDao(dao);

		List datas = tabZXSC_GS.execute(obj);
		ro.setObj("zxscs", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);

		return ro;
	}

	public String xls_zxsc_gs() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_GS tabZXSC_GS = new TabZXSC_GS();
		tabZXSC_GS.setDao(dao);

		List datas = tabZXSC_GS.execute(obj);
		
		String[] cnames = new String[]{ "公司", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "执行时长考核公司统计.xls";

		//exp_excel(cnames, pnames, datas, fileName);

		return "excel";
	}

	@At("/main_zxsc_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_bm.ftl")
	public Map main_zxsc_bm() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		
		return ro;
	}

	@At("/main_local_zxsc_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_bm.ftl")
	public Map main_local_zxsc_bm() throws Exception
	{

		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		
		return ro;
	}

	@At("/tab_zxsc_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/tab_zxsc_bm.ftl")
	public Map tab_zxsc_bm() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_BM tabZXSC_BM = new TabZXSC_BM();
		tabZXSC_BM.setDao(dao);

		List datas = tabZXSC_BM.execute(obj);
		ro.setObj("zxscs", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		return ro;
	}
	
	public String xls_zxsc_bm() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_BM tabZXSC_BM = new TabZXSC_BM();
		tabZXSC_BM.setDao(dao);

		List datas = tabZXSC_BM.execute(obj);

		String[] cnames = new String[]{ "部门", "考核总分" };
		String[] pnames = new String[]{ "orgcname", "zxsccskh" };
		String fileName = "执行时长考核部门统计.xls";

		//exp_excel(cnames, pnames, datas, fileName);

		return "excel";		
	}

	@At("/main_zxsc_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_ry.ftl")
	public Map main_zxsc_ry() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String orginternal = StringToolKit.formatText(Mvcs.getReq().getParameter("orginternal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("orginternal", orginternal);
		ro.setAttr("reptype", reptype);
		
		return ro;
	}

	@At("/main_local_zxsc_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/main_zxsc_ry.ftl")
	public Map main_local_zxsc_ry() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("orginternal", orginternal);
		ro.setAttr("reptype", reptype);
		
		return ro;
	}

	@At("/tab_zxsc_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/zxsc/view/tab_zxsc_ry.ftl")
	public Map tab_zxsc_ry() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String orginternal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		ro.setAttr("orginternal", orginternal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabZXSC_RY tabZXSC_RY = new TabZXSC_RY();
		tabZXSC_RY.setDao(dao);

		List datas = tabZXSC_RY.execute(obj);
		ro.setObj("zxscs", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		return ro;
	}
	
	public String xls_zxsc_ry() throws Exception
	{
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter("begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter("enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter("internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter("reptype"));
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);
		
		// 发布总数
		TabZXSC_RY tabZXSC_RY = new TabZXSC_RY();
		tabZXSC_RY.setDao(dao);

		List datas = tabZXSC_RY.execute(obj);
		
		String[] cnames = new String[]{ "人员", "考核总分", "所属公司", "所属部门"};
		String[] pnames = new String[]{ "username", "zxsccskh", "orgcname", "deptcname" };
		String fileName = "执行时长考核人员统计.xls";

		//exp_excel(cnames, pnames, datas, fileName);

		return "excel";	
	}	

	

}