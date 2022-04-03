package org.opengeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.OgcInfoRequestImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.DirectoryRetriever;
import org.opengeoportal.Interface.DirectoryRetrieverImpl;
import org.opengeoportal.Interface.SolrRecord;
import org.opengeoportal.Interface.SolrRecordImpl;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.BoundingBoxImpl;
import org.opengeoportal.Interface.BoundingBox;
import org.opengeoportal.Interface.BoundingBoxImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
import org.opengeoportal.Interface.LayerInfoRetriever;
import org.opengeoportal.Interface.LayerInfoRetrieverImpl;
import org.opengeoportal.Interface.DirectoryRetriever;
import org.opengeoportal.Interface.DirectoryRetrieverImpl;
import org.opengeoportal.Interface.AugmentedSolrRecordRetriever;
import org.opengeoportal.Interface.AugmentedSolrRecordRetrieverImpl;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.OgcInfoRequestImpl;
import org.opengeoportal.Interface.FeatureSourceToShape;
import org.opengeoportal.Interface.FeatureSourceToShapeImpl;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.HttpRequesterImpl;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.OgcInfoRequestImpl;
import org.opengeoportal.Interface.OgcInfoRequest;
import org.opengeoportal.Interface.OgcInfoRequestImpl;
import org.opengeoportal.Interface.MetadataRetriever;
import org.opengeoportal.Interface.MetadataRetrieverImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
import org.opengeoportal.Interface.ProxyConfigRetriever;
import org.opengeoportal.Interface.ProxyConfigRetrieverImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
import org.opengeoportal.Interface.RequestStatusManager;
import org.opengeoportal.Interface.RequestStatusManagerImpl;
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
public OgcInfoRequest ogcinforequest(){

return  new OgcInfoRequestImpl(); 
    }



@Bean
public HttpRequester httprequester(){

return  new HttpRequesterImpl(); 
    }



@Bean
public DirectoryRetriever directoryretriever(){

return  new DirectoryRetrieverImpl(); 
    }



@Bean
public SolrRecord solrrecord(){

return  new SolrRecordImpl(); 
    }



@Bean
public BoundingBox boundingbox(){

return  new BoundingBoxImpl(); 
    }



@Bean
public BoundingBox boundingbox(){

return  new BoundingBoxImpl(); 
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
public DirectoryRetriever directoryretriever(){

return  new DirectoryRetrieverImpl(); 
    }



@Bean
public AugmentedSolrRecordRetriever augmentedsolrrecordretriever(){

return  new AugmentedSolrRecordRetrieverImpl(); 
    }



@Bean
public OgcInfoRequest ogcinforequest(){

return  new OgcInfoRequestImpl(); 
    }



@Bean
public FeatureSourceToShape featuresourcetoshape(){

return  new FeatureSourceToShapeImpl(); 
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
public OgcInfoRequest ogcinforequest(){

return  new OgcInfoRequestImpl(); 
    }



@Bean
public MetadataRetriever metadataretriever(){

return  new MetadataRetrieverImpl(); 
    }



@Bean
public RequestStatusManager requeststatusmanager(){

return  new RequestStatusManagerImpl(); 
    }



@Bean
public ProxyConfigRetriever proxyconfigretriever(){

return  new ProxyConfigRetrieverImpl(); 
    }



@Bean
public ProxyConfigRetriever proxyconfigretriever(){

return  new ProxyConfigRetrieverImpl(); 
    }



@Bean
public RequestStatusManager requeststatusmanager(){

return  new RequestStatusManagerImpl(); 
    }



@Bean
public RequestStatusManager requeststatusmanager(){

return  new RequestStatusManagerImpl(); 
    }



}