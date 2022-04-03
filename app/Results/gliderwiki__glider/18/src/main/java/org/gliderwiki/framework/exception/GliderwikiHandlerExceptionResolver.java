package org.gliderwiki.framework.exception;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Lists;
public class GliderwikiHandlerExceptionResolver implements HandlerExceptionResolver{

 private Logger log;

 private  List<GliderwikiHandlerExceptionView> viewList;

 private  MessageSourceAccessor messageSourceAccessor;


public void logException(GliderwikiException ex){
    switch(ex.getLogLevel()) {
        case TRACE:
            log.trace(ex.getMessage(), ex);
            break;
        case DEBUG:
            log.debug(ex.getMessage(), ex);
            break;
        case INFO:
            log.info(ex.getMessage(), ex);
            break;
        case WARN:
            log.warn(ex.getMessage(), ex);
            break;
        default:
            log.error(ex.getMessage(), ex);
            break;
    }
}


public void setViewList(List<GliderwikiHandlerExceptionView> viewList){
    this.viewList = viewList;
}


public GliderwikiException refineException(Exception ex){
    if (ex instanceof GliderwikiException) {
        return (GliderwikiException) ex;
    }
    return new GliderwikiException(ex.getMessage(), ex);
}


public ModelAndView generateView(HttpServletRequest request,GliderwikiException gliderwikiException){
    String viewName = parseViewName(request, gliderwikiException);
    ModelAndView mav = new ModelAndView(viewName);
    String message = messageSourceAccessor.getMessage(gliderwikiException.getMessageCode(), gliderwikiException.getMessageArgs());
    mav.addObject("message", message);
    return mav;
}


public String parseViewName(HttpServletRequest request,GliderwikiException gliderwikiException){
    String prefix = "";
    String viewName = gliderwikiException.getViewName();
    for (GliderwikiHandlerExceptionView v : viewList) {
        if (v.isDefaultView()) {
            prefix = v.getPrefix();
        }
        if (v.match(request)) {
            prefix = v.getPrefix();
            if (v.getViewName() != null) {
                viewName = v.getViewName();
            }
            break;
        }
    }
    return prefix + viewName;
}


public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor){
    this.messageSourceAccessor = messageSourceAccessor;
}


@Override
public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
    GliderwikiException gliderwikiException = refineException(ex);
    logException(gliderwikiException);
    ModelAndView mav = generateView(request, gliderwikiException);
    setHttpResponseStatus(response, gliderwikiException);
    return mav;
}


public void setHttpResponseStatus(HttpServletResponse response,GliderwikiException gliderwikiException){
    response.setStatus(gliderwikiException.getHttpStatusCode());
}


}