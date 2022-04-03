package org.gliderwiki.framework.exception;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.google.common.collect.Lists;
public class GliderwikiHandlerExceptionView {

 private  String prefix;

 private  boolean defaultView;

 private  String viewName;

 private  List<String> uriPatterns;


public String getPrefix(){
    return prefix;
}


public void setViewName(String viewName){
    this.viewName = viewName;
}


public void setPrefix(String prefix){
    this.prefix = prefix;
}


public void setDefaultView(boolean defaultView){
    this.defaultView = defaultView;
}


public boolean isDefaultView(){
    return defaultView;
}


public boolean match(HttpServletRequest request){
    boolean uriCheck = false;
    String uri = getRequestUriWithoutContextPath(request);
    if (uri != null & uriPatterns.size() != 0) {
        for (String pattern : uriPatterns) {
            if (uri.matches(pattern)) {
                uriCheck = true;
                break;
            }
        }
    }
    return uriCheck;
}


public String getViewName(){
    return viewName;
}


public List<String> getUriPatterns(){
    return uriPatterns;
}


public String getRequestUriWithoutContextPath(HttpServletRequest request){
    if (StringUtils.isBlank(request.getContextPath())) {
        return request.getRequestURI();
    }
    return request.getRequestURI().substring(request.getContextPath().length());
}


public void setUriPatterns(List<String> uriPatterns){
    this.uriPatterns = uriPatterns;
}


}