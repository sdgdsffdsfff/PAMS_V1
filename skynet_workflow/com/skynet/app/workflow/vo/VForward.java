package com.skynet.app.workflow.vo;

import java.util.ArrayList;
import java.util.List;

public class VForward {
	public String userid;
	public String loginname;
	public String username;
	public String usertype;
	public String deptid;
	public String deptcname;
	public String orgid;
	public String orgname;
	public String flowdefid;
	public String tableid;
	public String dataid;
	public String runactkey;
	public String runflowkey;
	public String beginactdefid;

	public List<VAct> endacts = new ArrayList();
}
