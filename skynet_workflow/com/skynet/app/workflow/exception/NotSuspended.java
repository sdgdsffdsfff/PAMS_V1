//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\NotSuspended.java

package com.skynet.app.workflow.exception;


/**
NotSuspended Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class NotSuspended extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601D7
     */
    public NotSuspended(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601D5
     */
    public NotSuspended(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601D4
     */
    public NotSuspended() {
     
    }
}
