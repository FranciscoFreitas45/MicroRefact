package com.dtdhehe.ptulife;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.dtdhehe.ptulife.Interface.NewsService;
import com.dtdhehe.ptulife.Interface.NewsServiceImpl;
import com.dtdhehe.ptulife.Interface.AnswerService;
import com.dtdhehe.ptulife.Interface.AnswerServiceImpl;
import com.dtdhehe.ptulife.Interface.CommentService;
import com.dtdhehe.ptulife.Interface.CommentServiceImpl;
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
public NewsService newsservice(){

return  new NewsServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



}