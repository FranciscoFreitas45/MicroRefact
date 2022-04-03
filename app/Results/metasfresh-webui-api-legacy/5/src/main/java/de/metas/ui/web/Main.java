package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.DocumentIdsSelection;
import Interface.DocumentIdsSelectionImpl;
import Interface.CreateViewRequest;
import Interface.CreateViewRequestImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
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
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public DocumentIdsSelection documentidsselection(){

return  new DocumentIdsSelectionImpl() 
    }



@Bean
public CreateViewRequest createviewrequest(){

return  new CreateViewRequestImpl() 
    }



@Bean
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
    }



}