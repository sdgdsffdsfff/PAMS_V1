//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\WfException.java

package com.skynet.app.workflow.exception;


/**
WfException - Generic Workflow Exception
@created    2003.2.26
@author     wang gang
@version    1.0
 */
public class WfException extends GeneralException {
   
   /**
   Constructs an <code>WfException</code> with the specified detail message and 
   nested exception.
   @param msg the detail message.
   @param nested the nested exception
   @roseuid 3E6CC92D01B7
    */
   public WfException(String msg, Throwable nested) {
       super(msg,nested);
   }
   
   /**
   Constructs an <code>WfException</code> with the specified detail message.
   @param msg the detail message.
   @roseuid 3E6CC92D01B5
    */
   public WfException(String msg) {
       super(msg); 
   }
   
   /**
   Creates new <code>WfException</code> without detail message.
   @roseuid 3E6CC92D01B4
    */
   public WfException() {
       super();
   }
}
