package com.pams.gxgl.kpi.jsl.action;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.pams.gxgl.kpi.jsl.service.TabJSL_BM;
import com.pams.gxgl.kpi.jsl.service.TabJSL_GS;
import com.pams.gxgl.kpi.jsl.service.TabJSL_RY;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
@At("/module/pams/gxgl/kpi/jsl/rep")
public class RepAction extends BaseAction {
	private String _searchname;

	@Inject
	private Dao dao;

	@At("/mainframe")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/mainframe.ftl")
	public String mainframe() throws Exception {
		return "mainframe";
	}

	// 集团公司统计报表
	@At("/main_jsl_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_gs.ftl")
	public Map main_jsl_gs() throws Exception {
		String begindate = StringToolKit.formatText(Mvcs.getReq().getParameter(
				"begindate"));
		String enddate = StringToolKit.formatText(Mvcs.getReq().getParameter(
				"enddate"));
		String internal = StringToolKit.formatText(Mvcs.getReq().getParameter(
				"internal"));
		String reptype = StringToolKit.formatText(Mvcs.getReq().getParameter(
				"reptype"));

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("orginternal", internal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_gs";
		return ro;
	}

	// 本公司统计报表
	@At("/main_local_jsl_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_gs.ftl")
	public Map main_local_jsl_gs() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Mvcs.getReq().getParameter("reptype");

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_gs";
		return ro;
	}

	@At("/tab_jsl_gs")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/tab_jsl_gs.ftl")
	public Map tab_jsl_gs() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_GS tabJSL_GS = new TabJSL_GS();
		tabJSL_GS.setDao(dao);

		List datas = tabJSL_GS.execute(obj);
		ro.setObj("jsls", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		// return "tab_jsl_gs";
		return ro;
	}

	public Map xls_jsl_gs() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_GS tabJSL_GS = new TabJSL_GS();
		tabJSL_GS.setDao(dao);

		List datas = tabJSL_GS.execute(obj);

		String[] cnames = new String[] { "公司", "考核总分" };
		String[] pnames = new String[] { "orgcname", "zxsccskh" };
		String fileName = "共享及时率考核公司统计.xls";

		// exp_excel(cnames, pnames, datas, fileName);

		// return "excel";
		return ro;
	}

	@At("/main_jsl_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_bm.ftl")
	public Map main_jsl_bm() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_bm";
		return ro;
	}

	@At("/main_local_jsl_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_bm.ftl")
	public Map main_local_jsl_bm() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Mvcs.getReq().getParameter("reptype");

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_bm";
		return ro;
	}

	@At("/tab_jsl_bm")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/tab_jsl_bm.ftl")
	public Map tab_jsl_bm() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_BM tabJSL_BM = new TabJSL_BM();
		tabJSL_BM.setDao(dao);

		List datas = tabJSL_BM.execute(obj);
		ro.setObj("jsls", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);

		// return "tab_jsl_bm";
		return ro;
	}

	public Map xls_jsl_bm() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_BM tabJSL_BM = new TabJSL_BM();
		tabJSL_BM.setDao(dao);

		List datas = tabJSL_BM.execute(obj);

		String[] cnames = new String[] { "部门", "考核总分" };
		String[] pnames = new String[] { "orgcname", "zxsccskh" };
		String fileName = "共享及时率考核部门统计.xls";

		// exp_excel(cnames, pnames, datas, fileName);

		// return "excel";

		return ro;
	}

	@At("/main_jsl_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_ry.ftl")
	public Map main_jsl_ry() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String orginternal = Mvcs.getReq().getParameter("orginternal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("orginternal", orginternal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_ry";
		return ro;
	}

	@At("/main_local_jsl_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/main_jsl_ry.ftl")
	public Map main_local_jsl_ry() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String orginternal = ((DynamicObject) Mvcs.getReq().getSession()
				.getAttribute(GlobalConstants.sys_login_token))
				.getFormatAttr(GlobalConstants.sys_login_org_internal);
		String reptype = Mvcs.getReq().getParameter("reptype");

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("orginternal", orginternal);
		ro.setAttr("reptype", reptype);

		// return "main_jsl_ry";
		return ro;
	}

	@At("/tab_jsl_ry")
	@Ok("->:/page/report/com/pams/gxgl/kpi/jsl/view/tab_jsl_ry.ftl")
	public Map tab_jsl_ry() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_RY tabJSL_RY = new TabJSL_RY();
		tabJSL_RY.setDao(dao);

		List datas = tabJSL_RY.execute(obj);
		ro.setObj("jsls", datas);

		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("internal", internal);
		ro.setAttr("reptype", reptype);
		// return "tab_jsl_ry";

		return ro;
	}

	public Map xls_jsl_ry() throws Exception {
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String internal = Mvcs.getReq().getParameter("internal");
		String reptype = Mvcs.getReq().getParameter("reptype");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("internal", internal);
		obj.setAttr("reptype", reptype);

		// 发布总数
		TabJSL_RY tabJSL_RY = new TabJSL_RY();
		tabJSL_RY.setDao(dao);

		List datas = tabJSL_RY.execute(obj);

		String[] cnames = new String[] { "人员", "考核总分", "所属公司", "所属部门" };
		String[] pnames = new String[] { "username", "zxsccskh", "orgcname",
				"deptcname" };
		String fileName = "共享及时率考核人员统计.xls";

		// exp_excel(cnames, pnames, datas, fileName);

		// return "excel";

		return ro;
	}
}