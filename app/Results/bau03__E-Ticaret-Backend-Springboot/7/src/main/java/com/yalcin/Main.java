package com.yalcin;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.ProductRepositoryImpl;
import com.yalcin.Interface.AdressRepository;
import com.yalcin.Interface.AdressRepositoryImpl;
import com.yalcin.Interface.OrderRepository;
import com.yalcin.Interface.OrderRepositoryImpl;
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
public ProductRepository productrepository(){

return  new ProductRepositoryImpl(); 
    }



@Bean
public AdressRepository adressrepository(){

return  new AdressRepositoryImpl(); 
    }



@Bean
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



}