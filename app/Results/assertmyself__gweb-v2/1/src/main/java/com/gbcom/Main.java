package com.gbcom;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.Interface.SysCodeManagerImpl;
import com.gbcom.Interface.SysLogService;
import com.gbcom.Interface.SysLogServiceImpl;
import com.gbcom.Interface.SysUserManager;
import com.gbcom.Interface.SysUserManagerImpl;
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
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysCodeManager syscodemanager(){

return  new SysCodeManagerImpl(); 
    }



@Bean
public SysLogService syslogservice(){

return  new SysLogServiceImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



@Bean
public SysUserManager sysusermanager(){

return  new SysUserManagerImpl(); 
    }



}