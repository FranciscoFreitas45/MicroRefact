package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.MetadataRetriever;
import org.opengeoportal.Interface.MetadataRetrieverImpl;
import org.opengeoportal.Interface.QuickDownload;
import org.opengeoportal.Interface.QuickDownloadImpl;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.BoundingBoxImpl;
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
public MetadataRetriever metadataretriever(){

return  new MetadataRetrieverImpl(); 
    }



@Bean
public QuickDownload quickdownload(){

return  new QuickDownloadImpl(); 
    }



@Bean
public BoundingBox boundingbox(){

return  new BoundingBoxImpl(); 
    }



}