package com.empl.mgr;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.empl.mgr.Interface.AccountService;
import com.empl.mgr.Interface.AccountServiceImpl;
import com.empl.mgr.Interface.AccountService;
import com.empl.mgr.Interface.AccountServiceImpl;
import com.empl.mgr.Interface.AccountService;
import com.empl.mgr.Interface.AccountServiceImpl;
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
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



}