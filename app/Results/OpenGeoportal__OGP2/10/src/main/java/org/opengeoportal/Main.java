package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.OgpHttpClient;
import org.opengeoportal.Interface.OgpHttpClientImpl;
import org.opengeoportal.Interface.ContactInformationType;
import org.opengeoportal.Interface.ContactInformationTypeImpl;
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
public LayerInfoRetriever layerinforetriever(){

return  new LayerInfoRetrieverImpl(); 
    }



@Bean
public LayerInfoRetriever layerinforetriever(){

return  new LayerInfoRetrieverImpl(); 
    }



@Bean
public OgpHttpClient ogphttpclient(){

return  new OgpHttpClientImpl(); 
    }



@Bean
public ContactInformationType contactinformationtype(){

return  new ContactInformationTypeImpl(); 
    }



}