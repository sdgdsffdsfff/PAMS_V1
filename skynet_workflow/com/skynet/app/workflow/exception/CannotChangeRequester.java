//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\CannotChangeRequester.java

package com.skynet.app.workflow.exception;


/**
CannotChangeRequester Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class CannotChangeRequester extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560141
     */
    public CannotChangeRequester(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC956013F
     */
    public CannotChangeRequester(String msg) {
        super(msg); 
    }
    
    /**
    @roseuid 3E6CC956013E
     */
    public CannotChangeRequester() {
     
    }
}
