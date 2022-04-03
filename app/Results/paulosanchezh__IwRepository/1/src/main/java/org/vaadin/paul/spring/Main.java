package org.vaadin.paul.spring;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.vaadin.paul.spring.Interface.CitaRepository;
import org.vaadin.paul.spring.Interface.CitaRepositoryImpl;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
import org.vaadin.paul.spring.Interface.SanitarioRepositoryImpl;
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
public CitaRepository citarepository(){

return  new CitaRepositoryImpl(); 
    }



@Bean
public SanitarioRepository sanitariorepository(){

return  new SanitarioRepositoryImpl(); 
    }



}