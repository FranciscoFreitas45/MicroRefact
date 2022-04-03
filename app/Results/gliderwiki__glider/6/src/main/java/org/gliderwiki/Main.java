package org.gliderwiki;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.CommonServiceImpl;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.CommonServiceImpl;
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
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



@Bean
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



}