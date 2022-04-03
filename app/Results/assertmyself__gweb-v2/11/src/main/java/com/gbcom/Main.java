package com.gbcom;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gbcom.Interface.SysLogManager;
import com.gbcom.Interface.SysLogManagerImpl;
import com.gbcom.Interface.SysLogManager;
import com.gbcom.Interface.SysLogManagerImpl;
import com.gbcom.Interface.SysLogService;
import com.gbcom.Interface.SysLogServiceImpl;
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
public SysLogManager syslogmanager(){

return  new SysLogManagerImpl(); 
    }



@Bean
public SysLogManager syslogmanager(){

return  new SysLogManagerImpl(); 
    }



@Bean
public SysLogService syslogservice(){

return  new SysLogServiceImpl(); 
    }



}