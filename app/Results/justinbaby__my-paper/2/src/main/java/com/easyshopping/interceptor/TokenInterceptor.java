package com.easyshopping.interceptor;
 import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easyshopping.util.WebUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class TokenInterceptor extends HandlerInterceptorAdapter{

 private  String TOKEN_ATTRIBUTE_NAME;

 private  String TOKEN_COOKIE_NAME;

 private  String TOKEN_PARAMETER_NAME;

 private  String ERROR_MESSAGE;


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    String token = WebUtils.getCookie(request, TOKEN_COOKIE_NAME);
    if (request.getMethod().equalsIgnoreCase("POST")) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
                return true;
            } else {
                response.addHeader("tokenStatus", "accessDenied");
            }
        } else {
            if (token != null && token.equals(request.getParameter(TOKEN_PARAMETER_NAME))) {
                return true;
            }
        }
        if (token == null) {
            token = UUID.randomUUID().toString();
            WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
        }
        response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
        return false;
    } else {
        if (token == null) {
            token = UUID.randomUUID().toString();
            WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
        }
        request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
        return true;
    }
}


}