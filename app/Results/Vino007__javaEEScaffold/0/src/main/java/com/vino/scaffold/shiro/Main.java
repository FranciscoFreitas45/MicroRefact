package com.vino.scaffold.shiro;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.vino.scaffold.shiro.Interface.RoleRepository;
import com.vino.scaffold.shiro.Interface.RoleRepositoryImpl;
import com.vino.scaffold.shiro.Interface.RoleService;
import com.vino.scaffold.shiro.Interface.RoleServiceImpl;
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
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



}