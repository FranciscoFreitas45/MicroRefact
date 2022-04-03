package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.PositionService;
import com.qidian.hcm.Interface.PositionServiceImpl;
import com.qidian.hcm.Interface.PositionRepository;
import com.qidian.hcm.Interface.PositionRepositoryImpl;
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
public PositionService positionservice(){

return  new PositionServiceImpl(); 
    }



@Bean
public PositionRepository positionrepository(){

return  new PositionRepositoryImpl(); 
    }



}