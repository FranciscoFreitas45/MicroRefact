package org.jeecgframework.core.util;
 import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
public class ContextHolderUtils {

 private  Map<String,HttpSession> sessionMap;


public void removeSession(String sessionId){
    if (sessionMap.containsKey(sessionId)) {
        sessionMap.remove(sessionId);
    }
}


public HttpServletRequest getRequest(){
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request;
}


public HttpSession getSession(String sessionId){
    HttpSession session = sessionMap.get(sessionId);
    return session == null ? getSession() : session;
}


}