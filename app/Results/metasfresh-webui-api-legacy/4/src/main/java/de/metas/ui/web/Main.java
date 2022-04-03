package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.WebsocketSender;
import Interface.WebsocketSenderImpl;
import Interface.ProcessId;
import Interface.ProcessIdImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentFilterDescriptorsProvider;
import Interface.DocumentFilterDescriptorsProviderImpl;
import Interface.DocumentFilterDescriptorsProvidersService;
import Interface.DocumentFilterDescriptorsProvidersServiceImpl;
import Interface.DocumentPrint;
import Interface.DocumentPrintImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentIdsSelection;
import Interface.DocumentIdsSelectionImpl;
import Interface.ProcessRestController;
import Interface.ProcessRestControllerImpl;
import Interface.MenuTreeRepository;
import Interface.MenuTreeRepositoryImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.AddressDescriptorFactory;
import Interface.AddressDescriptorFactoryImpl;
import Interface.AddressLayout;
import Interface.AddressLayoutImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.LookupDescriptorProvider;
import Interface.LookupDescriptorProviderImpl;
import Interface.DefaultValueExpressionsFactory;
import Interface.DefaultValueExpressionsFactoryImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.ASILayout;
import Interface.ASILayoutImpl;
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
public WebsocketSender websocketsender(){

return  new WebsocketSenderImpl() 
    }



@Bean
public ProcessId processid(){

return  new ProcessIdImpl() 
    }



@Bean
public ViewId viewid(){

return  new ViewIdImpl() 
    }



@Bean
public DocumentFilterDescriptorsProvider documentfilterdescriptorsprovider(){

return  new DocumentFilterDescriptorsProviderImpl() 
    }



@Bean
public DocumentFilterDescriptorsProvidersService documentfilterdescriptorsprovidersservice(){

return  new DocumentFilterDescriptorsProvidersServiceImpl() 
    }



@Bean
public DocumentPrint documentprint(){

return  new DocumentPrintImpl() 
    }



@Bean
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public DocumentIdsSelection documentidsselection(){

return  new DocumentIdsSelectionImpl() 
    }



@Bean
public ProcessRestController processrestcontroller(){

return  new ProcessRestControllerImpl() 
    }



@Bean
public MenuTreeRepository menutreerepository(){

return  new MenuTreeRepositoryImpl() 
    }



@Bean
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public AddressDescriptorFactory addressdescriptorfactory(){

return  new AddressDescriptorFactoryImpl() 
    }



@Bean
public AddressLayout addresslayout(){

return  new AddressLayoutImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public LookupDescriptorProvider lookupdescriptorprovider(){

return  new LookupDescriptorProviderImpl() 
    }



@Bean
public DefaultValueExpressionsFactory defaultvalueexpressionsfactory(){

return  new DefaultValueExpressionsFactoryImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



@Bean
public ASILayout asilayout(){

return  new ASILayoutImpl() 
    }



}