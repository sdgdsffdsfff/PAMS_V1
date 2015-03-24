//Source file: D:\\work\\telcom97\\code\\com\\butone\\workflow\\runtime\\exception\\GeneralException.java

package com.skynet.app.workflow.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
@created    2003.2.26
@author      wang gang
@version    1.0
 */
public class GeneralException extends Exception 
{
   
    Throwable nested = null;

    /**
     * Creates new <code>GeneralException</code> without detail message.
     */
    public GeneralException() {
        super();
    }

    /**
     * Constructs an <code>GeneralException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public GeneralException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>GeneralException</code> with the specified detail message and nested Exception.
     * @param msg the detail message.
     */
    public GeneralException(String msg, Throwable nested) {
        super(msg);
        this.nested = nested;
    }

    /** Returns the detail message, including the message from the nested exception if there is one. */
    public String getMessage() {
        if (nested != null)
            return super.getMessage() + " (" + nested.getMessage() + ")";
        else
            return super.getMessage();
    }

    /** Prints the composite message to System.err. */
    public void printStackTrace() {
        super.printStackTrace();
        if (nested != null) nested.printStackTrace();
    }

    /** Prints the composite message and the embedded stack trace to the specified stream ps. */
    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (nested != null) nested.printStackTrace(ps);
    }

    /** Prints the composite message and the embedded stack trace to the specified print writer pw. */
    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (nested != null) nested.printStackTrace(pw);
    }
}
