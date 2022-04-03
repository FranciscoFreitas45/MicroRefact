package com.gbcom;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gbcom.Interface.SysCodeDetail;
import com.gbcom.Interface.SysCodeDetailImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysUserService;
import com.gbcom.Interface.SysUserServiceImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysLogManager;
import com.gbcom.Interface.SysLogManagerImpl;
import com.gbcom.Interface.SimpleQueryManager;
import com.gbcom.Interface.SimpleQueryManagerImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
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
public SysCodeDetail syscodedetail(){

return  new SysCodeDetailImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysUserService sysuserservice(){

return  new SysUserServiceImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysLogManager syslogmanager(){

return  new SysLogManagerImpl(); 
    }



@Bean
public SimpleQueryManager simplequerymanager(){

return  new SimpleQueryManagerImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



}