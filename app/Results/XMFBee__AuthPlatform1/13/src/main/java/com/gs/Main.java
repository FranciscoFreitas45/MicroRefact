package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
import com.gs.Interface.Checkin;
import com.gs.Interface.CheckinImpl;
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
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public Checkin checkin(){

return  new CheckinImpl(); 
    }



}