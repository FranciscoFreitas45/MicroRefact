package com.gbcom;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gbcom.Interface.SysCodeDetail;
import com.gbcom.Interface.SysCodeDetailImpl;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.Interface.SysCodeManagerImpl;
import com.gbcom.Interface.SysPerson;
import com.gbcom.Interface.SysPersonImpl;
import com.gbcom.Interface.SysArea;
import com.gbcom.Interface.SysAreaImpl;
import com.gbcom.Interface.SysCodeDetail;
import com.gbcom.Interface.SysCodeDetailImpl;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.Interface.SysCodeManagerImpl;
import com.gbcom.Interface.SimpleQueryManager;
import com.gbcom.Interface.SimpleQueryManagerImpl;
import com.gbcom.Interface.SysLogCustomManager;
import com.gbcom.Interface.SysLogCustomManagerImpl;
import com.gbcom.Interface.SysMenuManager;
import com.gbcom.Interface.SysMenuManagerImpl;
import com.gbcom.Interface.SysCodeManager;
import com.gbcom.Interface.SysCodeManagerImpl;
import com.gbcom.Interface.ConfigManager;
import com.gbcom.Interface.ConfigManagerImpl;
import com.gbcom.Interface.SimpleQueryManager;
import com.gbcom.Interface.SimpleQueryManagerImpl;
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
public SysCodeManager syscodemanager(){

return  new SysCodeManagerImpl(); 
    }



@Bean
public SysPerson sysperson(){

return  new SysPersonImpl(); 
    }



@Bean
public SysArea sysarea(){

return  new SysAreaImpl(); 
    }



@Bean
public SysCodeDetail syscodedetail(){

return  new SysCodeDetailImpl(); 
    }



@Bean
public SysCodeManager syscodemanager(){

return  new SysCodeManagerImpl(); 
    }



@Bean
public SimpleQueryManager simplequerymanager(){

return  new SimpleQueryManagerImpl(); 
    }



@Bean
public SysLogCustomManager syslogcustommanager(){

return  new SysLogCustomManagerImpl(); 
    }



@Bean
public SysMenuManager sysmenumanager(){

return  new SysMenuManagerImpl(); 
    }



@Bean
public SysCodeManager syscodemanager(){

return  new SysCodeManagerImpl(); 
    }



@Bean
public ConfigManager configmanager(){

return  new ConfigManagerImpl(); 
    }



@Bean
public SimpleQueryManager simplequerymanager(){

return  new SimpleQueryManagerImpl(); 
    }



}