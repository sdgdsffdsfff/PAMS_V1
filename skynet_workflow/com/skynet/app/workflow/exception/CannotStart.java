//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\CannotStart.java

package com.skynet.app.workflow.exception;


/**
CannotStart Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class CannotStart extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC956015C
     */
    public CannotStart(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC956015A
     */
    public CannotStart(String msg) {
        super(msg); 
    }
    
    /**
    @roseuid 3E6CC9560159
     */
    public CannotStart() {
     
    }
}
