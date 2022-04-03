package com.zammc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zammc.Interface.IdWorker;
import com.zammc.Interface.IdWorkerImpl;
import com.zammc.Interface.IdWorker;
import com.zammc.Interface.IdWorkerImpl;
import com.zammc.Interface.GoodsCateService;
import com.zammc.Interface.GoodsCateServiceImpl;
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
public IdWorker idworker(){

return  new IdWorkerImpl(); 
    }



@Bean
public IdWorker idworker(){

return  new IdWorkerImpl(); 
    }



@Bean
public GoodsCateService goodscateservice(){

return  new GoodsCateServiceImpl(); 
    }



}