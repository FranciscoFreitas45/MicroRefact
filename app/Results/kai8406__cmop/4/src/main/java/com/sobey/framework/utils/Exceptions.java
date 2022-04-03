package com.sobey.framework.utils;
 import java.io.PrintWriter;
import java.io.StringWriter;
public class Exceptions {


public RuntimeException unchecked(Exception e){
    if (e instanceof RuntimeException) {
        return (RuntimeException) e;
    } else {
        return new RuntimeException(e);
    }
}


public String getStackTraceAsString(Exception e){
    StringWriter stringWriter = new StringWriter();
    e.printStackTrace(new PrintWriter(stringWriter));
    return stringWriter.toString();
}


public boolean isCausedBy(Exception ex,Class<? extends Exception> causeExceptionClasses){
    Throwable cause = ex.getCause();
    while (cause != null) {
        for (Class<? extends Exception> causeClass : causeExceptionClasses) {
            if (causeClass.isInstance(cause)) {
                return true;
            }
        }
        cause = cause.getCause();
    }
    return false;
}


}