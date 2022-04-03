package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.AdministrationSactionProcessor;
import com.fosun.fc.projects.creepers.Interface.AdministrationSactionProcessorImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobService;
import com.fosun.fc.projects.creepers.Interface.ICreepersJobServiceImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListCourtDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListCourtDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListJudgementDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListJudgementDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListPatentDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListPatentDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListTradeMarkDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListTradeMarkDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListShixinDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListShixinDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListInsuranceDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListInsuranceDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListFundDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListFundDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersListTourGuideDao;
import com.fosun.fc.projects.creepers.Interface.CreepersListTourGuideDaoImpl;
import com.fosun.fc.projects.creepers.Interface.CreepersTourBlackListDao;
import com.fosun.fc.projects.creepers.Interface.CreepersTourBlackListDaoImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtAnnounceService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtAnnounceServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTradeMarkService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTradeMarkServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundService;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDishonestyServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDisInfoService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtDisInfoServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinAllServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionDetailService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionDetailServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristBlackListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristBlackListServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersExceptionHandleServiceImpl;
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
public AdministrationSactionProcessor administrationsactionprocessor(){

return  new AdministrationSactionProcessorImpl(); 
    }



@Bean
public ICreepersJobService icreepersjobservice(){

return  new ICreepersJobServiceImpl(); 
    }



@Bean
public CreepersListCourtDao creeperslistcourtdao(){

return  new CreepersListCourtDaoImpl(); 
    }



@Bean
public CreepersListJudgementDao creeperslistjudgementdao(){

return  new CreepersListJudgementDaoImpl(); 
    }



@Bean
public CreepersListPatentDao creeperslistpatentdao(){

return  new CreepersListPatentDaoImpl(); 
    }



@Bean
public CreepersListTradeMarkDao creeperslisttrademarkdao(){

return  new CreepersListTradeMarkDaoImpl(); 
    }



@Bean
public CreepersListShixinDao creeperslistshixindao(){

return  new CreepersListShixinDaoImpl(); 
    }



@Bean
public CreepersListInsuranceDao creeperslistinsurancedao(){

return  new CreepersListInsuranceDaoImpl(); 
    }



@Bean
public CreepersListFundDao creeperslistfunddao(){

return  new CreepersListFundDaoImpl(); 
    }



@Bean
public CreepersListTourGuideDao creeperslisttourguidedao(){

return  new CreepersListTourGuideDaoImpl(); 
    }



@Bean
public CreepersTourBlackListDao creeperstourblacklistdao(){

return  new CreepersTourBlackListDaoImpl(); 
    }



@Bean
public ICreepersJudgementService icreepersjudgementservice(){

return  new ICreepersJudgementServiceImpl(); 
    }



@Bean
public ICreepersCourtAnnounceService icreeperscourtannounceservice(){

return  new ICreepersCourtAnnounceServiceImpl(); 
    }



@Bean
public ICreepersPatentService icreeperspatentservice(){

return  new ICreepersPatentServiceImpl(); 
    }



@Bean
public ICreepersTradeMarkService icreeperstrademarkservice(){

return  new ICreepersTradeMarkServiceImpl(); 
    }



@Bean
public ICreepersShixinService icreepersshixinservice(){

return  new ICreepersShixinServiceImpl(); 
    }



@Bean
public ICreepersFundService icreepersfundservice(){

return  new ICreepersFundServiceImpl(); 
    }



@Bean
public ICreepersInsuranceService icreepersinsuranceservice(){

return  new ICreepersInsuranceServiceImpl(); 
    }



@Bean
public ICreepersCourtCorpBondsService icreeperscourtcorpbondsservice(){

return  new ICreepersCourtCorpBondsServiceImpl(); 
    }



@Bean
public ICreepersCourtDishonestyService icreeperscourtdishonestyservice(){

return  new ICreepersCourtDishonestyServiceImpl(); 
    }



@Bean
public ICreepersCourtDisInfoService icreeperscourtdisinfoservice(){

return  new ICreepersCourtDisInfoServiceImpl(); 
    }



@Bean
public ICreepersShixinAllService icreepersshixinallservice(){

return  new ICreepersShixinAllServiceImpl(); 
    }



@Bean
public ICreepersTaxEvasionService icreeperstaxevasionservice(){

return  new ICreepersTaxEvasionServiceImpl(); 
    }



@Bean
public ICreepersTaxEvasionDetailService icreeperstaxevasiondetailservice(){

return  new ICreepersTaxEvasionDetailServiceImpl(); 
    }



@Bean
public ICreepersTouristGuideService icreeperstouristguideservice(){

return  new ICreepersTouristGuideServiceImpl(); 
    }



@Bean
public ICreepersTouristBlackListService icreeperstouristblacklistservice(){

return  new ICreepersTouristBlackListServiceImpl(); 
    }



@Bean
public ICreepersMedicalService icreepersmedicalservice(){

return  new ICreepersMedicalServiceImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



@Bean
public ICreepersExceptionHandleService icreepersexceptionhandleservice(){

return  new ICreepersExceptionHandleServiceImpl(); 
    }



}