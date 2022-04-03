package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ProcessLayout;
import Interface.ProcessLayoutImpl;
import Interface.DocumentEntityDescriptor;
import Interface.DocumentEntityDescriptorImpl;
import Interface.ETag;
import Interface.ETagImpl;
import Interface.IView;
import Interface.IViewImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.WindowRestController;
import Interface.WindowRestControllerImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.Document;
import Interface.DocumentImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.ResultAction;
import Interface.ResultActionImpl;
import Interface.CreateViewRequest;
import Interface.CreateViewRequestImpl;
import Interface.DocumentIdsSelection;
import Interface.DocumentIdsSelectionImpl;
import Interface.ReasonSupplier;
import Interface.ReasonSupplierImpl;
import Interface.ProcessParametersDataBindingDescriptorBuilder;
import Interface.ProcessParametersDataBindingDescriptorBuilderImpl;
import Interface.DocumentEntityDataBindingDescriptor;
import Interface.DocumentEntityDataBindingDescriptorImpl;
import Interface.IDocumentFieldView;
import Interface.IDocumentFieldViewImpl;
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
public ProcessLayout processlayout(){

return  new ProcessLayoutImpl() 
    }



@Bean
public DocumentEntityDescriptor documententitydescriptor(){

return  new DocumentEntityDescriptorImpl() 
    }



@Bean
public ETag etag(){

return  new ETagImpl() 
    }



@Bean
public IView iview(){

return  new IViewImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public UserSession usersession(){

return  new UserSessionImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
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
public WindowRestController windowrestcontroller(){

return  new WindowRestControllerImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
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
public ViewId viewid(){

return  new ViewIdImpl() 
    }



@Bean
public ResultAction resultaction(){

return  new ResultActionImpl() 
    }



@Bean
public CreateViewRequest createviewrequest(){

return  new CreateViewRequestImpl() 
    }



@Bean
public DocumentIdsSelection documentidsselection(){

return  new DocumentIdsSelectionImpl() 
    }



@Bean
public ReasonSupplier reasonsupplier(){

return  new ReasonSupplierImpl() 
    }



@Bean
public ProcessParametersDataBindingDescriptorBuilder processparametersdatabindingdescriptorbuilder(){

return  new ProcessParametersDataBindingDescriptorBuilderImpl() 
    }



@Bean
public DocumentEntityDataBindingDescriptor documententitydatabindingdescriptor(){

return  new DocumentEntityDataBindingDescriptorImpl() 
    }



@Bean
public IDocumentFieldView idocumentfieldview(){

return  new IDocumentFieldViewImpl() 
    }



}