package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.DetailId;
import Interface.DetailIdImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.DocumentQueryOrderByList;
import Interface.DocumentQueryOrderByListImpl;
import Interface.ETag;
import Interface.ETagImpl;
import Interface.DocumentEntityDescriptor;
import Interface.DocumentEntityDescriptorImpl;
import Interface.QuickInputDescriptorFactoryService;
import Interface.QuickInputDescriptorFactoryServiceImpl;
import Interface.GridTabVOBasedDocumentEntityDescriptorFactory;
import Interface.GridTabVOBasedDocumentEntityDescriptorFactoryImpl;
import Interface.DocumentFilterDescriptorsProvidersService;
import Interface.DocumentFilterDescriptorsProvidersServiceImpl;
import Interface.DataEntrySubTabBindingDescriptorBuilder;
import Interface.DataEntrySubTabBindingDescriptorBuilderImpl;
import Interface.DataEntryWebuiTools;
import Interface.DataEntryWebuiToolsImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.ButtonFieldActionDescriptor;
import Interface.ButtonFieldActionDescriptorImpl;
import Interface.ProcessId;
import Interface.ProcessIdImpl;
import Interface.Builder;
import Interface.BuilderImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.NewRecordDescriptorsProvider;
import Interface.NewRecordDescriptorsProviderImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.HUBarcodeSqlDocumentFilterConverter;
import Interface.HUBarcodeSqlDocumentFilterConverterImpl;
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
public DetailId detailid(){

return  new DetailIdImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public DocumentQueryOrderByList documentqueryorderbylist(){

return  new DocumentQueryOrderByListImpl() 
    }



@Bean
public ETag etag(){

return  new ETagImpl() 
    }



@Bean
public DocumentEntityDescriptor documententitydescriptor(){

return  new DocumentEntityDescriptorImpl() 
    }



@Bean
public QuickInputDescriptorFactoryService quickinputdescriptorfactoryservice(){

return  new QuickInputDescriptorFactoryServiceImpl() 
    }



@Bean
public GridTabVOBasedDocumentEntityDescriptorFactory gridtabvobaseddocumententitydescriptorfactory(){

return  new GridTabVOBasedDocumentEntityDescriptorFactoryImpl() 
    }



@Bean
public DocumentFilterDescriptorsProvidersService documentfilterdescriptorsprovidersservice(){

return  new DocumentFilterDescriptorsProvidersServiceImpl() 
    }



@Bean
public DataEntrySubTabBindingDescriptorBuilder dataentrysubtabbindingdescriptorbuilder(){

return  new DataEntrySubTabBindingDescriptorBuilderImpl() 
    }



@Bean
public DataEntryWebuiTools dataentrywebuitools(){

return  new DataEntryWebuiToolsImpl() 
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
public ButtonFieldActionDescriptor buttonfieldactiondescriptor(){

return  new ButtonFieldActionDescriptorImpl() 
    }



@Bean
public ProcessId processid(){

return  new ProcessIdImpl() 
    }



@Bean
public Builder builder(){

return  new BuilderImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



@Bean
public NewRecordDescriptorsProvider newrecorddescriptorsprovider(){

return  new NewRecordDescriptorsProviderImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public HUBarcodeSqlDocumentFilterConverter hubarcodesqldocumentfilterconverter(){

return  new HUBarcodeSqlDocumentFilterConverterImpl() 
    }



}