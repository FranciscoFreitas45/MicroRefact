package com.csquard.mregister;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.csquard.mregister.Interface.UserRepository;
import com.csquard.mregister.Interface.UserRepositoryImpl;
import com.csquard.mregister.Interface.RoleRepository;
import com.csquard.mregister.Interface.RoleRepositoryImpl;
import com.csquard.mregister.Interface.AsmRepository;
import com.csquard.mregister.Interface.AsmRepositoryImpl;
import com.csquard.mregister.Interface.JwtTokenProvider;
import com.csquard.mregister.Interface.JwtTokenProviderImpl;
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
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



@Bean
public AsmRepository asmrepository(){

return  new AsmRepositoryImpl(); 
    }



@Bean
public JwtTokenProvider jwttokenprovider(){

return  new JwtTokenProviderImpl(); 
    }



}