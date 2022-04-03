package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.PickingSlotViewRepository;
import Interface.PickingSlotViewRepositoryImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.ViewActionDescriptorsList;
import Interface.ViewActionDescriptorsListImpl;
import Interface.IViewDataRepository;
import Interface.IViewDataRepositoryImpl;
import Interface.IViewInvalidationAdvisor;
import Interface.IViewInvalidationAdvisorImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.SqlAndParams;
import Interface.SqlAndParamsImpl;
import Interface.IntegerLookupValue;
import Interface.IntegerLookupValueImpl;
import Interface.DebugProperties;
import Interface.DebugPropertiesImpl;
import Interface.GeoLocationDocumentDescriptor;
import Interface.GeoLocationDocumentDescriptorImpl;
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
public PickingSlotViewRepository pickingslotviewrepository(){

return  new PickingSlotViewRepositoryImpl() 
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
public ViewId viewid(){

return  new ViewIdImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public ViewActionDescriptorsList viewactiondescriptorslist(){

return  new ViewActionDescriptorsListImpl() 
    }



@Bean
public IViewDataRepository iviewdatarepository(){

return  new IViewDataRepositoryImpl() 
    }



@Bean
public IViewInvalidationAdvisor iviewinvalidationadvisor(){

return  new IViewInvalidationAdvisorImpl() 
    }



@Bean
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public SqlAndParams sqlandparams(){

return  new SqlAndParamsImpl() 
    }



@Bean
public IntegerLookupValue integerlookupvalue(){

return  new IntegerLookupValueImpl() 
    }



@Bean
public DebugProperties debugproperties(){

return  new DebugPropertiesImpl() 
    }



@Bean
public GeoLocationDocumentDescriptor geolocationdocumentdescriptor(){

return  new GeoLocationDocumentDescriptorImpl() 
    }



}