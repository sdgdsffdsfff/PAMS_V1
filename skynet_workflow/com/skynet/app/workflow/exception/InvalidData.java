//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\InvalidData.java

package com.skynet.app.workflow.exception;


/**
InvalidData Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class InvalidData extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560186
     */
    public InvalidData(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC9560184
     */
    public InvalidData(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC9560183
     */
    public InvalidData() {
     
    }
}
