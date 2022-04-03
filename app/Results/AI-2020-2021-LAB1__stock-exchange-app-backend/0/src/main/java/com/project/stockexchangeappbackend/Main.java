package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.StockRepository;
import com.project.stockexchangeappbackend.Interface.StockRepositoryImpl;
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
public StockRepository stockrepository(){

return  new StockRepositoryImpl(); 
    }



}