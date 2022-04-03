package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysDbmsChartDimensionGroupDao;
import org.danyuan.application.Interface.SysDbmsChartDimensionGroupDaoImpl;
import org.danyuan.application.Interface.SysUserModalService;
import org.danyuan.application.Interface.SysUserModalServiceImpl;
import org.danyuan.application.Interface.SysUserBaseService;
import org.danyuan.application.Interface.SysUserBaseServiceImpl;
import org.danyuan.application.Interface.SysUserEducationService;
import org.danyuan.application.Interface.SysUserEducationServiceImpl;
import org.danyuan.application.Interface.SysUserSkillService;
import org.danyuan.application.Interface.SysUserSkillServiceImpl;
import org.danyuan.application.Interface.SysUserWorkExpreienceService;
import org.danyuan.application.Interface.SysUserWorkExpreienceServiceImpl;
import org.danyuan.application.Interface.SysUserProjectService;
import org.danyuan.application.Interface.SysUserProjectServiceImpl;
import org.danyuan.application.Interface.SysUserEvaluateService;
import org.danyuan.application.Interface.SysUserEvaluateServiceImpl;
import org.danyuan.application.Interface.SysModalInfoService;
import org.danyuan.application.Interface.SysModalInfoServiceImpl;
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
public SysDbmsChartDimensionGroupDao sysdbmschartdimensiongroupdao(){

return  new SysDbmsChartDimensionGroupDaoImpl(); 
    }



@Bean
public SysUserModalService sysusermodalservice(){

return  new SysUserModalServiceImpl(); 
    }



@Bean
public SysUserBaseService sysuserbaseservice(){

return  new SysUserBaseServiceImpl(); 
    }



@Bean
public SysUserEducationService sysusereducationservice(){

return  new SysUserEducationServiceImpl(); 
    }



@Bean
public SysUserSkillService sysuserskillservice(){

return  new SysUserSkillServiceImpl(); 
    }



@Bean
public SysUserWorkExpreienceService sysuserworkexpreienceservice(){

return  new SysUserWorkExpreienceServiceImpl(); 
    }



@Bean
public SysUserProjectService sysuserprojectservice(){

return  new SysUserProjectServiceImpl(); 
    }



@Bean
public SysUserEvaluateService sysuserevaluateservice(){

return  new SysUserEvaluateServiceImpl(); 
    }



@Bean
public SysModalInfoService sysmodalinfoservice(){

return  new SysModalInfoServiceImpl(); 
    }



}