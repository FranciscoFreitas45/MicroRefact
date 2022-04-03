package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.PricingConditionsRowLookups;
import Interface.PricingConditionsRowLookupsImpl;
import Interface.PricingConditionsViewFilters;
import Interface.PricingConditionsViewFiltersImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.LookupValue;
import Interface.LookupValueImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.DocumentQueryOrderByList;
import Interface.DocumentQueryOrderByListImpl;
import Interface.ViewHeaderProperties;
import Interface.ViewHeaderPropertiesImpl;
import Interface.LookupDataSource;
import Interface.LookupDataSourceImpl;
import Interface.LookupValueByIdSupplier;
import Interface.LookupValueByIdSupplierImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentFilterDescriptorsProvider;
import Interface.DocumentFilterDescriptorsProviderImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.ViewRowIdsSelection;
import Interface.ViewRowIdsSelectionImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
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
public PricingConditionsRowLookups pricingconditionsrowlookups(){

return  new PricingConditionsRowLookupsImpl() 
    }



@Bean
public PricingConditionsViewFilters pricingconditionsviewfilters(){

return  new PricingConditionsViewFiltersImpl() 
    }



@Bean
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public DocumentId documentid(){

return  new DocumentIdImpl() 
    }



@Bean
public LookupValue lookupvalue(){

return  new LookupValueImpl() 
    }



@Bean
public DocumentCollection documentcollection(){

return  new DocumentCollectionImpl() 
    }



@Bean
public DocumentQueryOrderByList documentqueryorderbylist(){

return  new DocumentQueryOrderByListImpl() 
    }



@Bean
public ViewHeaderProperties viewheaderproperties(){

return  new ViewHeaderPropertiesImpl() 
    }



@Bean
public LookupDataSource lookupdatasource(){

return  new LookupDataSourceImpl() 
    }



@Bean
public LookupValueByIdSupplier lookupvaluebyidsupplier(){

return  new LookupValueByIdSupplierImpl() 
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
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
    }



@Bean
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public ViewRowIdsSelection viewrowidsselection(){

return  new ViewRowIdsSelectionImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



}