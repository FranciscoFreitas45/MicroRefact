package com.cg.hbm;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.cg.hbm.Request.UserRequest;
import com.cg.hbm.Request.Impl.UserRequestImpl;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.*;
import org.springframework.boot.*;
import java.util.Arrays;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableSpringConfigured
@ComponentScan
public class Main {


@Bean
//@LoadBalanced
public RestTemplate restTemplate(){
 
 return new RestTemplate();

  }

@Bean 
   public UserRequest UserRequest(){
      return new UserRequestImpl();
   }



public static void main(String[] args){

SpringApplication.run(Main.class,args);

   }

/*@Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      System.out.println("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }
    };
  }   */
}

