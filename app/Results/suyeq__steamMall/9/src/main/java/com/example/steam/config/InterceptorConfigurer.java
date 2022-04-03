package com.example.steam.config;
 import com.example.steam.interceptor.PermissionCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter{

@Autowired
 private  PermissionCheckInterceptor permissionCheckInterceptor;


@Override
public void addInterceptors(InterceptorRegistry registry){
    // addPathPatterns 添加路径
    // excludePathPatterns 排除路径
    registry.addInterceptor(permissionCheckInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/userVerification").excludePathPatterns("/admin/login");
    super.addInterceptors(registry);
}


}