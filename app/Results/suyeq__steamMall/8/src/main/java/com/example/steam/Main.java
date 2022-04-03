package com.example.steam;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.TypeService;
import com.example.steam.Interface.TypeServiceImpl;
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
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public TypeService typeservice(){

return  new TypeServiceImpl(); 
    }



}