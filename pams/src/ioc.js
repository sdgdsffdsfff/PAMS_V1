var ioc={

    dataSource : {

        type:"org.apache.commons.dbcp.BasicDataSource",
        singleton:true,
        events:
        {
            depose:'close'
        },

        fields : 
        {
           driverClassName:'oracle.jdbc.driver.OracleDriver',
           url:'jdbc:oracle:thin:@develop:1521:orcl',
           username:'pams',
           password:'pams1234'
        }
    },
    
	dao : {
		type : "com.skynet.framework.dao.SkynetDao",
		args : [ {
			refer : "dataSource"
		} ]
	},
	
	log : {
		type : 'org.nutz.aop.interceptor.LoggingMethodInterceptor'
	},
	
	txNONE : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [0]
    },
    txREAD_UNCOMMITTED : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [1]
    },
    txREAD_COMMITTED : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [2]
    },
    txREPEATABLE_READ : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [4]
    },
    txSERIALIZABLE : {
        type : 'org.nutz.aop.interceptor.TransactionInterceptor',
        args : [8]
    },
    
    $aop : 
	{
	    type : 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',
	    fields : 
	    {
	        aopConfigrations  : 
	        [
	            {	
	            	type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
	                fields : 
	                {
	                    itemList : 
	                    [
	                     	['com\\.skynet\\..+\\.service','.+','ioc:txREAD_COMMITTED']
	                   	]
	                }
	            },
	            {	type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration' }
	        ]
	    }
	}
}