package com.vino.scaffold.shiro;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.vino.scaffold.shiro.Interface.RoleService;
import com.vino.scaffold.shiro.Interface.RoleServiceImpl;
import com.vino.scaffold.shiro.Interface.ResourceRepository;
import com.vino.scaffold.shiro.Interface.ResourceRepositoryImpl;
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
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public ResourceRepository resourcerepository(){

return  new ResourceRepositoryImpl(); 
    }



}