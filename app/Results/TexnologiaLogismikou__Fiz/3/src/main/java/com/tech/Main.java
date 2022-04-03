package com.tech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.tech.Interface.IChatroomEntitiesService;
import com.tech.Interface.IChatroomEntitiesServiceImpl;
import com.tech.Interface.IUserService;
import com.tech.Interface.IUserServiceImpl;
import com.tech.Interface.ICRLocationService;
import com.tech.Interface.ICRLocationServiceImpl;
import com.tech.Interface.IChatroomMembersService;
import com.tech.Interface.IChatroomMembersServiceImpl;
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
public IChatroomEntitiesService ichatroomentitiesservice(){

return  new IChatroomEntitiesServiceImpl(); 
    }



@Bean
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



@Bean
public ICRLocationService icrlocationservice(){

return  new ICRLocationServiceImpl(); 
    }



@Bean
public IChatroomMembersService ichatroommembersservice(){

return  new IChatroomMembersServiceImpl(); 
    }



}