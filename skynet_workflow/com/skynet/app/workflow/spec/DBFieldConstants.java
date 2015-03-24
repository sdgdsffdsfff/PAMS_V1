package com.skynet.app.workflow.spec;

public class DBFieldConstants
{
	public static String RFLOW_STATE_NULL = "尚未实例化";	
	public static String RFlOW_STATE_INITIATED = "起始";
	public static String RFlOW_STATE_RUNNING = "准备运行";
	public static String RFlOW_STATE_ACTIVE = "运行中";
	public static String RFlOW_STATE_SUSPENDED = "挂起";
	public static String RFlOW_STATE_COMPLETED = "结束";
	public static String RFlOW_STATE_TERMINATED = "终止";
	
	public static String RACT_STATE_NULL = "尚未实例化";
	public static String RACT_STATE_INACTIVE = "待处理";
	public static String RACT_STATE_ACTIVE = "正处理";
	public static String RACT_STATE_SUSPENDED = "暂停";
	public static String RACT_STATE_COMPLETED = "已处理";
	
	public static String RFLOWAUTHOR_SOURCE_APPDEF = "应用定义";
	public static String RFLOWAUTHOR_SOURCE_FLOWDEF = "流程定义";
	public static String RFLOWAUTHOR_SOURCE_ACTDEF = "活动定义";
	
	public static String RFLOWREADER_SOURCE_APPDEF = "应用定义";
	public static String RFLOWREADER_SOURCE_FLOWDEF = "流程定义";
	public static String RFLOWREADER_SOURCE_ACTDEF = "流程定义";

	public static String PUB_PARTICIPATOR_PERSON = "PERSON";
	public static String PUB_PARTICIPATOR_ROLE = "ROLE";
	public static String PUB_PARTICIPATOR_DEPT = "DEPT";
	public static String PUB_PARTICIPATOR_WORKGROUP = "WORKGROUP";


	public static String BACT_CTYPE_BEGIN = "BEGIN";
	public static String BACT_CTYPE_END = "END";
	public static String BACT_CTYPE_NORMAL = "NORMAL";
	
	public static String RACTTASK_COMPLETE_YES = "完成";
	public static String RACTTASK_COMPLETE_NO = "未完成";
	
	public static String LEVENT_EVENTTYPE_FLOW = "流程";
	public static String LEVENT_EVENTTYPE_ACT = "活动";
	public static String LEVENT_EVENTTYPE_ROUTE = "路由";
	
	
	public static String LEVENTFLOW_EVENTTYPE_CREATE = "CREATE";
	public static String LEVENTFLOW_EVENTTYPE_SUSPEND = "SUSPEND";
	public static String LEVENTFLOW_EVENTTYPE_TERMINATE = "TERMINATE";
	public static String LEVENTFLOW_EVENTTYPE_RESUME = "RESUME";
	public static String LEVENTFLOW_EVENTTYPE_COMPLETE = "COMPLETE";
	
	public static String LEVENTROUTE_EVENTTYPE_FORWARD = "FORWARD";
	public static String LEVENTROUTE_EVENTTYPE_CFORWARD = "CFORWARD";
	
	public static String LEVENTROUTE_EVENTTYPE_BACKWARD = "BACKWARD";
	public static String LEVENTROUTE_EVENTTYPE_CBACKWARD = "CBACKWARD";
	public static String LEVENTROUTE_EVENTTYPE_CREATE = "CREATE";
	public static String LEVENTROUTE_EVENTTYPE_RESET = "RESET";
	
	public static String LEVENTROUTE_EVENTTYPE_CONSIGN_HANDER = "CONSIGN_HANDER";
	public static String LEVENTROUTE_EVENTTYPE_CONSIGN_ASSORTER = "CONSIGN_ASSORTER";
	
	public static String LEVENTACT_EVENTTYPE_CONSIGN_HANDER = "CONSIGN_HANDER";
	public static String LEVENTACT_EVENTTYPE_CONSIGN_ASSORTER = "CONSIGN_ASSORTER";
	public static String LEVENTACT_EVENTTYPE_FORWARD = "FORWARD";
	public static String LEVENTACT_EVENTTYPE_APPLY = "APPLY";
	
	
	public static String CTYPES_STR = "'PERSON', 'WORKGROUP', 'DEPT', 'ROLE', 'DEPTROLE'";
	public static String CTYPES_FORMULA_STR = "'FORMULA'";
	
	
	
	/*	
	public static String RFlOW_EVENT_INITIAL = "初始化";
	public static String RFlOW_EVENT_BEGIN = "开始";
	public static String RFlOW_EVENT_RUN = "运行";
	public static String RFlOW_EVENT_REREADY = "重新准备";
	public static String RFlOW_EVENT_SUSPEND = "挂起";
	public static String RFlOW_EVENT_RESUME = "恢复";
	public static String RFlOW_EVENT_TERMINATE = "终止";
	*/

	/*
	public static String RACT_EVENT_INITIAL = "初始化";
	public static String RACT_EVENT_BEGIN = "开始";
	public static String RACT_EVENT_REWAIT = "重新未开始";
	public static String RACT_EVENT_SUSPEND = "挂起";
	public static String RACT_EVENT_RESUME = "恢复";
	*/
	
	
}
