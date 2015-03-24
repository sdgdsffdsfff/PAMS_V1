package com.skynet.app.workflow.vo;

import java.util.ArrayList;
import java.util.List;

public class VAct {
	public String actdefid;
	public List<VActUser> actowners = new ArrayList();
	public List<VActUser> actagenters = new ArrayList();
}
