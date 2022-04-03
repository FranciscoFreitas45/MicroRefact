package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysDbmsChartDimensionDataDao;
import org.danyuan.application.Interface.SysDbmsChartDimensionDataDaoImpl;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoService;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoServiceImpl;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoService;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoServiceImpl;
import org.danyuan.application.Interface.SysDbmsTabsInfoService;
import org.danyuan.application.Interface.SysDbmsTabsInfoServiceImpl;
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
public SysDbmsChartDimensionDataDao sysdbmschartdimensiondatadao(){

return  new SysDbmsChartDimensionDataDaoImpl(); 
    }



@Bean
public SysDbmsTabsColsInfoService sysdbmstabscolsinfoservice(){

return  new SysDbmsTabsColsInfoServiceImpl(); 
    }



@Bean
public SysDbmsTabsColsInfoService sysdbmstabscolsinfoservice(){

return  new SysDbmsTabsColsInfoServiceImpl(); 
    }



@Bean
public SysDbmsTabsInfoService sysdbmstabsinfoservice(){

return  new SysDbmsTabsInfoServiceImpl(); 
    }



}