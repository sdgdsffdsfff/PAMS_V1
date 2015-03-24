package com.skynet.app.workflow.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.app.workflow.pojo.RAct;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.SplitTableConstants;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("ractService")
@IocBean(args = { "refer:dao" })
public class RActService extends SkynetNameEntityService<RAct> {
	public RActService() {
		super();
	}

	public RActService(Dao dao) {
		super(dao);
	}

	public RActService(Dao dao, Class<RAct> entityType) {
		super(dao, entityType);
	}

	public String create(RAct ract) {
		String runactkey = UUIDGenerator.getInstance().getNextValue();
		ract.setRunactkey(runactkey);
		ract.setIsuse("Y");
		sdao().insert(ract);

		return ract.getRunactkey();
	}

	// public String create(String runflowkey, String createtime, String
	// flowdefid, String actdefid, String state, String priority, String dataid,
	// String formid, String tableid, String handletype) throws Exception
	// {
	// String runactkey = UUIDGenerator.getInstance().getNextValue();
	// //String.valueOf(System.nanoTime()); ;
	//
	// StringBuffer sql = new StringBuffer();
	// sql.append(" insert into t_sys_wfract (runflowkey,runactkey,createtime,ccreatetime,flowdefid,actdefid,state,priority,dataid,formid,tableid,handletype,isuse) \n");
	// sql.append(" values(");
	// sql.append(SQLParser.charValueEnd(runflowkey));
	// sql.append(SQLParser.charValueEnd(runactkey));
	// sql.append(" sysdate, ");
	// sql.append(SQLParser.charValueEnd(createtime));
	// sql.append(SQLParser.charValueEnd(flowdefid));
	// sql.append(SQLParser.charValueEnd(actdefid));
	// sql.append(SQLParser.charValueEnd(state));
	// sql.append(" null, ");
	// sql.append(SQLParser.charValueEnd(dataid));
	// sql.append(SQLParser.charValueEnd(formid));
	// sql.append(SQLParser.charValueEnd(tableid));
	// sql.append(SQLParser.charValueEnd(handletype));
	// sql.append(SQLParser.charValue("Y"));
	// sql.append(")");
	//
	// dao().execute(Sqls.create(sql.toString()));
	// return runactkey;
	// }
	//
	// public String create(String runflowkey, String createtime, String
	// flowdefid, String actdefid, String state, String priority, String dataid,
	// String formid, String tableid, String handletype, int ordernum) throws
	// Exception
	// {
	// String runactkey = UUIDGenerator.getInstance().getNextValue();
	// //String.valueOf(System.nanoTime()); ;
	//
	// StringBuffer sql = new StringBuffer();
	// sql.append(" insert into t_sys_wfract (runflowkey,runactkey,createtime,ccreatetime,flowdefid,actdefid,state,priority,dataid,formid,tableid,handletype,isuse,ordernum) \n");
	// sql.append(" values(");
	// sql.append(SQLParser.charValueEnd(runflowkey));
	// sql.append(SQLParser.charValueEnd(runactkey));
	// sql.append(" sysdate, ");
	// sql.append(SQLParser.charValueEnd(createtime));
	// sql.append(SQLParser.charValueEnd(flowdefid));
	// sql.append(SQLParser.charValueEnd(actdefid));
	// sql.append(SQLParser.charValueEnd(state));
	// sql.append(" null, ");
	// sql.append(SQLParser.charValueEnd(dataid));
	// sql.append(SQLParser.charValueEnd(formid));
	// sql.append(SQLParser.charValueEnd(tableid));
	// sql.append(SQLParser.charValueEnd(handletype));
	// sql.append(SQLParser.charValueEnd("Y"));
	// sql.append(SQLParser.numberValue(ordernum));
	// sql.append(")");
	//
	// dao().execute(Sqls.create(sql.toString()));
	// return runactkey;
	// }
	//
	// public String create(String runflowkey, String createtime, String
	// flowdefid, String actdefid, String state, String priority, String dataid,
	// String formid, String tableid, String handletype, int ordernum, String
	// suprunflowkey, String suprunactkey) throws Exception
	// {
	// String runactkey = UUIDGenerator.getInstance().getNextValue();
	// //String.valueOf(System.nanoTime()); ;
	//
	// StringBuffer sql = new StringBuffer();
	// sql.append(" insert into t_sys_wfract (runflowkey,runactkey,createtime,ccreatetime,flowdefid,actdefid,state,priority,dataid,formid,tableid,handletype,isuse,ordernum,suprunflowkey,suprunactkey) \n");
	// sql.append(" values(");
	// sql.append(SQLParser.charValueEnd(runflowkey));
	// sql.append(SQLParser.charValueEnd(runactkey));
	// sql.append(" sysdate, ");
	// sql.append(SQLParser.charValueEnd(createtime));
	// sql.append(SQLParser.charValueEnd(flowdefid));
	// sql.append(SQLParser.charValueEnd(actdefid));
	// sql.append(SQLParser.charValueEnd(state));
	// sql.append(" null, ");
	// sql.append(SQLParser.charValueEnd(dataid));
	// sql.append(SQLParser.charValueEnd(formid));
	// sql.append(SQLParser.charValueEnd(tableid));
	// sql.append(SQLParser.charValueEnd(handletype));
	// sql.append(SQLParser.charValueEnd("Y"));
	// sql.append(SQLParser.numberValueEnd(ordernum));
	// sql.append(SQLParser.charValueEnd(suprunflowkey));
	// sql.append(SQLParser.charValue(suprunactkey));
	//
	// sql.append(")");
	//
	// dao().execute(Sqls.create(sql.toString()));
	// return runactkey;
	// }

