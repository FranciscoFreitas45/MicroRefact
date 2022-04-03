package com.fzshuai;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fzshuai.Interface.TagRepository;
import com.fzshuai.Interface.TagRepositoryImpl;
import com.fzshuai.Interface.BlogService;
import com.fzshuai.Interface.BlogServiceImpl;
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
public TagRepository tagrepository(){

return  new TagRepositoryImpl(); 
    }



@Bean
public BlogService blogservice(){

return  new BlogServiceImpl(); 
    }



@Bean
public TypeService typeservice(){

return  new TypeServiceImpl(); 
    }



}