package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.BoundingBoxImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.OgcInfoRequestImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
import org.opengeoportal.Interface.SearchConfigRetriever;
import org.opengeoportal.Interface.SearchConfigRetrieverImpl;
import org.opengeoportal.Interface.OgpHttpClient;
import org.opengeoportal.Interface.OgpHttpClientImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
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
public BoundingBox boundingbox(){

return  new BoundingBoxImpl(); 
    }



@Bean
public HttpRequester httprequester(){

return  new HttpRequesterImpl(); 
    }



@Bean
public OgcInfoRequest ogcinforequest(){

return  new OgcInfoRequestImpl(); 
    }



@Bean
public ProxyConfigRetriever proxyconfigretriever(){

return  new ProxyConfigRetrieverImpl(); 
    }



@Bean
public SearchConfigRetriever searchconfigretriever(){

return  new SearchConfigRetrieverImpl(); 
    }



@Bean
public OgpHttpClient ogphttpclient(){

return  new OgpHttpClientImpl(); 
    }



@Bean
public HttpRequester httprequester(){

return  new HttpRequesterImpl(); 
    }



@Bean
public HttpRequester httprequester(){

return  new HttpRequesterImpl(); 
    }



@Bean
public ProxyConfigRetriever proxyconfigretriever(){

return  new ProxyConfigRetrieverImpl(); 
    }



}