package org.gliderwiki;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.gliderwiki.Interface.SpaceService;
import org.gliderwiki.Interface.SpaceServiceImpl;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.EntityDao;
import org.gliderwiki.Interface.EntityDaoImpl;
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
public SpaceService spaceservice(){

return  new SpaceServiceImpl(); 
    }



@Bean
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public EntityDao entitydao(){

return  new EntityDaoImpl(); 
    }



}