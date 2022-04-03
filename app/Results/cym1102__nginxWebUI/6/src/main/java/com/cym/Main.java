package com.cym;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.ConfService;
import com.cym.Interface.ConfServiceImpl;
import com.cym.Interface.ConfController;
import com.cym.Interface.ConfControllerImpl;
import com.cym.Interface.MainController;
import com.cym.Interface.MainControllerImpl;
import com.cym.Interface.NginxApiController;
import com.cym.Interface.NginxApiControllerImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.CertController;
import com.cym.Interface.CertControllerImpl;
import com.cym.Interface.SettingService;
import com.cym.Interface.SettingServiceImpl;
import com.cym.Interface.ConfController;
import com.cym.Interface.ConfControllerImpl;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.UpstreamServiceImpl;
import com.cym.Interface.SendMailUtils;
import com.cym.Interface.SendMailUtilsImpl;
import com.cym.Interface.HttpService;
import com.cym.Interface.HttpServiceImpl;
import com.cym.Interface.MessageUtils;
import com.cym.Interface.MessageUtilsImpl;
import com.cym.Interface.AdminService;
import com.cym.Interface.AdminServiceImpl;
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
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public ConfService confservice(){

return  new ConfServiceImpl(); 
    }



@Bean
public ConfController confcontroller(){

return  new ConfControllerImpl(); 
    }



@Bean
public MainController maincontroller(){

return  new MainControllerImpl(); 
    }



@Bean
public NginxApiController nginxapicontroller(){

return  new NginxApiControllerImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public CertController certcontroller(){

return  new CertControllerImpl(); 
    }



@Bean
public SettingService settingservice(){

return  new SettingServiceImpl(); 
    }



@Bean
public ConfController confcontroller(){

return  new ConfControllerImpl(); 
    }



@Bean
public UpstreamService upstreamservice(){

return  new UpstreamServiceImpl(); 
    }



@Bean
public SendMailUtils sendmailutils(){

return  new SendMailUtilsImpl(); 
    }



@Bean
public HttpService httpservice(){

return  new HttpServiceImpl(); 
    }



@Bean
public MessageUtils messageutils(){

return  new MessageUtilsImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
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