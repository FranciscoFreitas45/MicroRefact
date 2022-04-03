package org.jeecgframework.core.util;
 import java.io.PrintWriter;
import java.io.StringWriter;
public class ExceptionUtil {


public String getExceptionMessage(Exception ex){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    return sw.toString();
}


}