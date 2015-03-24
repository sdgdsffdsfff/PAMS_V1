package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFWAITWORK")
public class WFWaitWork extends IdEntity {
	
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
    @ColDefine(type=ColType.DATETIME)
	private Timestamp readtime; // 查看（签收）时间

	@Column
	private String runactkey;

	@Column
	private String ownerorg;

	@Column
	private String ownerorgname;

	@Column
	private String ownerdept;

	@Column
	private String ownerdeptname;

	@Column
	private String isshow;

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

	public Timestamp getReadtime() {
		return readtime;
	}

	public void setReadtime(Timestamp readtime) {
		this.readtime = readtime;
	}

	public String getRunactkey() {
		return runactkey;
	}

	public void setRunactkey(String runactkey) {
		this.runactkey = runactkey;
	}

	public String getOwnerorg() {
		return ownerorg;
	}

	public void setOwnerorg(String ownerorg) {
		this.ownerorg = ownerorg;
	}

	public String getOwnerorgname() {
		return ownerorgname;
	}

	public void setOwnerorgname(String ownerorgname) {
		this.ownerorgname = ownerorgname;
	}

	public String getOwnerdept() {
		return ownerdept;
	}

	public void setOwnerdept(String ownerdept) {
		this.ownerdept = ownerdept;
	}

	public String getOwnerdeptname() {
		return ownerdeptname;
	}

	public void setOwnerdeptname(String ownerdeptname) {
		this.ownerdeptname = ownerdeptname;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

}
