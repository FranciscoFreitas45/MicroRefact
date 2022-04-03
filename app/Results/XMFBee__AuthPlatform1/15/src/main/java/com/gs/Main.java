package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.IncomingOutgoingServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
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
public IncomingOutgoingService incomingoutgoingservice(){

return  new IncomingOutgoingServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}