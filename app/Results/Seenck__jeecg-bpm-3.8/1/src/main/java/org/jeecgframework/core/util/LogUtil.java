package org.jeecgframework.core.util;
 import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
public class LogUtil {

 private  String LOGCONFIG;

 private  Logger objLog;


public void trace(String message,Exception exception){
    try {
        log("TRACE", message, exception);
    } catch (Exception ex) {
    }
}


public void debug(String message){
    try {
        log("DEBUG", message);
    } catch (Exception ex) {
    }
}


public void log(String level,Object msg,Throwable e){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    try {
        StringBuilder sb = new StringBuilder();
        Throwable t = new Throwable();
        t.printStackTrace(pw);
        String input = sw.getBuffer().toString();
        StringReader sr = new StringReader(input);
        BufferedReader br = new BufferedReader(sr);
        for (int i = 0; i < 4; i++) br.readLine();
        String line = br.readLine();
        // at com.media.web.action.DicManageAction.list(DicManageAction.java:89)
        int paren = line.indexOf("at ");
        line = line.substring(paren + 3);
        paren = line.indexOf('(');
        String invokeInfo = line.substring(0, paren);
        int period = invokeInfo.lastIndexOf('.');
        sb.append('[');
        sb.append(invokeInfo.substring(0, period));
        sb.append(':');
        sb.append(invokeInfo.substring(period + 1));
        sb.append("():");
        paren = line.indexOf(':');
        period = line.lastIndexOf(')');
        sb.append(line.substring(paren + 1, period));
        sb.append(']');
        sb.append(" - ");
        sb.append(msg);
        getLogger().log((Priority) Level.toLevel(level), sb.toString(), e);
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            sw.close();
            pw.close();
        } catch (Exception e2) {
        }
    }
}


public Logger getLogger(){
    if (objLog == null) {
        // DOMConfigurator.configure(getConfigFile());
        objLog = LogManager.getLogger(LogUtil.class);
    }
    return objLog;
}


public void warning(String message){
    try {
        log("WARN", message);
    } catch (Exception ex) {
    }
}


public void error(String message){
    try {
        log("ERROR", message);
    } catch (Exception ex) {
    }
}


public String getConfigFile(){
    String s = LogUtil.class.getClassLoader().getResource("").toString();
    String filePath = s + LOGCONFIG;
    return filePath;
}


public void info(Object message){
    log("INFO", message);
}


public void fatal(String message){
    try {
        log("FATAL", message);
    } catch (Exception ex) {
    }
}


}