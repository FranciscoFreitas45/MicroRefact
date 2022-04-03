package com.fosun.fc.projects.creepers;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListJudgementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListJudgementServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakService;
import com.fosun.fc.projects.creepers.Interface.ICreepersAccountBakServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersAssetHandleService;
import com.fosun.fc.projects.creepers.Interface.ICreepersAssetHandleServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersBasicService;
import com.fosun.fc.projects.creepers.Interface.ICreepersBasicServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCcDetailService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCcDetailServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersCompensatoryService;
import com.fosun.fc.projects.creepers.Interface.ICreepersCompensatoryServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersGeneralService;
import com.fosun.fc.projects.creepers.Interface.ICreepersGeneralServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersGuaranteeService;
import com.fosun.fc.projects.creepers.Interface.ICreepersGuaranteeServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersHlDetailService;
import com.fosun.fc.projects.creepers.Interface.ICreepersHlDetailServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersOlDetailService;
import com.fosun.fc.projects.creepers.Interface.ICreepersOlDetailServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicCivilService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicCivilServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicEnforcementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicEnforcementServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicIspService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicIspServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicSanctionService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicSanctionServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicTaxService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPublicTaxServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersQueryLogService;
import com.fosun.fc.projects.creepers.Interface.ICreepersQueryLogServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundService;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceService;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersErrorListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersErrorListServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentService;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentServiceImpl;
import com.fosun.fc.projects.creepers.Interface.ICreepersListPatentService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListPatentServiceImpl;
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
public ICreepersJudgementService icreepersjudgementservice(){

return  new ICreepersJudgementServiceImpl(); 
    }



@Bean
public ICreepersListJudgementService icreeperslistjudgementservice(){

return  new ICreepersListJudgementServiceImpl(); 
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
public ICreepersCcDetailService icreepersccdetailservice(){

return  new ICreepersCcDetailServiceImpl(); 
    }



@Bean
public ICreepersCompensatoryService icreeperscompensatoryservice(){

return  new ICreepersCompensatoryServiceImpl(); 
    }



@Bean
public ICreepersGeneralService icreepersgeneralservice(){

return  new ICreepersGeneralServiceImpl(); 
    }



@Bean
public ICreepersGuaranteeService icreepersguaranteeservice(){

return  new ICreepersGuaranteeServiceImpl(); 
    }



@Bean
public ICreepersHlDetailService icreepershldetailservice(){

return  new ICreepersHlDetailServiceImpl(); 
    }



@Bean
public ICreepersOlDetailService icreepersoldetailservice(){

return  new ICreepersOlDetailServiceImpl(); 
    }



@Bean
public ICreepersPublicCivilService icreeperspubliccivilservice(){

return  new ICreepersPublicCivilServiceImpl(); 
    }



@Bean
public ICreepersPublicEnforcementService icreeperspublicenforcementservice(){

return  new ICreepersPublicEnforcementServiceImpl(); 
    }



@Bean
public ICreepersPublicIspService icreeperspublicispservice(){

return  new ICreepersPublicIspServiceImpl(); 
    }



@Bean
public ICreepersPublicSanctionService icreeperspublicsanctionservice(){

return  new ICreepersPublicSanctionServiceImpl(); 
    }



@Bean
public ICreepersPublicTaxService icreeperspublictaxservice(){

return  new ICreepersPublicTaxServiceImpl(); 
    }



@Bean
public ICreepersQueryLogService icreepersquerylogservice(){

return  new ICreepersQueryLogServiceImpl(); 
    }



@Bean
public ICreepersTouristGuideService icreeperstouristguideservice(){

return  new ICreepersTouristGuideServiceImpl(); 
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
public ICreepersErrorListService icreeperserrorlistservice(){

return  new ICreepersErrorListServiceImpl(); 
    }



@Bean
public ICreepersShixinService icreepersshixinservice(){

return  new ICreepersShixinServiceImpl(); 
    }



@Bean
public ICreepersPatentService icreeperspatentservice(){

return  new ICreepersPatentServiceImpl(); 
    }



@Bean
public ICreepersListPatentService icreeperslistpatentservice(){

return  new ICreepersListPatentServiceImpl(); 
    }



}