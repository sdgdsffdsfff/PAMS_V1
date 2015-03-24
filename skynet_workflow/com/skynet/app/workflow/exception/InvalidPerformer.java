//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\InvalidPerformer.java

package com.skynet.app.workflow.exception;


/**
InvalidPerformer Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class InvalidPerformer extends WfException {
    
    /**
    @param msg
    @param nested
    @roseuid 3E6CC9560198
     */
    public InvalidPerformer(String msg, Throwable nested) {
        super(msg, nested); 
    }
    
    /**
    @param msg
    @roseuid 3E6CC9560196
     */
    public InvalidPerformer(String msg) {
        super(msg);
    }
    
    /**
    @roseuid 3E6CC9560195
     */
    public InvalidPerformer() {
     
    }
}
