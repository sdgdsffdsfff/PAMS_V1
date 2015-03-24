//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\NotAssigned.java

package com.skynet.app.workflow.exception;


/**
NotAssigned Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class NotAssigned extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601BC
     */
    public NotAssigned(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601BA
     */
    public NotAssigned(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601B9
     */
    public NotAssigned() {
     
    }
}
