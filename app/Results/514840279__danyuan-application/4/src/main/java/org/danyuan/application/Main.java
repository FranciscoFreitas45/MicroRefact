package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoDao;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoDaoImpl;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDaoImpl;
import org.danyuan.application.Interface.SysDbmsAdviMessInfoDao;
import org.danyuan.application.Interface.SysDbmsAdviMessInfoDaoImpl;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoService;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoServiceImpl;
import org.danyuan.application.Interface.SysDbmsUserIndexInfoService;
import org.danyuan.application.Interface.SysDbmsUserIndexInfoServiceImpl;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDaoImpl;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDao;
import org.danyuan.application.Interface.SysDbmsTabsJdbcInfoDaoImpl;
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
public SysDbmsTabsMergeInfoDao sysdbmstabsmergeinfodao(){

return  new SysDbmsTabsMergeInfoDaoImpl(); 
    }



@Bean
public SysDbmsTabsJdbcInfoDao sysdbmstabsjdbcinfodao(){

return  new SysDbmsTabsJdbcInfoDaoImpl(); 
    }



@Bean
public SysDbmsAdviMessInfoDao sysdbmsadvimessinfodao(){

return  new SysDbmsAdviMessInfoDaoImpl(); 
    }



@Bean
public SysDbmsTabsTypeInfoService sysdbmstabstypeinfoservice(){

return  new SysDbmsTabsTypeInfoServiceImpl(); 
    }



@Bean
public SysDbmsUserIndexInfoService sysdbmsuserindexinfoservice(){

return  new SysDbmsUserIndexInfoServiceImpl(); 
    }



@Bean
public SysDbmsTabsJdbcInfoDao sysdbmstabsjdbcinfodao(){

return  new SysDbmsTabsJdbcInfoDaoImpl(); 
    }



@Bean
public SysDbmsTabsJdbcInfoDao sysdbmstabsjdbcinfodao(){

return  new SysDbmsTabsJdbcInfoDaoImpl(); 
    }



}