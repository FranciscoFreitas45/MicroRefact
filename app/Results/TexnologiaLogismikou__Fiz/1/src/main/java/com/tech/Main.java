package com.tech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.tech.Interface.IUserService;
import com.tech.Interface.IUserServiceImpl;
import com.tech.Interface.IChatroomEntitiesService;
import com.tech.Interface.IChatroomEntitiesServiceImpl;
import com.tech.Interface.IChatroomBlacklistService;
import com.tech.Interface.IChatroomBlacklistServiceImpl;
import com.tech.Interface.IChatroomWhitelistService;
import com.tech.Interface.IChatroomWhitelistServiceImpl;
import com.tech.Interface.IChatroomPrivilegesService;
import com.tech.Interface.IChatroomPrivilegesServiceImpl;
import com.tech.Interface.IChatroomMembersService;
import com.tech.Interface.IChatroomMembersServiceImpl;
import com.tech.Interface.ICRLocationService;
import com.tech.Interface.ICRLocationServiceImpl;
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
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



@Bean
public IChatroomEntitiesService ichatroomentitiesservice(){

return  new IChatroomEntitiesServiceImpl(); 
    }



@Bean
public IChatroomBlacklistService ichatroomblacklistservice(){

return  new IChatroomBlacklistServiceImpl(); 
    }



@Bean
public IChatroomWhitelistService ichatroomwhitelistservice(){

return  new IChatroomWhitelistServiceImpl(); 
    }



@Bean
public IChatroomPrivilegesService ichatroomprivilegesservice(){

return  new IChatroomPrivilegesServiceImpl(); 
    }



@Bean
public IChatroomMembersService ichatroommembersservice(){

return  new IChatroomMembersServiceImpl(); 
    }



@Bean
public ICRLocationService icrlocationservice(){

return  new ICRLocationServiceImpl(); 
    }



}