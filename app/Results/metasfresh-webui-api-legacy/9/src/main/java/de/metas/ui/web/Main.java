package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.SqlViewFactory;
import Interface.SqlViewFactoryImpl;
import Interface.MenuTreeRepository;
import Interface.MenuTreeRepositoryImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.JSONIncludedViewId;
import Interface.JSONIncludedViewIdImpl;
import Interface.MaterialCockpitFilters;
import Interface.MaterialCockpitFiltersImpl;
import Interface.ProductsToPickRowsService;
import Interface.ProductsToPickRowsServiceImpl;
import Interface.ASIRepository;
import Interface.ASIRepositoryImpl;
import Interface.DefaultHUEditorViewFactory;
import Interface.DefaultHUEditorViewFactoryImpl;
import Interface.LookupValue;
import Interface.LookupValueImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentFilterDescriptorsProvider;
import Interface.DocumentFilterDescriptorsProviderImpl;
import Interface.DocumentFilterList;
import Interface.DocumentFilterListImpl;
import Interface.DocumentQueryOrderByList;
import Interface.DocumentQueryOrderByListImpl;
import Interface.ProductFilterVO;
import Interface.ProductFilterVOImpl;
import Interface.UserSession;
import Interface.UserSessionImpl;
import Interface.DocumentCollection;
import Interface.DocumentCollectionImpl;
import Interface.ViewLayout;
import Interface.ViewLayoutImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.LookupDataSource;
import Interface.LookupDataSourceImpl;
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
public WindowId windowid(){

return  new WindowIdImpl() 
    }



@Bean
public SqlViewFactory sqlviewfactory(){

return  new SqlViewFactoryImpl() 
    }



@Bean
public MenuTreeRepository menutreerepository(){

return  new MenuTreeRepositoryImpl() 
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
public ViewProfileId viewprofileid(){

return  new ViewProfileIdImpl() 
    }



@Bean
public JSONIncludedViewId jsonincludedviewid(){

return  new JSONIncludedViewIdImpl() 
    }



@Bean
public MaterialCockpitFilters materialcockpitfilters(){

return  new MaterialCockpitFiltersImpl() 
    }



@Bean
public ProductsToPickRowsService productstopickrowsservice(){

return  new ProductsToPickRowsServiceImpl() 
    }



@Bean
public ASIRepository asirepository(){

return  new ASIRepositoryImpl() 
    }



@Bean
public DefaultHUEditorViewFactory defaulthueditorviewfactory(){

return  new DefaultHUEditorViewFactoryImpl() 
    }



@Bean
public LookupValue lookupvalue(){

return  new LookupValueImpl() 
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
public DocumentFilterList documentfilterlist(){

return  new DocumentFilterListImpl() 
    }



@Bean
public DocumentQueryOrderByList documentqueryorderbylist(){

return  new DocumentQueryOrderByListImpl() 
    }



@Bean
public ProductFilterVO productfiltervo(){

return  new ProductFilterVOImpl() 
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
public ViewLayout viewlayout(){

return  new ViewLayoutImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



@Bean
public LookupDataSource lookupdatasource(){

return  new LookupDataSourceImpl() 
    }



@Bean
public ViewRowIdsSelection viewrowidsselection(){

return  new ViewRowIdsSelectionImpl() 
    }



}