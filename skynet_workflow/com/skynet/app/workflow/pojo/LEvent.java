package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;


@Table("T_SYS_WFLEVENT")


public class LEvent extends IdEntity
{
	@Column
	public String eventtype;

	@Column
	public String runflowkey;

    @Column
    @ColDefine(type=ColType.DATETIME)
	public Timestamp eventtime;
	
	@Column
	public String ceventtime;	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getEventtype()
	{
		return eventtype;
	}

	public void setEventtype(String eventtype)
	{
		this.eventtype = eventtype;
	}

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public Timestamp getEventtime()
	{
		return eventtime;
	}

	public void setEventtime(Timestamp eventtime)
	{
		this.eventtime = eventtime;
	}

	public String getCeventtime()
	{
		return ceventtime;
	}

	public void setCeventtime(String ceventtime)
	{
		this.ceventtime = ceventtime;
	}
	
}
