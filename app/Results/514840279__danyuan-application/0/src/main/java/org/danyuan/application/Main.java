package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.VSysComnLogsDao;
import org.danyuan.application.Interface.VSysComnLogsDaoImpl;
import org.danyuan.application.Interface.VSysComnLogsDao;
import org.danyuan.application.Interface.VSysComnLogsDaoImpl;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoDao;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoDaoImpl;
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
public VSysComnLogsDao vsyscomnlogsdao(){

return  new VSysComnLogsDaoImpl(); 
    }



@Bean
public VSysComnLogsDao vsyscomnlogsdao(){

return  new VSysComnLogsDaoImpl(); 
    }



@Bean
public SysDbmsTabsTypeInfoDao sysdbmstabstypeinfodao(){

return  new SysDbmsTabsTypeInfoDaoImpl(); 
    }



}