package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.StockService;
import com.project.stockexchangeappbackend.Interface.StockServiceImpl;
import com.project.stockexchangeappbackend.Interface.TransactionService;
import com.project.stockexchangeappbackend.Interface.TransactionServiceImpl;
import com.project.stockexchangeappbackend.Interface.StockRepository;
import com.project.stockexchangeappbackend.Interface.StockRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.StockService;
import com.project.stockexchangeappbackend.Interface.StockServiceImpl;
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
public StockService stockservice(){

return  new StockServiceImpl(); 
    }



@Bean
public TransactionService transactionservice(){

return  new TransactionServiceImpl(); 
    }



@Bean
public StockRepository stockrepository(){

return  new StockRepositoryImpl(); 
    }



@Bean
public StockService stockservice(){

return  new StockServiceImpl(); 
    }



}