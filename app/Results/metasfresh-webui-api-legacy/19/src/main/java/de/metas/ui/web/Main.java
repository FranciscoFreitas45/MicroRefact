package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
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
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
    }



}