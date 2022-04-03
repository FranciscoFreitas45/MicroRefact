package com.cym;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cym.Interface.UpstreamService;
import com.cym.Interface.UpstreamServiceImpl;
import com.cym.Interface.ServerService;
import com.cym.Interface.ServerServiceImpl;
import com.cym.Interface.ConfService;
import com.cym.Interface.ConfServiceImpl;
import com.cym.Interface.AdminService;
import com.cym.Interface.AdminServiceImpl;
import com.cym.Interface.BasicService;
import com.cym.Interface.BasicServiceImpl;
import com.cym.Interface.ScheduleTask;
import com.cym.Interface.ScheduleTaskImpl;
import com.cym.Interface.AdminService;
import com.cym.Interface.AdminServiceImpl;
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
public ServerService serverservice(){

return  new ServerServiceImpl(); 
    }



@Bean
public ConfService confservice(){

return  new ConfServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public BasicService basicservice(){

return  new BasicServiceImpl(); 
    }



@Bean
public ScheduleTask scheduletask(){

return  new ScheduleTaskImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



}