package de.metas.ui.web;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.IViewsRepository;
import Interface.IViewsRepositoryImpl;
import Interface.ViewProfileId;
import Interface.ViewProfileIdImpl;
import Interface.ViewRowIdsSelection;
import Interface.ViewRowIdsSelectionImpl;
import Interface.JSONOptions;
import Interface.JSONOptionsImpl;
import Interface.HUEditorViewRepository;
import Interface.HUEditorViewRepositoryImpl;
import Interface.ViewId;
import Interface.ViewIdImpl;
import Interface.DocumentId;
import Interface.DocumentIdImpl;
import Interface.DocumentPath;
import Interface.DocumentPathImpl;
import Interface.JSONLookupValue;
import Interface.JSONLookupValueImpl;
import Interface.ASIDocument;
import Interface.ASIDocumentImpl;
import Interface.ViewRowAttributesLayout;
import Interface.ViewRowAttributesLayoutImpl;
import Interface.ASIRepository;
import Interface.ASIRepositoryImpl;
import Interface.PackingInfoProcessParams;
import Interface.PackingInfoProcessParamsImpl;
import Interface.WindowId;
import Interface.WindowIdImpl;
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



@Bean
public HUEditorViewRepository hueditorviewrepository(){

return  new HUEditorViewRepositoryImpl() 
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
public DocumentPath documentpath(){

return  new DocumentPathImpl() 
    }



@Bean
public JSONLookupValue jsonlookupvalue(){

return  new JSONLookupValueImpl() 
    }



@Bean
public ASIDocument asidocument(){

return  new ASIDocumentImpl() 
    }



@Bean
public ViewRowAttributesLayout viewrowattributeslayout(){

return  new ViewRowAttributesLayoutImpl() 
    }



@Bean
public ASIRepository asirepository(){

return  new ASIRepositoryImpl() 
    }



@Bean
public PackingInfoProcessParams packinginfoprocessparams(){

return  new PackingInfoProcessParamsImpl() 
    }



@Bean
public WindowId windowid(){

return  new WindowIdImpl() 
    }



}