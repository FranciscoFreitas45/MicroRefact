package io.swagger;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import io.swagger.Interface.OrderRepository;
import io.swagger.Interface.OrderRepositoryImpl;
import io.swagger.Interface.SessionRepository;
import io.swagger.Interface.SessionRepositoryImpl;
import io.swagger.Interface.SessionRepository;
import io.swagger.Interface.SessionRepositoryImpl;
import io.swagger.Interface.OrderRepository;
import io.swagger.Interface.OrderRepositoryImpl;
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
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



@Bean
public SessionRepository sessionrepository(){

return  new SessionRepositoryImpl(); 
    }



@Bean
public SessionRepository sessionrepository(){

return  new SessionRepositoryImpl(); 
    }



@Bean
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



}