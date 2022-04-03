package com.yalcin;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserRepositoryImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserRepositoryImpl;
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



}