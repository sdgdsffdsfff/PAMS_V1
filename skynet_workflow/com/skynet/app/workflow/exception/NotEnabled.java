//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\NotEnabled.java

package com.skynet.app.workflow.exception;


/**
NotEnabled Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class NotEnabled extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601C5
     */
    public NotEnabled(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601C3
     */
    public NotEnabled(String msg) {
        super(msg); 
    }
    
    /**
    @roseuid 3E6CC95601C2
     */
    public NotEnabled() {
     
    }
}
