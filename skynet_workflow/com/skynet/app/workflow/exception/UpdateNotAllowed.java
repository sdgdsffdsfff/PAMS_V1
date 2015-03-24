//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\UpdateNotAllowed.java

package com.skynet.app.workflow.exception;


/**
UpdateNotAllowed Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class UpdateNotAllowed extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560204
     */
    public UpdateNotAllowed(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC9560202
     */
    public UpdateNotAllowed(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC9560201
     */
    public UpdateNotAllowed() {
     
    }
}
