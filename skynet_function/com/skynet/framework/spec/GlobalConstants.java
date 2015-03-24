package com.skynet.framework.spec;

public class GlobalConstants
{
    public static final String database_type = "SQLServer";	
    public static final String database_type_sqlserver = "SQLServer";
    public static final String database_type_db2 = "DB2";
    public static final String database_type_oracle = "Oracle";
    
    public static final String data_type_string = "0";
    public static final String data_type_number = "1";

	// 系统登录用户声明
	public static String sys_login_token = "sys_login_token";

	public static String sys_login_user = "sys_login_user";

	public static String sys_login_username = "sys_login_username";

	public static String sys_login_dept = "sys_login_dept";

	public static String sys_login_dept_internal = "sys_login_dept_internal";	

	public static String sys_login_deptname = "sys_login_deptname";
	
	public static String sys_login_userid = "sys_login_userid";

	public static String sys_login_org = "sys_login_org";
	
	public static String sys_login_org_internal = "sys_login_org_internal";	

	public static String sys_login_orgname = "sys_login_orgname";
	
	// 通用查询参数声明
	public static String spec_queryid = "spec_queryid";
	
	public static String spec_sql_app = "spec_sql_app";

	public static String spec_filter_field_html_names = "spec_filter_field_html_names";

	public static String spec_browse_field_names = "spec_browse_field_names";

	public static String spec_filter_field_names = "spec_filter_field_names";

	public static String spec_filter_field_types = "spec_filter_field_types";

	public static String spec_filter_field_values = "spec_filter_field_values";

	public static String spec_filter_field_oper = "spec_filter_field_oper";

	public static String spec_pkey = "spec_pkey";

	public static String spec_orderby = "spec_orderby";
	
	public static String spec_orderby_desc = "spec_orderby_desc";
	
	public static String spec_tableid = "spec_tableid";

	public static String spec_viewtype = "spec_viewtype";

	public static String spec_pagenum = "spec_pagenum";

	public static String spec_rownum = "spec_rownum";
	
	public static String spec_resultcount = "spec_resultcount";	
	
	public static int spec_rownum_value = 20;
	
	public static int spec_pagenum_value = 1;
	
	public static int spec_maxrownum_value = 3000;
	
	public static int create_days = -90;

    public static final String des_key = "sky."; 

}
