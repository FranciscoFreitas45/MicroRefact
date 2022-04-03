package com.yalcin;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.yalcin.Interface.User;
import com.yalcin.Interface.UserImpl;
import com.yalcin.Interface.Order;
import com.yalcin.Interface.OrderImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
import com.yalcin.Interface.User;
import com.yalcin.Interface.UserImpl;
import com.yalcin.Interface.Product;
import com.yalcin.Interface.ProductImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
import com.yalcin.Interface.User;
import com.yalcin.Interface.UserImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
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
public Order order(){

return  new OrderImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public Product product(){

return  new ProductImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



}