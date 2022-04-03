package com.project.stockexchangeappbackend.configuration;
 import com.project.stockexchangeappbackend.util.timemeasuring.ProcessingTime;
import com.project.stockexchangeappbackend.util.timemeasuring.ResponseBodyInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@AllArgsConstructor
public class CustomWebMvcConfiguration implements WebMvcConfigurer{

 private  ProcessingTime processingTime;


@Override
public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new ResponseBodyInterceptor(processingTime));
}


}