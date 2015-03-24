//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\InvalidState.java

package com.skynet.app.workflow.exception;


/**
InvalidState Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class InvalidState extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601B3
     */
    public InvalidState(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601B1
     */
    public InvalidState(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601B0
     */
    public InvalidState() {
     
    }
}
