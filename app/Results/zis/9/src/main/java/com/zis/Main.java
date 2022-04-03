package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.BookAmountDao;
import com.zis.Interface.BookAmountDaoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
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
public BookAmountDao bookamountdao(){

return  new BookAmountDaoImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



}