package com.cym;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cym.Interface.MessageUtils;
import com.cym.Interface.MessageUtilsImpl;
import com.cym.Interface.VersionConfig;
import com.cym.Interface.VersionConfigImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.GroupService;
import com.cym.Interface.GroupServiceImpl;
import com.cym.Interface.RemoteController;
import com.cym.Interface.RemoteControllerImpl;
import com.cym.Interface.MessageUtils;
import com.cym.Interface.MessageUtilsImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.FrontInterceptor;
import com.cym.Interface.FrontInterceptorImpl;
import com.cym.Interface.MessageUtils;
import com.cym.Interface.MessageUtilsImpl;
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
public MessageUtils messageutils(){

return  new MessageUtilsImpl(); 
    }



@Bean
public VersionConfig versionconfig(){

return  new VersionConfigImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public GroupService groupservice(){

return  new GroupServiceImpl(); 
    }



@Bean
public RemoteController remotecontroller(){

return  new RemoteControllerImpl(); 
    }



@Bean
public MessageUtils messageutils(){

return  new MessageUtilsImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public FrontInterceptor frontinterceptor(){

return  new FrontInterceptorImpl(); 
    }



@Bean
public MessageUtils messageutils(){

return  new MessageUtilsImpl(); 
    }



}