//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\RequesterRequired.java

package com.skynet.app.workflow.exception;


/**
RequesterRequired Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class RequesterRequired extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601E0
     */
    public RequesterRequired(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601DE
     */
    public RequesterRequired(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601DD
     */
    public RequesterRequired() {
     
    }
}
