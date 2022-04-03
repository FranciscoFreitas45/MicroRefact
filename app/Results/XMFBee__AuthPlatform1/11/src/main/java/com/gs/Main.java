package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Role;
import com.gs.Interface.RoleImpl;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.AppointmentServiceImpl;
import com.gs.Interface.CompanyService;
import com.gs.Interface.CompanyServiceImpl;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.AppointmentServiceImpl;
import com.gs.Interface.CheckinService;
import com.gs.Interface.CheckinServiceImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
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
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Role role(){

return  new RoleImpl(); 
    }



@Bean
public AppointmentService appointmentservice(){

return  new AppointmentServiceImpl(); 
    }



@Bean
public CompanyService companyservice(){

return  new CompanyServiceImpl(); 
    }



@Bean
public AppointmentService appointmentservice(){

return  new AppointmentServiceImpl(); 
    }



@Bean
public CheckinService checkinservice(){

return  new CheckinServiceImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



}