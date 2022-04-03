package org.gliderwiki.util;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
public class RequestManager {

 public  String L7_X_FORWARDED_FOR_HEADER_NAME;


public Object getSessionAttribute(HttpServletRequest request,String attributeName){
    HttpSession session = request.getSession(false);
    if (session == null) {
        return null;
    }
    return session.getAttribute(attributeName);
}


public HttpServletRequest getHttpServletRequest(){
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = sra.getRequest();
    return request;
}


public String getRemoteAddress(HttpServletRequest request){
    String remoteAddr = request.getHeader(L7_X_FORWARDED_FOR_HEADER_NAME);
    if (StringUtils.isBlank(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
    } else {
        int indexOfComma = remoteAddr.indexOf(",");
        if (indexOfComma >= 0) {
            remoteAddr = remoteAddr.substring(0, indexOfComma);
        }
    }
    return remoteAddr;
}


}