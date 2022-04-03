package net.shangtech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import net.shangtech.Interface.SysUserDao;
import net.shangtech.Interface.SysUserDaoImpl;
import net.shangtech.Interface.SiteTemplateService;
import net.shangtech.Interface.SiteTemplateServiceImpl;
import net.shangtech.Interface.SiteTemplateService;
import net.shangtech.Interface.SiteTemplateServiceImpl;
import net.shangtech.Interface.AppointmentDao;
import net.shangtech.Interface.AppointmentDaoImpl;
import net.shangtech.Interface.AppointmentHistoryDao;
import net.shangtech.Interface.AppointmentHistoryDaoImpl;
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
public SysUserDao sysuserdao(){

return  new SysUserDaoImpl(); 
    }



@Bean
public SiteTemplateService sitetemplateservice(){

return  new SiteTemplateServiceImpl(); 
    }



@Bean
public SiteTemplateService sitetemplateservice(){

return  new SiteTemplateServiceImpl(); 
    }



@Bean
public AppointmentDao appointmentdao(){

return  new AppointmentDaoImpl(); 
    }



@Bean
public AppointmentHistoryDao appointmenthistorydao(){

return  new AppointmentHistoryDaoImpl(); 
    }



}