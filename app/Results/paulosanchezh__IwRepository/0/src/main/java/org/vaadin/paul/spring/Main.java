package org.vaadin.paul.spring;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
import org.vaadin.paul.spring.Interface.SanitarioRepositoryImpl;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepositoryImpl;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.UserRepositoryImpl;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.UserRepositoryImpl;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
import org.vaadin.paul.spring.Interface.SanitarioRepositoryImpl;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepositoryImpl;
import org.vaadin.paul.spring.Interface.SanitarioRepository;
import org.vaadin.paul.spring.Interface.SanitarioRepositoryImpl;
import org.vaadin.paul.spring.Interface.TrabajadorRepository;
import org.vaadin.paul.spring.Interface.TrabajadorRepositoryImpl;
import org.vaadin.paul.spring.Interface.UserRepository;
import org.vaadin.paul.spring.Interface.UserRepositoryImpl;
import org.vaadin.paul.spring.Interface.User;
import org.vaadin.paul.spring.Interface.UserImpl;
import org.vaadin.paul.spring.Interface.User;
import org.vaadin.paul.spring.Interface.UserImpl;
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
public SanitarioRepository sanitariorepository(){

return  new SanitarioRepositoryImpl(); 
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public SanitarioRepository sanitariorepository(){

return  new SanitarioRepositoryImpl(); 
    }



@Bean
public TrabajadorRepository trabajadorrepository(){

return  new TrabajadorRepositoryImpl(); 
    }



@Bean
public SanitarioRepository sanitariorepository(){

return  new SanitarioRepositoryImpl(); 
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
public User user(){

return  new UserImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}