//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\CannotStop.java

package com.skynet.app.workflow.exception;


/**
CannotStop Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class CannotStop extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560165
     */
    public CannotStop(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC9560163
     */
    public CannotStop(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC9560162
     */
    public CannotStop() {
     
    }
}
