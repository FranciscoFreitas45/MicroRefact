package org.gliderwiki.framework.exception;
 import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartException;
import com.google.common.collect.Maps;
public class GliderwikiException extends RuntimeException{

 private  long serialVersionUID;

 public  LogLevel DEFAULT_LOGLEVEL;

 public  String DEFAULT_MESSAGE_CODE;

 public  String DEFAULT_VIEW_NAME;

 private  Map<Class<? extends Throwable>,LogLevel> EXCEPTION_LOGLEVEL_MAP;

 private  String messageCode;

 private  String[] messageArgs;

 private  String viewName;

 private  LogLevel logLevel;

public GliderwikiException() {
    this(null, null);
}public GliderwikiException(String message) {
    this(message, null);
}public GliderwikiException(Throwable cause) {
    this(cause.getMessage(), cause);
}public GliderwikiException(String message, Throwable cause) {
    super(message, cause);
    distinguishCauseLogLevel(cause);
}
public void setViewName(String viewName){
    this.viewName = viewName;
}


public void setLogLevel(LogLevel logLevel){
    this.logLevel = logLevel;
}


public String getDefaultViewName(){
    return DEFAULT_VIEW_NAME;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public void distinguishCauseLogLevel(Throwable cause){
    if (cause == null) {
        return;
    }
    LogLevel causeLogLevel = EXCEPTION_LOGLEVEL_MAP.get(cause.getClass());
    if (causeLogLevel != null) {
        setLogLevel(causeLogLevel);
    }
}


public void setMessageArgs(String messageArgs){
    this.messageArgs = messageArgs;
}


public String[] getMessageArgs(){
    return messageArgs;
}


public int getHttpStatusCode(){
    return HttpServletResponse.SC_BAD_REQUEST;
}


public String getDefaultMessageCode(){
    return DEFAULT_MESSAGE_CODE;
}


public String getViewName(){
    return viewName;
}


public LogLevel getLogLevel(){
    return logLevel;
}


public String getMessageCode(){
    return messageCode;
}


public LogLevel getDefaultLoglevel(){
    return DEFAULT_LOGLEVEL;
}


public void setMessageCode(String messageCode){
    this.messageCode = messageCode;
}


}