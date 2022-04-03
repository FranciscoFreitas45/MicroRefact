package com.example.steam.resolvers;
 import com.example.steam.entity.User;
import com.example.steam.service.UserService;
import com.example.steam.utils.StaticField;
import com.example.steam.vo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

@Autowired
 private UserService userService;


public String getCookieValue(HttpServletRequest request,String cookie_token){
    Cookie[] cookies = request.getCookies();
    if (cookies == null || cookies.length <= 0) {
        return null;
    }
    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals(cookie_token)) {
            return cookies[i].getValue();
        }
    }
    return null;
}


@Override
public Object resolveArgument(MethodParameter methodParameter,ModelAndViewContainer modelAndViewContainer,NativeWebRequest nativeWebRequest,WebDataBinderFactory webDataBinderFactory){
    HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
    HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();
    String cookieToken = getCookieValue(request, StaticField.COOKIE_KEY);
    // System.out.println(cookieToken+"  "+"token");
    LoginUser user = userService.getUserByToken(response, cookieToken);
    // request.getSession().setAttribute("email",user.getEmail());
    return user;
}


@Override
public boolean supportsParameter(MethodParameter methodParameter){
    Class clazz = methodParameter.getParameterType();
    return clazz == LoginUser.class;
}


}