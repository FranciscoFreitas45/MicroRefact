package com.yalcin;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.yalcin.Interface.UserRepository;
import com.yalcin.Interface.UserRepositoryImpl;
import com.yalcin.Interface.RoleRepository;
import com.yalcin.Interface.RoleRepositoryImpl;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.Interface.ProductRepositoryImpl;
import com.yalcin.Interface.ProductService;
import com.yalcin.Interface.ProductServiceImpl;
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
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



@Bean
public ProductRepository productrepository(){

return  new ProductRepositoryImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



}