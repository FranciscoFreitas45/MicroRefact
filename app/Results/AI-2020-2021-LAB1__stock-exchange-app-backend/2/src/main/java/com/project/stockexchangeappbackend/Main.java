package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.UserRepository;
import com.project.stockexchangeappbackend.Interface.UserRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import com.project.stockexchangeappbackend.Interface.OrderRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.ArchivedOrderRepository;
import com.project.stockexchangeappbackend.Interface.ArchivedOrderRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.ResourceRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.StockIndexValueRepository;
import com.project.stockexchangeappbackend.Interface.StockIndexValueRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.TagService;
import com.project.stockexchangeappbackend.Interface.TagServiceImpl;
import com.project.stockexchangeappbackend.Interface.ResourceRepository;
import com.project.stockexchangeappbackend.Interface.ResourceRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import com.project.stockexchangeappbackend.Interface.OrderRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.UserRepository;
import com.project.stockexchangeappbackend.Interface.UserRepositoryImpl;
import com.project.stockexchangeappbackend.Interface.StockIndexValueService;
import com.project.stockexchangeappbackend.Interface.StockIndexValueServiceImpl;
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
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



@Bean
public ArchivedOrderRepository archivedorderrepository(){

return  new ArchivedOrderRepositoryImpl(); 
    }



@Bean
public ResourceRepository resourcerepository(){

return  new ResourceRepositoryImpl(); 
    }



@Bean
public StockIndexValueRepository stockindexvaluerepository(){

return  new StockIndexValueRepositoryImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public ResourceRepository resourcerepository(){

return  new ResourceRepositoryImpl(); 
    }



@Bean
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public StockIndexValueService stockindexvalueservice(){

return  new StockIndexValueServiceImpl(); 
    }



}