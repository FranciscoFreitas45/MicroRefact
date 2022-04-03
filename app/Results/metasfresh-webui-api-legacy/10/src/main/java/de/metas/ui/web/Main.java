package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.JSONLookupValue;
import Interface.JSONLookupValueImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.HUBarcodeSqlDocumentFilterConverter;
import Interface.HUBarcodeSqlDocumentFilterConverterImpl;
import Interface.HUReservationDocumentFilterService;
import Interface.HUReservationDocumentFilterServiceImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.ViewActionDescriptorsList;
import Interface.ViewActionDescriptorsListImpl;
import Interface.DocumentFilterDescriptorsProvider;
import Interface.DocumentFilterDescriptorsProviderImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.LookupValue;
import Interface.LookupValueImpl;
import Interface.ViewEvaluationCtx;
import Interface.ViewEvaluationCtxImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.HUIdsFilterData;
import Interface.HUIdsFilterDataImpl;
import Interface.DocumentQueryOrderByList;
import Interface.DocumentQueryOrderByListImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.ViewRowIdsSelection;
import Interface.ViewRowIdsSelectionImpl;
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
public JSONLookupValue jsonlookupvalue(){

return  new JSONLookupValueImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public HUBarcodeSqlDocumentFilterConverter hubarcodesqldocumentfilterconverter(){

return  new HUBarcodeSqlDocumentFilterConverterImpl() 
    }



@Bean
public HUReservationDocumentFilterService hureservationdocumentfilterservice(){

return  new HUReservationDocumentFilterServiceImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public ViewId viewid(){

return  new ViewIdImpl() 
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
public DocumentFilterDescriptorsProvider documentfilterdescriptorsprovider(){

return  new DocumentFilterDescriptorsProviderImpl() 
    }



@Bean
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public LookupValue lookupvalue(){

return  new LookupValueImpl() 
    }



@Bean
public ViewEvaluationCtx viewevaluationctx(){

return  new ViewEvaluationCtxImpl() 
    }



@Bean
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public HUIdsFilterData huidsfilterdata(){

return  new HUIdsFilterDataImpl() 
    }



@Bean
public DocumentQueryOrderByList documentqueryorderbylist(){

return  new DocumentQueryOrderByListImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public ViewRowIdsSelection viewrowidsselection(){

return  new ViewRowIdsSelectionImpl() 
    }



}