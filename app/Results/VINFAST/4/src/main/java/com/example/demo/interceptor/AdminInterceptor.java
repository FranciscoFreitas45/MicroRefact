package com.example.demo.interceptor;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.demo.entity.Users;
import com.example.demo.service.SessionService;
@Component
public class AdminInterceptor implements HandlerInterceptor{

@Autowired
 private SessionService sessionService;


@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
    Users user = sessionService.get("user");
    String error = "";
    boolean admin = user.isAdmin();
    if (!admin) {
        error = "Please login Role Admin!";
        if (error.length() > 0) {
            response.sendRedirect("/login?error=" + error);
            return false;
        }
    }
    return true;
}


}