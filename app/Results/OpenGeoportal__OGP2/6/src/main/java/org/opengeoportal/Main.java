package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.GeoCommonsClient;
import org.opengeoportal.Interface.GeoCommonsClientImpl;
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
public GeoCommonsClient geocommonsclient(){

return  new GeoCommonsClientImpl(); 
    }



}