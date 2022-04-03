package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.NewRecordDescriptorsProvider;
import Interface.NewRecordDescriptorsProviderImpl;
import Interface.DocumentWebsocketPublisher;
import Interface.DocumentWebsocketPublisherImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.DetailId;
import Interface.DetailIdImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.Document;
import Interface.DocumentImpl;
import Interface.AvailableToPromiseAdapter;
import Interface.AvailableToPromiseAdapterImpl;
import Interface.ProductLookupDescriptor;
import Interface.ProductLookupDescriptorImpl;
import Interface.DocumentEntityDescriptor;
import Interface.DocumentEntityDescriptorImpl;
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
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public NewRecordDescriptorsProvider newrecorddescriptorsprovider(){

return  new NewRecordDescriptorsProviderImpl() 
    }



@Bean
public DocumentWebsocketPublisher documentwebsocketpublisher(){

return  new DocumentWebsocketPublisherImpl() 
    }



@Bean
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
    }



@Bean
public DetailId detailid(){

return  new DetailIdImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public Document document(){

return  new DocumentImpl() 
    }



@Bean
public AvailableToPromiseAdapter availabletopromiseadapter(){

return  new AvailableToPromiseAdapterImpl() 
    }



@Bean
public ProductLookupDescriptor productlookupdescriptor(){

return  new ProductLookupDescriptorImpl() 
    }



@Bean
public DocumentEntityDescriptor documententitydescriptor(){

return  new DocumentEntityDescriptorImpl() 
    }



}