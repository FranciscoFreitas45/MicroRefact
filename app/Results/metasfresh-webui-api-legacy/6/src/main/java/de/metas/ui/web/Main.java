package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ViewLayout;
import Interface.ViewLayoutImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentQueryOrderByList;
import Interface.DocumentQueryOrderByListImpl;
import Interface.HUEditorRowAttributesProvider;
import Interface.HUEditorRowAttributesProviderImpl;
import Interface.HUEditorRowIsProcessedPredicate;
import Interface.HUEditorRowIsProcessedPredicateImpl;
import Interface.DocumentReferencesService;
import Interface.DocumentReferencesServiceImpl;
import Interface.CompositeDefaultViewProfileIdProvider;
import Interface.CompositeDefaultViewProfileIdProviderImpl;
import Interface.DocumentDescriptorFactory;
import Interface.DocumentDescriptorFactoryImpl;
import Interface.GeoLocationDocumentService;
import Interface.GeoLocationDocumentServiceImpl;
import Interface.DocumentFieldValueLoader;
import Interface.DocumentFieldValueLoaderImpl;
import Interface.DocumentFilterDescriptorsProvider;
import Interface.DocumentFilterDescriptorsProviderImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.FacetFilterViewCacheMap;
import Interface.FacetFilterViewCacheMapImpl;
import Interface.IViewInvalidationAdvisor;
import Interface.IViewInvalidationAdvisorImpl;
import Interface.DocumentsRepository;
import Interface.DocumentsRepositoryImpl;
import Interface.Document;
import Interface.DocumentImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.LookupDescriptor;
import Interface.LookupDescriptorImpl;
import Interface.SqlForFetchingLookupById;
import Interface.SqlForFetchingLookupByIdImpl;
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
public ViewLayout viewlayout(){

return  new ViewLayoutImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public ViewId viewid(){

return  new ViewIdImpl() 
    }



@Bean
public DocumentQueryOrderByList documentqueryorderbylist(){

return  new DocumentQueryOrderByListImpl() 
    }



@Bean
public HUEditorRowAttributesProvider hueditorrowattributesprovider(){

return  new HUEditorRowAttributesProviderImpl() 
    }



@Bean
public HUEditorRowIsProcessedPredicate hueditorrowisprocessedpredicate(){

return  new HUEditorRowIsProcessedPredicateImpl() 
    }



@Bean
public DocumentReferencesService documentreferencesservice(){

return  new DocumentReferencesServiceImpl() 
    }



@Bean
public CompositeDefaultViewProfileIdProvider compositedefaultviewprofileidprovider(){

return  new CompositeDefaultViewProfileIdProviderImpl() 
    }



@Bean
public DocumentDescriptorFactory documentdescriptorfactory(){

return  new DocumentDescriptorFactoryImpl() 
    }



@Bean
public GeoLocationDocumentService geolocationdocumentservice(){

return  new GeoLocationDocumentServiceImpl() 
    }



@Bean
public DocumentFieldValueLoader documentfieldvalueloader(){

return  new DocumentFieldValueLoaderImpl() 
    }



@Bean
public DocumentFilterDescriptorsProvider documentfilterdescriptorsprovider(){

return  new DocumentFilterDescriptorsProviderImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public FacetFilterViewCacheMap facetfilterviewcachemap(){

return  new FacetFilterViewCacheMapImpl() 
    }



@Bean
public IViewInvalidationAdvisor iviewinvalidationadvisor(){

return  new IViewInvalidationAdvisorImpl() 
    }



@Bean
public DocumentsRepository documentsrepository(){

return  new DocumentsRepositoryImpl() 
    }



@Bean
public Document document(){

return  new DocumentImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



@Bean
public LookupDescriptor lookupdescriptor(){

return  new LookupDescriptorImpl() 
    }



@Bean
public SqlForFetchingLookupById sqlforfetchinglookupbyid(){

return  new SqlForFetchingLookupByIdImpl() 
    }



}