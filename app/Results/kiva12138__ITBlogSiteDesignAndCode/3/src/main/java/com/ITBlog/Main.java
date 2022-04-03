package com.ITBlog;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ITBlog.Interface.BlogService;
import com.ITBlog.Interface.BlogServiceImpl;
import com.ITBlog.Interface.BlogService;
import com.ITBlog.Interface.BlogServiceImpl;
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
public BlogService blogservice(){

return  new BlogServiceImpl(); 
    }



@Bean
public BlogService blogservice(){

return  new BlogServiceImpl(); 
    }



}