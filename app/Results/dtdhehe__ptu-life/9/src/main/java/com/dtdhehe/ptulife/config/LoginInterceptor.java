package com.dtdhehe.ptulife.config;
 import com.dtdhehe.ptulife.entity.PtuUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor{

 private  Logger logger;


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
}


@Override
public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    Object obj = request.getSession().getAttribute("loginUser");
    if (null == obj) {
        // 未登录，重定向到登录页
        logger.error("用户未登录");
        response.sendRedirect("/index");
        return false;
    }
    return true;
}


}