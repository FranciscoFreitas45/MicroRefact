package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.RoleService;
import org.live.Interface.RoleServiceImpl;
import org.live.Interface.RoleRepository;
import org.live.Interface.RoleRepositoryImpl;
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
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



}