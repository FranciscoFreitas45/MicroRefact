package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysRolesInfoDao;
import org.danyuan.application.Interface.SysRolesInfoDaoImpl;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
import org.danyuan.application.Interface.SysUserRolesInfoDaoImpl;
import org.danyuan.application.Interface.SysRolesInfoDao;
import org.danyuan.application.Interface.SysRolesInfoDaoImpl;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
import org.danyuan.application.Interface.SysUserRolesInfoDaoImpl;
import org.danyuan.application.Interface.SysUserRolesInfoDao;
import org.danyuan.application.Interface.SysUserRolesInfoDaoImpl;
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
public SysRolesInfoDao sysrolesinfodao(){

return  new SysRolesInfoDaoImpl(); 
    }



@Bean
public SysUserRolesInfoDao sysuserrolesinfodao(){

return  new SysUserRolesInfoDaoImpl(); 
    }



@Bean
public SysRolesInfoDao sysrolesinfodao(){

return  new SysRolesInfoDaoImpl(); 
    }



@Bean
public SysUserRolesInfoDao sysuserrolesinfodao(){

return  new SysUserRolesInfoDaoImpl(); 
    }



@Bean
public SysUserRolesInfoDao sysuserrolesinfodao(){

return  new SysUserRolesInfoDaoImpl(); 
    }



}