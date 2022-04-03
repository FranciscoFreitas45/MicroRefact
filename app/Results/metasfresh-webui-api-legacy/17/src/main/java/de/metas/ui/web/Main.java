package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.LookupDescriptorProvider;
import Interface.LookupDescriptorProviderImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.WebsocketSender;
import Interface.WebsocketSenderImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.JSONDocumentPath;
import Interface.JSONDocumentPathImpl;
import Interface.SqlSelectDisplayValue;
import Interface.SqlSelectDisplayValueImpl;
import Interface.SqlOrderByValue;
import Interface.SqlOrderByValueImpl;
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
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public LookupDescriptorProvider lookupdescriptorprovider(){

return  new LookupDescriptorProviderImpl() 
    }



@Bean
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public WebsocketSender websocketsender(){

return  new WebsocketSenderImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public JSONDocumentPath jsondocumentpath(){

return  new JSONDocumentPathImpl() 
    }



@Bean
public SqlSelectDisplayValue sqlselectdisplayvalue(){

return  new SqlSelectDisplayValueImpl() 
    }



@Bean
public SqlOrderByValue sqlorderbyvalue(){

return  new SqlOrderByValueImpl() 
    }



@Bean
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
    }



}