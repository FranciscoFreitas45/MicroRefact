package com.xwtec.xwserver.controller.interceptor;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.util.DataSourceContextHolder;
public class ClearThreadInterceptor implements HandlerInterceptor{


public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object controller,Exception exception){
}


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object controller,ModelAndView modelAndView){
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object controller){
    DataSourceContextHolder.clear();
    return true;
}


}