package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_WFHWAITWORK")
public class WFHWaitWork {
	@Name
	@ColDefine(width = 32)
	private String waitworkid;

	@Column
	private String consigner;

	@Column
	private String consignercname;

	@Column
	private String sendercname;

	@Column
	private String sender;

	@Column
	private String receivercname;

	@Column
	private String receiver;

	@Column
	private String dataid;

	@Column
	private String tableid;

	@Column
	private String link;

	@Column
	private String title;

	@Column
	private String actdefid;

	@Column
	private String ctype;

	@Column
	private String actcname;

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp sendtime;

	@Column
	private String priority;

	@Column
	private String readstate;

	@Column
	private String runactkey;

	@Column
	private String jgcname;

	@Column
	private String bmcname;

	@Column
	private String ownerorg;

	@Column
	private String ownerdept;

	public String getConsigner() {
		return consigner;
	}

	public void setConsigner(String consigner) {
		this.consigner = consigner;
	}

	public String getConsignercname() {
		return consignercname;
	}

	public void setConsignercname(String consignercname) {
		this.consignercname = consignercname;
	}

	public String getSendercname() {
		return sendercname;
	}

	public void setSendercname(String sendercname) {
		this.sendercname = sendercname;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceivercname() {
		return receivercname;
	}

	public void setReceivercname(String receivercname) {
		this.receivercname = receivercname;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActdefid() {
		return actdefid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getWaitworkid() {
		return waitworkid;
	}

	public void setWaitworkid(String waitworkid) {
		this.waitworkid = waitworkid;
	}

	public String getActcname() {
		return actcname;
	}

	public void setActcname(String actcname) {
		this.actcname = actcname;
	}

	public Timestamp getSendtime() {
		return sendtime;
	}

	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getReadstate() {
		return readstate;
	}

	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getJgcname() {
		return jgcname;
	}

	public void setJgcname(String jgcname) {
		this.jgcname = jgcname;
	}

	public String getBmcname() {
		return bmcname;
	}

	public void setBmcname(String bmcname) {
		this.bmcname = bmcname;
	}

	public String getOwnerorg() {
		return ownerorg;
	}

	public void setOwnerorg(String ownerorg) {
		this.ownerorg = ownerorg;
	}

	public String getOwnerdept() {
		return ownerdept;
	}

	public void setOwnerdept(String ownerdept) {
		this.ownerdept = ownerdept;
	}

}
