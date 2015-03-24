//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\InvalidResource.java

package com.skynet.app.workflow.exception;


/**
InvalidResource Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class InvalidResource extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601AA
     */
    public InvalidResource(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC95601A8
     */
    public InvalidResource(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC95601A7
     */
    public InvalidResource() {
     
    }
}
