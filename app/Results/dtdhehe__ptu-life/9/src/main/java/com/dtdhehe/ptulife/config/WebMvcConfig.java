package com.dtdhehe.ptulife.config;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

@Autowired
 private  LoginInterceptor loginInterceptor;


@Override
public void addResourceHandlers(ResourceHandlerRegistry registry){
    // 配置虚拟上传路径，使SpringBoot可以读取本地磁盘文件
    registry.addResourceHandler("/uploads/**").addResourceLocations("file:D:/ptu/uploads/");
    registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/static/").addResourceLocations("classpath:/public/");
}


@Override
public void addInterceptors(InterceptorRegistry registry){
    // addPathPatterns("/**") 表示拦截所有的请求，
    // excludePathPatterns("...") 表示除了登陆,注册与首页之外，因为不需要登陆也可以访问
    // 排除资源请求
    registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/ptu/homePageController/registPage", "/index", "/login/loginController/login", "/ptu/registUserController/regist", "/ptu/registUserController/validUser").excludePathPatterns("/css/**").excludePathPatterns("/js/**").excludePathPatterns("/img/**");
    super.addInterceptors(registry);
}


}