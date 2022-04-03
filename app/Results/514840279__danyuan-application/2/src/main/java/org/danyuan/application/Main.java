package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysShareFileInfoDao;
import org.danyuan.application.Interface.SysShareFileInfoDaoImpl;
import org.danyuan.application.Interface.SysSystemDao;
import org.danyuan.application.Interface.SysSystemDaoImpl;
import org.danyuan.application.Interface.MultiDatasourceConfig;
import org.danyuan.application.Interface.MultiDatasourceConfigImpl;
import org.danyuan.application.Interface.SysDepartmentInfoDao;
import org.danyuan.application.Interface.SysDepartmentInfoDaoImpl;
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
public SysShareFileInfoDao syssharefileinfodao(){

return  new SysShareFileInfoDaoImpl(); 
    }



@Bean
public SysSystemDao syssystemdao(){

return  new SysSystemDaoImpl(); 
    }



@Bean
public MultiDatasourceConfig multidatasourceconfig(){

return  new MultiDatasourceConfigImpl(); 
    }



@Bean
public SysDepartmentInfoDao sysdepartmentinfodao(){

return  new SysDepartmentInfoDaoImpl(); 
    }



}