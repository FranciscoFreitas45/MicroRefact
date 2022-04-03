package com.dtdhehe.ptulife;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.dtdhehe.ptulife.Interface.UserService;
import com.dtdhehe.ptulife.Interface.UserServiceImpl;
import com.dtdhehe.ptulife.Interface.LabelService;
import com.dtdhehe.ptulife.Interface.LabelServiceImpl;
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
public LabelService labelservice(){

return  new LabelServiceImpl(); 
    }



}