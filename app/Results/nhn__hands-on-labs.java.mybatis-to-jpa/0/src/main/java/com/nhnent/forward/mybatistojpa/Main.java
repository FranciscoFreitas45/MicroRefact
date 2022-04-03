package com.nhnent.forward.mybatistojpa;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.nhnent.forward.mybatistojpa.Interface.OrderItemMapper;
import com.nhnent.forward.mybatistojpa.Interface.OrderItemMapperImpl;
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
public OrderItemMapper orderitemmapper(){

return  new OrderItemMapperImpl(); 
    }



}