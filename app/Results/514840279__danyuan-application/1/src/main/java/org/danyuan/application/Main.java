package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoService;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoServiceImpl;
import org.danyuan.application.Interface.SysSystemInfoService;
import org.danyuan.application.Interface.SysSystemInfoServiceImpl;
import org.danyuan.application.Interface.SysUserLanguageService;
import org.danyuan.application.Interface.SysUserLanguageServiceImpl;
import org.danyuan.application.Interface.SysCrawlerTaskInfoService;
import org.danyuan.application.Interface.SysCrawlerTaskInfoServiceImpl;
import org.danyuan.application.Interface.SysUserProjectService;
import org.danyuan.application.Interface.SysUserProjectServiceImpl;
import org.danyuan.application.Interface.SysModalInfoService;
import org.danyuan.application.Interface.SysModalInfoServiceImpl;
import org.danyuan.application.Interface.SysShareFilePathInfoService;
import org.danyuan.application.Interface.SysShareFilePathInfoServiceImpl;
import org.danyuan.application.Interface.SysUserEducationService;
import org.danyuan.application.Interface.SysUserEducationServiceImpl;
import org.danyuan.application.Interface.SysCrawlerTaskErrInfoService;
import org.danyuan.application.Interface.SysCrawlerTaskErrInfoServiceImpl;
import org.danyuan.application.Interface.SysRolesTabsInfoService;
import org.danyuan.application.Interface.SysRolesTabsInfoServiceImpl;
import org.danyuan.application.Interface.SysUserEvaluateService;
import org.danyuan.application.Interface.SysUserEvaluateServiceImpl;
import org.danyuan.application.Interface.SysUserSkillService;
import org.danyuan.application.Interface.SysUserSkillServiceImpl;
import org.danyuan.application.Interface.SysCrawlerTaskSelfCodeInfoService;
import org.danyuan.application.Interface.SysCrawlerTaskSelfCodeInfoServiceImpl;
import org.danyuan.application.Interface.SysUserWorkExpreienceService;
import org.danyuan.application.Interface.SysUserWorkExpreienceServiceImpl;
import org.danyuan.application.Interface.SysUserCredentialsService;
import org.danyuan.application.Interface.SysUserCredentialsServiceImpl;
import org.danyuan.application.Interface.SysFileUploadInfoService;
import org.danyuan.application.Interface.SysFileUploadInfoServiceImpl;
import org.danyuan.application.Interface.SysDicPorvinceCityService;
import org.danyuan.application.Interface.SysDicPorvinceCityServiceImpl;
import org.danyuan.application.Interface.SysOrganizationInfoDao;
import org.danyuan.application.Interface.SysOrganizationInfoDaoImpl;
import org.danyuan.application.Interface.SysRolesJurisdictionInfoDao;
import org.danyuan.application.Interface.SysRolesJurisdictionInfoDaoImpl;
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
public SysDbmsTabsMergeInfoService sysdbmstabsmergeinfoservice(){

return  new SysDbmsTabsMergeInfoServiceImpl(); 
    }



@Bean
public SysSystemInfoService syssysteminfoservice(){

return  new SysSystemInfoServiceImpl(); 
    }



@Bean
public SysUserLanguageService sysuserlanguageservice(){

return  new SysUserLanguageServiceImpl(); 
    }



@Bean
public SysCrawlerTaskInfoService syscrawlertaskinfoservice(){

return  new SysCrawlerTaskInfoServiceImpl(); 
    }



@Bean
public SysUserProjectService sysuserprojectservice(){

return  new SysUserProjectServiceImpl(); 
    }



@Bean
public SysModalInfoService sysmodalinfoservice(){

return  new SysModalInfoServiceImpl(); 
    }



@Bean
public SysShareFilePathInfoService syssharefilepathinfoservice(){

return  new SysShareFilePathInfoServiceImpl(); 
    }



@Bean
public SysUserEducationService sysusereducationservice(){

return  new SysUserEducationServiceImpl(); 
    }



@Bean
public SysCrawlerTaskErrInfoService syscrawlertaskerrinfoservice(){

return  new SysCrawlerTaskErrInfoServiceImpl(); 
    }



@Bean
public SysRolesTabsInfoService sysrolestabsinfoservice(){

return  new SysRolesTabsInfoServiceImpl(); 
    }



@Bean
public SysUserEvaluateService sysuserevaluateservice(){

return  new SysUserEvaluateServiceImpl(); 
    }



@Bean
public SysUserSkillService sysuserskillservice(){

return  new SysUserSkillServiceImpl(); 
    }



@Bean
public SysCrawlerTaskSelfCodeInfoService syscrawlertaskselfcodeinfoservice(){

return  new SysCrawlerTaskSelfCodeInfoServiceImpl(); 
    }



@Bean
public SysUserWorkExpreienceService sysuserworkexpreienceservice(){

return  new SysUserWorkExpreienceServiceImpl(); 
    }



@Bean
public SysUserCredentialsService sysusercredentialsservice(){

return  new SysUserCredentialsServiceImpl(); 
    }



@Bean
public SysFileUploadInfoService sysfileuploadinfoservice(){

return  new SysFileUploadInfoServiceImpl(); 
    }



@Bean
public SysDicPorvinceCityService sysdicporvincecityservice(){

return  new SysDicPorvinceCityServiceImpl(); 
    }



@Bean
public SysOrganizationInfoDao sysorganizationinfodao(){

return  new SysOrganizationInfoDaoImpl(); 
    }



@Bean
public SysRolesJurisdictionInfoDao sysrolesjurisdictioninfodao(){

return  new SysRolesJurisdictionInfoDaoImpl(); 
    }



}