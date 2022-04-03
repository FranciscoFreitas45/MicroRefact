package org.vaadin.paul.spring;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.vaadin.paul.spring.Interface.EspecialidadRepository;
import org.vaadin.paul.spring.Interface.EspecialidadRepositoryImpl;
import org.vaadin.paul.spring.Interface.CentroRepository;
import org.vaadin.paul.spring.Interface.CentroRepositoryImpl;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepositoryImpl;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.UserRepositoryImpl;
import org.vaadin.paul.spring.Interface.EspecialidadRepository;
import org.vaadin.paul.spring.Interface.EspecialidadRepositoryImpl;
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
public EspecialidadRepository especialidadrepository(){

return  new EspecialidadRepositoryImpl(); 
    }



@Bean
public CentroRepository centrorepository(){

return  new CentroRepositoryImpl(); 
    }



@Bean
public TrabajadorRepository trabajadorrepository(){

return  new TrabajadorRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public EspecialidadRepository especialidadrepository(){

return  new EspecialidadRepositoryImpl(); 
    }



}