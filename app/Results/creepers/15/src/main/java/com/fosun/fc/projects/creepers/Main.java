package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakServiceImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersAssetHandleDao;
import com.fosun.fc.projects.creepers.Interface.CreepersAssetHandleDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersBasicDao;
import com.fosun.fc.projects.creepers.Interface.CreepersBasicDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersGeneralDao;
import com.fosun.fc.projects.creepers.Interface.CreepersGeneralDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreditReferenceCenterProcessor;
import com.fosun.fc.projects.creepers.Interface.CreditReferenceCenterProcessorImpl;
import com.fosun.fc.projects.creepers.Interface.CreditReferenceUploadFilePipline;
import com.fosun.fc.projects.creepers.Interface.CreditReferenceUploadFilePiplineImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersAssetHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersAssetHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersBasicService;
import com.fosun.fc.projects.creepers.Interface.ICreepersBasicServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersGeneralService;
import com.fosun.fc.projects.creepers.Interface.ICreepersGeneralServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListServiceImpl;
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
public ICreepersAccountBakService icreepersaccountbakservice(){

return  new ICreepersAccountBakServiceImpl(); 
    }



@Bean
public CreepersAssetHandleDao creepersassethandledao(){

return  new CreepersAssetHandleDaoImpl(); 
    }



@Bean
public CreepersBasicDao creepersbasicdao(){

return  new CreepersBasicDaoImpl(); 
    }



@Bean
public CreepersGeneralDao creepersgeneraldao(){

return  new CreepersGeneralDaoImpl(); 
    }



@Bean
public CreditReferenceCenterProcessor creditreferencecenterprocessor(){

return  new CreditReferenceCenterProcessorImpl(); 
    }



@Bean
public CreditReferenceUploadFilePipline creditreferenceuploadfilepipline(){

return  new CreditReferenceUploadFilePiplineImpl(); 
    }



@Bean
public ICreepersAccountBakService icreepersaccountbakservice(){

return  new ICreepersAccountBakServiceImpl(); 
    }



@Bean
public ICreepersAssetHandleService icreepersassethandleservice(){

return  new ICreepersAssetHandleServiceImpl(); 
    }



@Bean
public ICreepersBasicService icreepersbasicservice(){

return  new ICreepersBasicServiceImpl(); 
    }



@Bean
public ICreepersGeneralService icreepersgeneralservice(){

return  new ICreepersGeneralServiceImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



@Bean
public ICreepersListService icreeperslistservice(){

return  new ICreepersListServiceImpl(); 
    }



}