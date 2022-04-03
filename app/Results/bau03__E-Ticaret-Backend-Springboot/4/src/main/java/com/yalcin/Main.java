package com.yalcin;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserRepositoryImpl;
import com.yalcin.Interface.ActiveSessionsRepository;
import com.yalcin.Interface.ActiveSessionsRepositoryImpl;
import com.yalcin.Interface.AdressService;
import com.yalcin.Interface.AdressServiceImpl;
import com.yalcin.Interface.SellerBeginService;
import com.yalcin.Interface.SellerBeginServiceImpl;
import com.yalcin.Interface.ProductService;
import com.yalcin.Interface.ProductServiceImpl;
import com.yalcin.Interface.OrderRepository;
import com.yalcin.Interface.OrderRepositoryImpl;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.ProductRepositoryImpl;
import com.yalcin.Interface.JwtProvider;
import com.yalcin.Interface.JwtProviderImpl;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.ProductRepositoryImpl;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserRepositoryImpl;
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
public ActiveSessionsRepository activesessionsrepository(){

return  new ActiveSessionsRepositoryImpl(); 
    }



@Bean
public AdressService adressservice(){

return  new AdressServiceImpl(); 
    }



@Bean
public SellerBeginService sellerbeginservice(){

return  new SellerBeginServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public OrderRepository orderrepository(){

return  new OrderRepositoryImpl(); 
    }



@Bean
public ProductRepository productrepository(){

return  new ProductRepositoryImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



@Bean
public ProductRepository productrepository(){

return  new ProductRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



}