package com.gbcom;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysUser;
import com.gbcom.Interface.SysUserImpl;
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
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysUser sysuser(){

return  new SysUserImpl(); 
    }



}