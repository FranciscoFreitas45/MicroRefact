package com.ukefu.webim.web.interceptor;
 import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.RequestLog;
import com.ukefu.webim.web.model.User;
import DTO.User;
public class LogIntercreptorHandler {


@Override
public void afterCompletion(HttpServletRequest request,HttpServletResponse arg1,Object arg2,Exception arg3){
}


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object arg2,ModelAndView arg3){
    HandlerMethod handlerMethod = (HandlerMethod) arg2;
    Object hander = handlerMethod.getBean();
    RequestMapping obj = handlerMethod.getMethod().getAnnotation(RequestMapping.class);
    if (!StringUtils.isBlank(request.getRequestURI()) && !(request.getRequestURI().startsWith("/message/ping") || request.getRequestURI().startsWith("/res/css") || request.getRequestURI().startsWith("/error") || request.getRequestURI().startsWith("/im/"))) {
        RequestLog log = new RequestLog();
        log.setEndtime(new Date());
        if (obj != null) {
            log.setName(obj.name());
        }
        log.setMethodname(handlerMethod.toString());
        log.setIp(request.getRemoteAddr());
        if (hander != null) {
            log.setClassname(hander.getClass().toString());
            if (hander instanceof Handler && ((Handler) hander).getStarttime() != 0) {
                log.setQuerytime(System.currentTimeMillis() - ((Handler) hander).getStarttime());
            }
        }
        log.setUrl(request.getRequestURI());
        log.setHostname(request.getRemoteHost());
        log.setEndtime(new Date());
        log.setType(UKDataContext.LogTypeEnum.REQUEST.toString());
        User user = (User) request.getSession(true).getAttribute(UKDataContext.USER_SESSION_NAME);
        if (user != null) {
            log.setUserid(user.getId());
            log.setUsername(user.getUsername());
            log.setUsermail(user.getEmail());
            log.setOrgi(user.getOrgi());
        }
        StringBuffer str = new StringBuffer();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String paraName = (String) names.nextElement();
            if (paraName.indexOf("password") >= 0) {
                str.append(paraName).append("=").append(UKTools.encryption(request.getParameter(paraName))).append(",");
            } else {
                str.append(paraName).append("=").append(request.getParameter(paraName)).append(",");
            }
        }
        Menu menu = handlerMethod.getMethod().getAnnotation(Menu.class);
        if (menu != null) {
            log.setFuntype(menu.type());
            log.setFundesc(menu.subtype());
            log.setName(menu.name());
        }
        log.setParameters(str.toString());
        UKTools.published(log);
    }
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object arg2){
    HandlerMethod handlerMethod = (HandlerMethod) arg2;
    Object hander = handlerMethod.getBean();
    if (hander instanceof Handler) {
        ((Handler) hander).setStarttime(System.currentTimeMillis());
    }
    return true;
}


}