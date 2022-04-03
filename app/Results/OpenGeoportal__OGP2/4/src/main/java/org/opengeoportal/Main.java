package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.LayerRequest;
import org.opengeoportal.Interface.LayerRequestImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.DirectoryRetriever;
import org.opengeoportal.Interface.DirectoryRetrieverImpl;
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
public LayerRequest layerrequest(){

return  new LayerRequestImpl(); 
    }



@Bean
public HttpRequester httprequester(){

return  new HttpRequesterImpl(); 
    }



@Bean
public DirectoryRetriever directoryretriever(){

return  new DirectoryRetrieverImpl(); 
    }



}