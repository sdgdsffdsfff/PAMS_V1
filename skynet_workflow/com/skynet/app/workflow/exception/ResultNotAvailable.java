//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\ResultNotAvailable.java

package com.skynet.app.workflow.exception;


/**
ResultNotAvailable Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class ResultNotAvailable extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601E9
     */
    public ResultNotAvailable(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601E7
     */
    public ResultNotAvailable(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601E6
     */
    public ResultNotAvailable() {
     
    }
}
