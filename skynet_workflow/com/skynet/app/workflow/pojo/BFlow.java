package com.skynet.app.workflow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFBFLOW")
public class BFlow extends IdEntity {
	@Column
	private String readerchoice;

	@Column
	private String startchoice;

	@Column
	private String cname;

	@Column
	private String createformid;

	@Column
	private String formid;

	@Column
	private String classid;

	@Column
	private String verson;

	@Column
	private String state;

	@Column
	private String sno;

	public String getReaderchoice() {
		return readerchoice;
	}

	public void setReaderchoice(String readerchoice) {
		this.readerchoice = readerchoice;
	}

	public String getStartchoice() {
		return startchoice;
	}

	public void setStartchoice(String startchoice) {
		this.startchoice = startchoice;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateformid() {
		return createformid;
	}

	public void setCreateformid(String createformid) {
		this.createformid = createformid;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getVerson() {
		return verson;
	}

	public void setVerson(String verson) {
		this.verson = verson;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

}
