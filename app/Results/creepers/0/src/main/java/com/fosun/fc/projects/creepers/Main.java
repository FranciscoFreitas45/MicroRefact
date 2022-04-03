package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.HttpClientGenerator;
import com.fosun.fc.projects.creepers.Interface.HttpClientGeneratorImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobService;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersSactionService;
import com.fosun.fc.projects.creepers.Interface.ICreepersSactionServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalServiceImpl;
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
public HttpClientGenerator httpclientgenerator(){

return  new HttpClientGeneratorImpl(); 
    }



@Bean
public ICreepersJobService icreepersjobservice(){

return  new ICreepersJobServiceImpl(); 
    }



@Bean
public ICreepersTaxEvasionService icreeperstaxevasionservice(){

return  new ICreepersTaxEvasionServiceImpl(); 
    }



@Bean
public ICreepersShixinAllService icreepersshixinallservice(){

return  new ICreepersShixinAllServiceImpl(); 
    }



@Bean
public ICreepersShixinService icreepersshixinservice(){

return  new ICreepersShixinServiceImpl(); 
    }



@Bean
public ICreepersCourtCorpBondsService icreeperscourtcorpbondsservice(){

return  new ICreepersCourtCorpBondsServiceImpl(); 
    }



@Bean
public ICreepersSactionService icreeperssactionservice(){

return  new ICreepersSactionServiceImpl(); 
    }



@Bean
public ICreepersCourtDishonestyService icreeperscourtdishonestyservice(){

return  new ICreepersCourtDishonestyServiceImpl(); 
    }



@Bean
public ICreepersMedicalService icreepersmedicalservice(){

return  new ICreepersMedicalServiceImpl(); 
    }



@Bean
public ICreepersListService icreeperslistservice(){

return  new ICreepersListServiceImpl(); 
    }



}