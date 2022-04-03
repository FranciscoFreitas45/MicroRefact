package com.tech;
 import com.tech.configurations.InitializeValidators;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer{


public void main(String[] args){
    InitializeValidators.InitializeCustomValidators();
    SpringApplication.run(Application.class, args);
}


@Override
public SpringApplicationBuilder configure(SpringApplicationBuilder application){
    return application.sources(Application.class);
}


}