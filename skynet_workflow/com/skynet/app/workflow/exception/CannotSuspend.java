//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\CannotSuspend.java

package com.skynet.app.workflow.exception;


/**
CannotSuspend Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class CannotSuspend extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC956016E
     */
    public CannotSuspend(String msg, Throwable nested) {
        super(msg, nested);  
    }
    
    /**
    @param msg
    @roseuid 3E6CC956016C
     */
    public CannotSuspend(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC956016B
     */
    public CannotSuspend() {
     
    }
}
