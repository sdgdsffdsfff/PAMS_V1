//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\NotRunning.java

package com.skynet.app.workflow.exception;


/**
NotRunning Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class NotRunning extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601CE
     */
    public NotRunning(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601CC
     */
    public NotRunning(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601CB
     */
    public NotRunning() {
     
    }
}
