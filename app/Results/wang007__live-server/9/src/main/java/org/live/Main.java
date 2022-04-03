package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.UserService;
import org.live.Interface.UserServiceImpl;
import org.live.Interface.MenuService;
import org.live.Interface.MenuServiceImpl;
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
public MenuService menuservice(){

return  new MenuServiceImpl(); 
    }



}