package com.vino.scaffold.shiro;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.vino.scaffold.shiro.Interface.UserService;
import com.vino.scaffold.shiro.Interface.UserServiceImpl;
import com.vino.scaffold.shiro.Interface.ResourceService;
import com.vino.scaffold.shiro.Interface.ResourceServiceImpl;
import com.vino.scaffold.shiro.Interface.UserService;
import com.vino.scaffold.shiro.Interface.UserServiceImpl;
@SpringBootApplication
public class Main {


@Bean
public RestTemplate restTemplate(){
 
 return new RestTemplate();

  }



public static void main(String[] args){

SpringApplication.run(Main.class,args);

   }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public ResourceService resourceservice(){

return  new ResourceServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}