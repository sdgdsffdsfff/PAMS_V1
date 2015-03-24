//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\AlreadyRunning.java

package com.skynet.app.workflow.exception;


/**
AlreadyRunning Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class AlreadyRunning extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC956012E
     */
    public AlreadyRunning(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC956012C
     */
    public AlreadyRunning(String msg) {
        super(msg); 
    }
    
    /**
    @roseuid 3E6CC956012B
     */
    public AlreadyRunning() {
     
    }
}
