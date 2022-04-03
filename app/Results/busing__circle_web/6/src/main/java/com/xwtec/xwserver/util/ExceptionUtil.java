package com.xwtec.xwserver.util;
 public class ExceptionUtil {


public String ExceptionToString(Exception exception){
    StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(exception.toString() + "\n");
    StackTraceElement[] stacks = exception.getStackTrace();
    if (stacks != null) {
        for (StackTraceElement stack : stacks) {
            errorMessage.append("\t" + stack.toString() + "\n");
        }
    }
    return errorMessage.toString();
}


}