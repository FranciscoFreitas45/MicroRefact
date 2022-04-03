package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.UserRepository;
import com.project.stockexchangeappbackend.Interface.UserRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.AllOrdersRepository;
import com.project.stockexchangeappbackend.Interface.AllOrdersRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.ResourceRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.TagService;
import com.project.stockexchangeappbackend.Interface.TagServiceImpl;
import com.project.stockexchangeappbackend.Interface.BannedAccessTokens;
import com.project.stockexchangeappbackend.Interface.BannedAccessTokensImpl;
import com.project.stockexchangeappbackend.Interface.ResourceService;
import com.project.stockexchangeappbackend.Interface.ResourceServiceImpl;
import com.project.stockexchangeappbackend.Interface.OrderService;
import com.project.stockexchangeappbackend.Interface.OrderServiceImpl;
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public AllOrdersRepository allordersrepository(){

return  new AllOrdersRepositoryImpl(); 
    }



@Bean
public ResourceRepository resourcerepository(){

return  new ResourceRepositoryImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public BannedAccessTokens bannedaccesstokens(){

return  new BannedAccessTokensImpl(); 
    }



@Bean
public ResourceService resourceservice(){

return  new ResourceServiceImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public TransactionService transactionservice(){

return  new TransactionServiceImpl(); 
    }



}