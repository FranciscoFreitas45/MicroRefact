package com.cym;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.UpstreamServiceImpl;
import com.cym.Interface.ParamService;
import com.cym.Interface.ParamServiceImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.ParamService;
import com.cym.Interface.ParamServiceImpl;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.UpstreamServiceImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.ParamService;
import com.cym.Interface.ParamServiceImpl;
import com.cym.Interface.TemplateService;
import com.cym.Interface.TemplateServiceImpl;
import com.cym.Interface.OperateLogService;
import com.cym.Interface.OperateLogServiceImpl;
import com.cym.Interface.Upstream;
import com.cym.Interface.UpstreamImpl;
import com.cym.Interface.AdminService;
import com.cym.Interface.AdminServiceImpl;
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
public UpstreamService upstreamservice(){

return  new UpstreamServiceImpl(); 
    }



@Bean
public ParamService paramservice(){

return  new ParamServiceImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public ParamService paramservice(){

return  new ParamServiceImpl(); 
    }



@Bean
public UpstreamService upstreamservice(){

return  new UpstreamServiceImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public ParamService paramservice(){

return  new ParamServiceImpl(); 
    }



@Bean
public TemplateService templateservice(){

return  new TemplateServiceImpl(); 
    }



@Bean
public OperateLogService operatelogservice(){

return  new OperateLogServiceImpl(); 
    }



@Bean
public Upstream upstream(){

return  new UpstreamImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public MessageUtils messageutils(){

return  new MessageUtilsImpl(); 
    }



}