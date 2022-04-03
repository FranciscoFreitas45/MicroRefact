package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.DebugProperties;
import Interface.DebugPropertiesImpl;
import Interface.DocumentEntityDataBindingDescriptor;
import Interface.DocumentEntityDataBindingDescriptorImpl;
import Interface.HUEditorView;
import Interface.HUEditorViewImpl;
import Interface.HUEditorRow;
import Interface.HUEditorRowImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.BankStatementReconciliationViewFactory;
import Interface.BankStatementReconciliationViewFactoryImpl;
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
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
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
public DebugProperties debugproperties(){

return  new DebugPropertiesImpl() 
    }



@Bean
public DocumentEntityDataBindingDescriptor documententitydatabindingdescriptor(){

return  new DocumentEntityDataBindingDescriptorImpl() 
    }



@Bean
public HUEditorView hueditorview(){

return  new HUEditorViewImpl() 
    }



@Bean
public HUEditorRow hueditorrow(){

return  new HUEditorRowImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public BankStatementReconciliationViewFactory bankstatementreconciliationviewfactory(){

return  new BankStatementReconciliationViewFactoryImpl() 
    }



}