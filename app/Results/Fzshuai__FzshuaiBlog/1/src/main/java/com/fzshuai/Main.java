package com.fzshuai;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fzshuai.Interface.BlogRepository;
import com.fzshuai.Interface.BlogRepositoryImpl;
import com.fzshuai.Interface.BlogRepository;
import com.fzshuai.Interface.BlogRepositoryImpl;
import com.fzshuai.Interface.TypeService;
import com.fzshuai.Interface.TypeServiceImpl;
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
public BlogRepository blogrepository(){

return  new BlogRepositoryImpl(); 
    }



@Bean
public BlogRepository blogrepository(){

return  new BlogRepositoryImpl(); 
    }



@Bean
public TypeService typeservice(){

return  new TypeServiceImpl(); 
    }



}