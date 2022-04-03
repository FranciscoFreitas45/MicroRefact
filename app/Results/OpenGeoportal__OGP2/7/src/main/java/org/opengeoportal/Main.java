package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.BoundingBoxImpl;
import org.opengeoportal.Interface.SolrRecord;
import org.opengeoportal.Interface.SolrRecordImpl;
import org.opengeoportal.Interface.SolrRecord;
import org.opengeoportal.Interface.SolrRecordImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
import org.opengeoportal.Interface.OgpHttpClient;
import org.opengeoportal.Interface.OgpHttpClientImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
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
public BoundingBox boundingbox(){

return  new BoundingBoxImpl(); 
    }



@Bean
public SolrRecord solrrecord(){

return  new SolrRecordImpl(); 
    }



@Bean
public SolrRecord solrrecord(){

return  new SolrRecordImpl(); 
    }



@Bean
public RequestStatusManager requeststatusmanager(){

return  new RequestStatusManagerImpl(); 
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
public OgpHttpClient ogphttpclient(){

return  new OgpHttpClientImpl(); 
    }



@Bean
public RequestStatusManager requeststatusmanager(){

return  new RequestStatusManagerImpl(); 
    }



@Bean
public DirectoryRetriever directoryretriever(){

return  new DirectoryRetrieverImpl(); 
    }



}