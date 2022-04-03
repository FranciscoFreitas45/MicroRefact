package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.PhResourceService;
import cn.offway.athena.Interface.PhResourceServiceImpl;
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
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public PhResourceService phresourceservice(){

return  new PhResourceServiceImpl(); 
    }



}