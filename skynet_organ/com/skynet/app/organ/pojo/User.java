package com.skynet.app.organ.pojo;

import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_USER")
public class User extends IdEntity {
	private String loginname; // 登录名

	private String cname; // 姓名
	
	private String password; // 密码
	
	private String isusing; // 可用标志
	
	private String ordernum; // 排序
	
	private String ownerdept; // 所属部门，临时字段，后期删除

	private String ownerorg; // 所属机构，临时字段，后期删除
	
	private String deptname; // 所属部门，临时字段，后期删除

	private String orgname; // 所属机构，临时字段，后期删除	

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsusing() {
		return isusing;
	}

	public void setIsusing(String isusing) {
		this.isusing = isusing;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getOwnerdept() {
		return ownerdept;
	}

	public void setOwnerdept(String ownerdept) {
		this.ownerdept = ownerdept;
	}

	public String getOwnerorg() {
		return ownerorg;
	}

	public void setOwnerorg(String ownerorg) {
		this.ownerorg = ownerorg;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}
