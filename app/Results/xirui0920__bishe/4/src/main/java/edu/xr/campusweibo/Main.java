package edu.xr.campusweibo;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import edu.xr.campusweibo.Interface.PersistentTokenRepository;
import edu.xr.campusweibo.Interface.PersistentTokenRepositoryImpl;
import edu.xr.campusweibo.Interface.PersistentTokenRepository;
import edu.xr.campusweibo.Interface.PersistentTokenRepositoryImpl;
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
public PersistentTokenRepository persistenttokenrepository(){

return  new PersistentTokenRepositoryImpl(); 
    }



@Bean
public PersistentTokenRepository persistenttokenrepository(){

return  new PersistentTokenRepositoryImpl(); 
    }



}