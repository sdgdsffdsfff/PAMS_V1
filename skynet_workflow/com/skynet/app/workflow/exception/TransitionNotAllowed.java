//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\TransitionNotAllowed.java

package com.skynet.app.workflow.exception;


/**
TransitionNotAllowed Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class TransitionNotAllowed extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601FB
     */
    public TransitionNotAllowed(String msg, Throwable nested) {
        super(msg, nested);      
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601F9
     */
    public TransitionNotAllowed(String msg) {
        super(msg); 
    }
    
    /**
    @roseuid 3E6CC95601F8
     */
    public TransitionNotAllowed() {
     
    }
}
