//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\AlreadySuspended.java

package com.skynet.app.workflow.exception;


/**
AlreadySuspended Workflow Exception
@created    October 29, 2002
@author wang gang
@version    1.0
 */
public final class AlreadySuspended extends WfException {
   
   /**
   @param msg
   @param nested
   @roseuid 3E6CC9560138
    */
   public AlreadySuspended(String msg, Throwable nested) {
       super(msg,nested);    
   }
   
   /**
   @param msg
   @roseuid 3E6CC9560136
    */
   public AlreadySuspended(String msg) {
       super(msg);
   }
   
   /**
   @roseuid 3E6CC9560135
    */
   public AlreadySuspended() 
   {
    
   }
}
