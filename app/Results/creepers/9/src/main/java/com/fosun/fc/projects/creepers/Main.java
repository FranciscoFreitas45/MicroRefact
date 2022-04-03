package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.TaxEvasionProcessor;
import com.fosun.fc.projects.creepers.Interface.TaxEvasionProcessorImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobService;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListServiceImpl;
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
public TaxEvasionProcessor taxevasionprocessor(){

return  new TaxEvasionProcessorImpl(); 
    }



@Bean
public ICreepersJobService icreepersjobservice(){

return  new ICreepersJobServiceImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



@Bean
public ICreepersListService icreeperslistservice(){

return  new ICreepersListServiceImpl(); 
    }



}