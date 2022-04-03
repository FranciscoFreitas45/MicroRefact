package com.project.stockexchangeappbackend.configuration;
 import com.project.stockexchangeappbackend.security.BannedAccessTokens;
import com.project.stockexchangeappbackend.util.timemeasuring.ProcessingTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
@Configuration
public class CustomBeansConfiguration {


@Bean
@RequestScope
public ProcessingTime responseObject(){
    return new ProcessingTime();
}


@Bean
public GlobalMemory globalMemory(){
    return new SystemInfo().getHardware().getMemory();
}


@Bean
public BannedAccessTokens bannedAccessToken(){
    return new BannedAccessTokens();
}


}