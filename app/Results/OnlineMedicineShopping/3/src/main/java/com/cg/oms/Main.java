package com.cg.oms;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cg.oms.Interface.User;
import com.cg.oms.Interface.UserImpl;
import com.cg.oms.Interface.Cart;
import com.cg.oms.Interface.CartImpl;
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
public User user(){

return  new UserImpl(); 
    }



@Bean
public Cart cart(){

return  new CartImpl(); 
    }



}