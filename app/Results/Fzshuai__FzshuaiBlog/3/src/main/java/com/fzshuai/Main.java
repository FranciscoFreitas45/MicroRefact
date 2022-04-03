package com.fzshuai;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fzshuai.Interface.BlogService;
import com.fzshuai.Interface.BlogServiceImpl;
import com.fzshuai.Interface.BlogService;
import com.fzshuai.Interface.BlogServiceImpl;
import com.fzshuai.Interface.TagService;
import com.fzshuai.Interface.TagServiceImpl;
import com.fzshuai.Interface.BlogService;
import com.fzshuai.Interface.BlogServiceImpl;
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



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public BlogService blogservice(){

return  new BlogServiceImpl(); 
    }



}