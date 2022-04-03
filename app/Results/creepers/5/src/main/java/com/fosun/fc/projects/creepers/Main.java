package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.CreditChinaGovProcessor;
import com.fosun.fc.projects.creepers.Interface.CreditChinaGovProcessorImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListCreditServiceImpl;
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
public CreditChinaGovProcessor creditchinagovprocessor(){

return  new CreditChinaGovProcessorImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



@Bean
public ICreepersListService icreeperslistservice(){

return  new ICreepersListServiceImpl(); 
    }



@Bean
public ICreepersListCreditService icreeperslistcreditservice(){

return  new ICreepersListCreditServiceImpl(); 
    }



}