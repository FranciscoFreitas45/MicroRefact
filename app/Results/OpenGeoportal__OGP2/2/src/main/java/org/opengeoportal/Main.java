package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.LegendGraphic;
import org.opengeoportal.Interface.LegendGraphicImpl;
import org.opengeoportal.Interface.PropertyNameType;
import org.opengeoportal.Interface.PropertyNameTypeImpl;
import org.opengeoportal.Interface.PropertyNameType;
import org.opengeoportal.Interface.PropertyNameTypeImpl;
import org.opengeoportal.Interface.RemoteOWS;
import org.opengeoportal.Interface.RemoteOWSImpl;
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
public LegendGraphic legendgraphic(){

return  new LegendGraphicImpl(); 
    }



@Bean
public PropertyNameType propertynametype(){

return  new PropertyNameTypeImpl(); 
    }



@Bean
public PropertyNameType propertynametype(){

return  new PropertyNameTypeImpl(); 
    }



@Bean
public RemoteOWS remoteows(){

return  new RemoteOWSImpl(); 
    }



}