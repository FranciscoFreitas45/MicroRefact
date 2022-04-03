package com.easyshopping.interceptor;
 import java.net.URLEncoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.easyshopping.Principal;
import com.easyshopping.entity.Member;
import com.easyshopping.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class MemberInterceptor extends HandlerInterceptorAdapter{

 private  String REDIRECT_VIEW_NAME_PREFIX;

 private  String REDIRECT_URL_PARAMETER_NAME;

 private  String MEMBER_ATTRIBUTE_NAME;

 private  String DEFAULT_LOGIN_URL;

 private  String loginUrl;

@Value("${url_escaping_charset}")
 private  String urlEscapingCharset;

@Resource(name = "memberServiceImpl")
 private  MemberService memberService;


@Override
public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
    if (modelAndView != null) {
        String viewName = modelAndView.getViewName();
        if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
            modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, memberService.getCurrent());
        }
    }
}


public String getLoginUrl(){
    return loginUrl;
}


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    HttpSession session = request.getSession();
    Principal principal = (Principal) session.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
    if (principal != null) {
        return true;
    } else {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            response.addHeader("loginStatus", "accessDenied");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            if (request.getMethod().equalsIgnoreCase("GET")) {
                String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
                response.sendRedirect(request.getContextPath() + loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "=" + URLEncoder.encode(redirectUrl, urlEscapingCharset));
            } else {
                response.sendRedirect(request.getContextPath() + loginUrl);
            }
            return false;
        }
    }
}


public void setLoginUrl(String loginUrl){
    this.loginUrl = loginUrl;
}


}