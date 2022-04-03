package com.csquard.mregister;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.csquard.mregister.Interface.AsmRepository;
import com.csquard.mregister.Interface.AsmRepositoryImpl;
import com.csquard.mregister.Interface.SalesRegionRepository;
import com.csquard.mregister.Interface.SalesRegionRepositoryImpl;
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
public AsmRepository asmrepository(){

return  new AsmRepositoryImpl(); 
    }



@Bean
public SalesRegionRepository salesregionrepository(){

return  new SalesRegionRepositoryImpl(); 
    }



}