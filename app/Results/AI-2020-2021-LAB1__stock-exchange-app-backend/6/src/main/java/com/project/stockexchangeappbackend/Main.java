package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.UserDTO;
import com.project.stockexchangeappbackend.Interface.UserDTOImpl;
import com.project.stockexchangeappbackend.Interface.OrderService;
import com.project.stockexchangeappbackend.Interface.OrderServiceImpl;
import com.project.stockexchangeappbackend.Interface.TransactionService;
import com.project.stockexchangeappbackend.Interface.TransactionServiceImpl;
import com.project.stockexchangeappbackend.Interface.TransactionService;
import com.project.stockexchangeappbackend.Interface.TransactionServiceImpl;
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
public UserDTO userdto(){

return  new UserDTOImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public TransactionService transactionservice(){

return  new TransactionServiceImpl(); 
    }



@Bean
public TransactionService transactionservice(){

return  new TransactionServiceImpl(); 
    }



}