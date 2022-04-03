package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.PurchaseViewLayoutFactory;
import Interface.PurchaseViewLayoutFactoryImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.PurchaseRowLookups;
import Interface.PurchaseRowLookupsImpl;
import Interface.LookupValue;
import Interface.LookupValueImpl;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.IView;
import Interface.IViewImpl;
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
public PurchaseViewLayoutFactory purchaseviewlayoutfactory(){

return  new PurchaseViewLayoutFactoryImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
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
public PurchaseRowLookups purchaserowlookups(){

return  new PurchaseRowLookupsImpl() 
    }



@Bean
public LookupValue lookupvalue(){

return  new LookupValueImpl() 
    }



@Bean
public IViewsRepository iviewsrepository(){

return  new IViewsRepositoryImpl() 
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
public ViewRowIdsSelection viewrowidsselection(){

return  new ViewRowIdsSelectionImpl() 
    }



@Bean
public JSONOptions jsonoptions(){

return  new JSONOptionsImpl() 
    }



}