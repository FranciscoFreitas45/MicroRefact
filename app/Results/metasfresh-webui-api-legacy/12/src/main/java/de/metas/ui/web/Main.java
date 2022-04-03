package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
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
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



}