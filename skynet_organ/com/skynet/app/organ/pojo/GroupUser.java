package com.skynet.app.organ.pojo;

import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_GROUPUSER")
public class GroupUser extends IdEntity {
	private String groupid; // 分组标识

	private String groupname; // 分组名称

	private String grouptype; // 分组类型

	private String userid; // 用户标识
	
	private String loginname; // 用户名

	private String username; // 用户姓名
	
	private String grouporder; // 分组级别顺序
	
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGrouporder() {
		return grouporder;
	}

	public void setGrouporder(String grouporder) {
		this.grouporder = grouporder;
	}
	

}
