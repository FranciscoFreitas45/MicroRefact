package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
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
public SysRolesJurisdictionInfoDao sysrolesjurisdictioninfodao(){

return  new SysRolesJurisdictionInfoDaoImpl(); 
    }



}