	public void set_apply_time(String runactkey, String tableid)
			throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append(" update t_sys_wfract ");
		sql.append(" set applytime = sysdate ");
		sql.append(" where 1 = 1 ");
		sql.append(" and runactkey = " + SQLParser.charValue(runactkey));

		dao().execute(Sqls.create(sql.toString()));
	}

	public void set_complete_time(String runactkey, String tableid,
			String completetype) throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append(" update "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid));
		sql.append(" set completetime = sysdate, ");
		sql.append(" isuse = 'N', ");
		sql.append(" completetype = " + SQLParser.charValue(completetype));
		sql.append(" where 1 = 1 ");
		sql.append(" and runactkey = " + SQLParser.charValue(runactkey));
		dao().execute(Sqls.create(sql.toString()));
	}

	public boolean enable_forward(String runactkey, String ownerctx,
			String ctype) throws Exception {
		boolean sign = false;
		String sql = SQL_ENABLE_FORWARD(runactkey, ownerctx, ctype);

		List owners = new ArrayList();
		owners = sdao().queryForList(sql.toString());
		if (owners.size() > 0) {
			sign = true;
		}
		return sign;
	}

	public boolean isforward(String runactkey) throws Exception {
		boolean sign = false;
		DynamicObject obj_ract = locate(runactkey);
		String state = obj_ract.getFormatAttr("state");

		if (DBFieldConstants.RACT_STATE_COMPLETED.equals(state)) {
			sign = true;
		}

		return sign;
	}

	// 查找并行路由活动完成状态(转出为AND)
	public List findCloseLoopActsFromEndAllComp(String runflowkey,
			String runactkey, String tableid, String dataid) throws Exception {
		// 当前活动是集合点(转入是join);
		// 查看集合点和分叉点之间的闭环之间是否具有未完成的活动，
		// 如果有，证明闭环没有完成，集合点不允许向下走
		//
		StringBuffer sql = new StringBuffer();
		sql.append("select a.actdefid from t_sys_wfract a where 1 = 1 and a.runactkey = "
				+ SQLParser.charValueRT(runactkey));
		String actdefid = queryForMap(sql.toString()).getFormatAttr("actdefid");
		if (actdefid.equals("")) {
			throw new RuntimeException("查找过程出错！");
		}

		List acts = getCloseLoopActsFromEnd(actdefid);

		int count = acts.size();
		String actvalues = new String();
		for (int i = 0; i < count; i++) {
			DynamicObject actObj = (DynamicObject) acts.get(i);
			String id = actObj.getFormatAttr("id");
			String cname = actObj.getFormatAttr("cname");
			String level = actObj.getFormatAttr("level");
			String split = actObj.getFormatAttr("split");
			String join = actObj.getFormatAttr("join");
			System.out.println(i + ":" + id + " , " + cname + " , " + level
					+ " , " + split + " , " + join);

			if (i < (count - 1)) {
				actvalues += SQLParser.charValue(id) + ", ";
			} else {
				actvalues += SQLParser.charValue(id);
			}
		}
		sql = new StringBuffer();
		sql.append("select distinct a.runactkey, a.actdefid, b.cname \n");
		sql.append("  from "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid)
				+ " a, t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.actdefid = b.id \n");
		sql.append("   and a.state <> '已处理' \n");
		sql.append("   and a.actdefid in (" + actvalues + ") \n");
		sql.append("   and a.tableid = " + SQLParser.charValue(tableid));
		sql.append("   and a.dataid = " + SQLParser.charValue(dataid));

		List nocompleteacts = new ArrayList();
		nocompleteacts = sdao().queryForList(sql.toString());
		return nocompleteacts;
	}

	public List getCloseLoopActsFromEnd(String endactdefid) throws Exception {
		Properties array = new Properties();
		findCloseLoopActsFromEnd(endactdefid, array, 0);
		List objs = new ArrayList();
		Enumeration elements = array.elements();
		while (elements.hasMoreElements()) {
			DynamicObject obj = (DynamicObject) elements.nextElement();
			objs.add(obj);
			System.out.println(obj);
		}
		return objs;
	}

	public void findCloseLoopActsFromEnd(String endactdefid, Properties keys,
			int level) throws Exception {
		// 检查是否到达流程的起始节点
		StringBuffer sql = new StringBuffer();
		sql.append("select b.id, b.cname, b.isfirst, b.ctype \n");
		sql.append("  from t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and b.ctype = 'BEGIN' \n");
		sql.append("   and b.id = " + SQLParser.charValueRT(endactdefid));

		if (queryForList(sql.toString()).size() > 0) {
		} else {
			sql = new StringBuffer();
			sql.append("select b.id, b.cname, b.isfirst, b.ctype, b.split, b.join \n");
			sql.append("  from t_sys_wfbroute a, t_sys_wfbact b \n");
			sql.append(" where 1 = 1 \n");
			sql.append("   and a.startactid = b.id \n");
			sql.append("   and a.endactid = "
					+ SQLParser.charValueRT(endactdefid));

			List obj = sdao().queryForList(sql.toString());
			;
			int count = obj.size();

			for (int i = 0; i < count; i++) {
				DynamicObject aObj = (DynamicObject) obj.get(i);
				String id = aObj.getFormatAttr("id");
				String cname = aObj.getFormatAttr("cname");
				String isfirst = aObj.getFormatAttr("isfirst");
				String ctype = aObj.getFormatAttr("ctype");
				String split = aObj.getFormatAttr("split");
				String join = aObj.getFormatAttr("join");

				aObj.setAttr("level", String.valueOf(level));

				if (join.equals("AND")) {
					aObj.setAttr("level", String.valueOf(level + 1));
					aObj.setAttr("sign", "JOIN");
				}

				if (ctype.equals("BEGIN")) {
				} else {
					keys.put(id, aObj);
				}

				// 如果已经是同层的起始集合点，不再查找前趋
				if (split.equals("AND") && (level == 0)) {
				} else {
					findCloseLoopActsFromEnd(id, keys, level);
				}
			}
		}
	}

	// 更新抄送路由活动完成状态(转出为COPY)
	public void updateCopyRoutesActsFromEndAll(String runflowkey,
			String runactkey, String tableid, String copy) throws Exception {
		// 当前活动是集合点(转入是COPY);
		// 查看集合点和分叉点之间的主线是否具有未完成的活动，
		// 如果有，证明主线没有完成，集合点不允许向下走
		StringBuffer sql = new StringBuffer();
		sql.append("select a.actdefid from "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid)
				+ " a where 1 = 1 and a.runactkey = "
				+ SQLParser.charValueRT(runactkey));

		String actdefid = queryForMap(sql.toString()).getFormatAttr("actdefid");
		if (actdefid.equals("")) {
			throw new RuntimeException("查找过程出错！");
		}

		List acts = getCopyRoutesActsFromEnd(actdefid, copy);

		int count = acts.size();
		String actvalues = new String();
		for (int i = 0; i < count; i++) {
			DynamicObject actObj = (DynamicObject) acts.get(i);
			String id = actObj.getFormatAttr("id");
			String cname = actObj.getFormatAttr("cname");
			String level = actObj.getFormatAttr("level");
			String split = actObj.getFormatAttr("split");
			String join = actObj.getFormatAttr("join");
			// System.out.println(i + ":" + id + " , " + cname + " , " + level +
			// " , " + split + " , " + join);

			/*
			 * if(i<(count-1)) { actvalues += SQLParser.charValue(id) + ", "; }
			 * else { actvalues += SQLParser.charValue(id); }
			 */
			actvalues += SQLParser.charValue(id) + ", ";
		}

		// 防止没有查询到活动
		actvalues += SQLParser.charValue("");

		sql = new StringBuffer();
		sql.append("update "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid)
				+ " set state = '已处理' where 1 = 1 and actdefid in ("
				+ actvalues + ") \n");
		dao().execute(Sqls.create(sql.toString()));

		sql = new StringBuffer();
		sql.append("delete from t_sys_wfwaitwork where 1 = 1 and actdefid in ("
				+ actvalues + ") \n");
		dao().execute(Sqls.create(sql.toString()));
	}

	public List getCopyRoutesActsFromEnd(String endactdefid, String copy)
			throws Exception {
		Properties array = new Properties();
		findCopyRoutesActsFromEnd(endactdefid, array, 0, copy);
		List objs = new ArrayList();
		Enumeration elements = array.elements();
		while (elements.hasMoreElements()) {
			DynamicObject obj = (DynamicObject) elements.nextElement();
			objs.add(obj);
		}
		return objs;
	}

	public void findCopyRoutesActsFromEnd(String endactdefid, Properties keys,
			int level, String copy) throws Exception {
		// 检查是否到达流程的起始节点
		StringBuffer sql = new StringBuffer();
		sql.append("select b.id, b.cname, b.isfirst, b.ctype \n");
		sql.append("  from t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and b.ctype = 'BEGIN' \n");
		sql.append("   and b.id = " + SQLParser.charValueRT(endactdefid));

		if (sdao().queryForList(sql.toString()).size() > 0) {
		} else {
			sql = new StringBuffer();
			sql.append("select b.id, b.cname, b.isfirst, b.ctype, b.split, b.join \n");
			sql.append("  from t_sys_wfbroute a, t_sys_wfbact b \n");
			sql.append(" where 1 = 1 \n");
			sql.append("   and a.startactid = b.id \n");
			sql.append("   and a.routetype = " + SQLParser.charValueRT(copy));
			sql.append("   and a.endactid = "
					+ SQLParser.charValueRT(endactdefid));

			List obj = sdao().queryForList(sql.toString());
			;
			int count = obj.size();

			for (int i = 0; i < count; i++) {
				DynamicObject aObj = (DynamicObject) obj.get(i);
				String id = aObj.getFormatAttr("id");
				String cname = aObj.getFormatAttr("cname");
				String isfirst = aObj.getFormatAttr("isfirst");
				String ctype = aObj.getFormatAttr("ctype");
				String split = aObj.getFormatAttr("split");
				String join = aObj.getFormatAttr("join");

				aObj.setAttr("level", String.valueOf(level));

				if (join.equals("COPY")) {
					aObj.setAttr("level", String.valueOf(level + 1));
					aObj.setAttr("sign", "COPY");
				}

				if (ctype.equals("BEGIN")) {
				} else {
					keys.put(id, aObj);
				}

				// 如果已经是同层的起始集合点，不再查找前趋
				if (split.equals("COPY") && (level == 0)) {
				} else {
					findCopyRoutesActsFromEnd(id, keys, level, copy);
				}
			}
		}
	}

	// 查找抄送路由活动完成状态(转出为COPY)
	public List findCopyRoutesActsFromEndAllComp(String runflowkey,
			String runactkey, String tableid, String dataid, String copy)
			throws Exception {
		// 当前活动是集合点(转入是COPY);
		// 查看集合点和分叉点之间的主线是否具有未完成的活动，
		// 如果有，证明主线没有完成，集合点不允许向下走
		StringBuffer sql = new StringBuffer();
		sql.append("select a.actdefid from "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid)
				+ " a where 1 = 1 and a.runactkey = "
				+ SQLParser.charValueRT(runactkey));

		String actdefid = sdao().queryForMap(sql.toString()).getFormatAttr(
				"actdefid");
		if (actdefid.equals("")) {
			throw new RuntimeException("查找过程出错！");
		}

		List acts = getCopyRoutesActsFromEnd(actdefid, copy);

		int count = acts.size();
		String actvalues = new String();
		for (int i = 0; i < count; i++) {
			DynamicObject actObj = (DynamicObject) acts.get(i);
			String id = actObj.getFormatAttr("id");
			String cname = actObj.getFormatAttr("cname");
			String level = actObj.getFormatAttr("level");
			String split = actObj.getFormatAttr("split");
			String join = actObj.getFormatAttr("join");
			System.out.println(i + ":" + id + " , " + cname + " , " + level
					+ " , " + split + " , " + join);

			if (i < (count - 1)) {
				actvalues += SQLParser.charValue(id) + ", ";
			} else {
				actvalues += SQLParser.charValue(id);
			}
		}
		sql = new StringBuffer();
		sql.append("select distinct a.runactkey, a.actdefid, b.cname \n");
		sql.append("  from "
				+ SplitTableConstants.getSplitTable("t_sys_wfract", tableid)
				+ " a, t_sys_wfbact b \n");
		sql.append(" where 1 = 1 \n");
		sql.append("   and a.actdefid = b.id \n");
		sql.append("   and a.state <> '已处理' \n");
		sql.append("   and a.actdefid in (" + actvalues + ") \n");
		sql.append("   and a.tableid = " + SQLParser.charValue(tableid));
		sql.append("   and a.dataid = " + SQLParser.charValue(dataid));

		List nocompleteacts = new ArrayList();
		nocompleteacts = sdao().queryForList(sql.toString());
		return nocompleteacts;
	}

	public void clear_ract_isuse(String runflowkey, String actdefid)
			throws Exception {
		// 清除所有当前活动所有待办工作
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_sys_wfract set isuse = 'N' where 1 = 1 ");
		sql.append(" and runflowkey = ")
				.append(SQLParser.charValue(runflowkey));
		sql.append(" and actdefid = ").append(SQLParser.charValue(actdefid));

		dao().execute(Sqls.create(sql.toString()));
	}

	public static String SQL_ENABLE_FORWARD(String runactkey, String loginname,
			String ctype) throws Exception {
		String sql_idens = OrgService.build_idens(loginname);

		StringBuffer sql = new StringBuffer();

		sql.append("select ractowner.id, ractowner.ownerctx, ractowner.ctype ")
				.append("\n");
		sql.append(
				"  from t_sys_wfractowner ractowner, t_sys_wfract ract, t_sys_wfrflow rflow ")
				.append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and ractowner.runactkey = ract.runactkey ").append("\n");
		sql.append("   and ract.runflowkey = rflow.runflowkey ").append("\n");
		// sql.append("   and ract.isuse = 'Y' ").append("\n");
		sql.append("   and ract.runactkey = " + SQLParser.charValue(runactkey));
		sql.append("   and rflow.state not in ('尚未实例化','挂起','结束','中止') ")
				.append("\n");
		sql.append("   and (ractowner.ownerctx, ractowner.ctype) in ").append(
				"\n");
		sql.append("(" + sql_idens + ")").append("\n");

		return sql.toString();
	}

}
