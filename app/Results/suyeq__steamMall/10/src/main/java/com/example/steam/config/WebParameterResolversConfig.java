package com.example.steam.config;
 import com.example.steam.resolvers.AdminUserArgumentResolve;
import com.example.steam.resolvers.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;
@Configuration
public class WebParameterResolversConfig extends WebMvcConfigurerAdapter{

@Autowired
 private UserArgumentResolver userArgumentResolver;

@Autowired
 private AdminUserArgumentResolve adminUserArgumentResolve;


@Override
public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
    argumentResolvers.add(userArgumentResolver);
    argumentResolvers.add(adminUserArgumentResolve);
}


}