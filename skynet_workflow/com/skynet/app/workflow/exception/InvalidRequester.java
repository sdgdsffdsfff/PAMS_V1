//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\InvalidRequester.java

package com.skynet.app.workflow.exception;


/**
InvalidRequester Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class InvalidRequester extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC95601A1
     */
    public InvalidRequester(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC956019F
     */
    public InvalidRequester(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC956019E
     */
    public InvalidRequester() {
     
    }
}
