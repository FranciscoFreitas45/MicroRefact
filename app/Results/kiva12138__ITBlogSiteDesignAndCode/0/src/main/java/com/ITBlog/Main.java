package com.ITBlog;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ITBlog.Interface.UserService;
import com.ITBlog.Interface.UserServiceImpl;
import com.ITBlog.Interface.BlogService;
import com.ITBlog.Interface.BlogServiceImpl;
import com.ITBlog.Interface.CommentService;
import com.ITBlog.Interface.CommentServiceImpl;
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
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public BlogService blogservice(){

return  new BlogServiceImpl(); 
    }



@Bean
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



}