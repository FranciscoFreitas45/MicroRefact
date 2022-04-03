package com.example.demo;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.interceptor.AdminInterceptor;
import com.example.demo.interceptor.AuthInterceptor;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

@Autowired
 private AuthInterceptor auth;

@Autowired
 private AdminInterceptor admin;


@Override
public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(auth).addPathPatterns("/admin/**", "/shopping-cart/order/**").excludePathPatterns("/sign-up", "/create-users", "/home", "/shopping-cart/update/*");
    registry.addInterceptor(admin).addPathPatterns("/admin/**").excludePathPatterns("/sign-up", "/create-users", "/home", "/shopping-cart/update/*");
}


}