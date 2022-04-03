package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
import org.opengeoportal.Interface.PropertiesFile;
import org.opengeoportal.Interface.PropertiesFileImpl;
import org.opengeoportal.Interface.OgpHttpClient;
import org.opengeoportal.Interface.OgpHttpClientImpl;
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
public ProxyConfigRetriever proxyconfigretriever(){

return  new ProxyConfigRetrieverImpl(); 
    }



@Bean
public PropertiesFile propertiesfile(){

return  new PropertiesFileImpl(); 
    }



@Bean
public OgpHttpClient ogphttpclient(){

return  new OgpHttpClientImpl(); 
    }



}