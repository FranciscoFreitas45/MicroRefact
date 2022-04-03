package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.MobileUserRepository;
import org.live.Interface.MobileUserRepositoryImpl;
import org.live.Interface.ReportRepository;
import org.live.Interface.ReportRepositoryImpl;
import org.live.Interface.LiveCategoryService;
import org.live.Interface.LiveCategoryServiceImpl;
import org.live.Interface.AnchorService;
import org.live.Interface.AnchorServiceImpl;
import org.live.Interface.UploadFilePathConfig;
import org.live.Interface.UploadFilePathConfigImpl;
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
public MobileUserRepository mobileuserrepository(){

return  new MobileUserRepositoryImpl(); 
    }



@Bean
public ReportRepository reportrepository(){

return  new ReportRepositoryImpl(); 
    }



@Bean
public LiveCategoryService livecategoryservice(){

return  new LiveCategoryServiceImpl(); 
    }



@Bean
public AnchorService anchorservice(){

return  new AnchorServiceImpl(); 
    }



@Bean
public UploadFilePathConfig uploadfilepathconfig(){

return  new UploadFilePathConfigImpl(); 
    }



}