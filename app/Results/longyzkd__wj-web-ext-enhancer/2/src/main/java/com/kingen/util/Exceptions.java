package com.kingen.util;
 import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
public class Exceptions {


public RuntimeException unchecked(Throwable ex){
    if (ex instanceof RuntimeException) {
        return (RuntimeException) ex;
    } else {
        return new RuntimeException(ex);
    }
}


public String getErrorMessageWithNestedException(Throwable ex){
    Throwable nestedException = ex.getCause();
    return new StringBuilder().append(ex.getMessage()).append(" nested exception is ").append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage()).toString();
}


public Throwable getRootCause(Throwable ex){
    Throwable cause;
    while ((cause = ex.getCause()) != null) {
        ex = cause;
    }
    return ex;
}


public String getStackTraceAsString(Throwable ex){
    StringWriter stringWriter = new StringWriter();
    ex.printStackTrace(new PrintWriter(stringWriter));
    return stringWriter.toString();
}


public Throwable getThrowable(HttpServletRequest request){
    Throwable ex = null;
    if (request.getAttribute("exception") != null) {
        ex = (Throwable) request.getAttribute("exception");
    } else if (request.getAttribute("javax.servlet.error.exception") != null) {
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
    }
    return ex;
}


public boolean isCausedBy(Exception ex,Class<? extends Exception> causeExceptionClasses){
    Throwable cause = ex;
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