//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\CannotResume.java

package com.skynet.app.workflow.exception;


/**
CannotResume Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class CannotResume extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560153
     */
    public CannotResume(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC9560151
     */
    public CannotResume(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC9560150
     */
    public CannotResume() {
     
    }
}
