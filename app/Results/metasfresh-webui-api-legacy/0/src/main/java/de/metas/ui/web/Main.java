package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.JSONLookupValue;
import Interface.JSONLookupValueImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.MenuTreeRepository;
import Interface.MenuTreeRepositoryImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.SqlViewFactory;
import Interface.SqlViewFactoryImpl;
import Interface.ProcessRestController;
import Interface.ProcessRestControllerImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
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
public JSONLookupValue jsonlookupvalue(){

return  new JSONLookupValueImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public MenuTreeRepository menutreerepository(){

return  new MenuTreeRepositoryImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public SqlViewFactory sqlviewfactory(){

return  new SqlViewFactoryImpl() 
    }



@Bean
public ProcessRestController processrestcontroller(){

return  new ProcessRestControllerImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public ViewId viewid(){

return  new ViewIdImpl() 
    }



}