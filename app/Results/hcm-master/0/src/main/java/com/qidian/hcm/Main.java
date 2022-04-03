package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.HCMConfig;
import com.qidian.hcm.Interface.HCMConfigImpl;
import com.qidian.hcm.Interface.EmployeeService;
import com.qidian.hcm.Interface.EmployeeServiceImpl;
import com.qidian.hcm.Interface.HCMConfig;
import com.qidian.hcm.Interface.HCMConfigImpl;
import com.qidian.hcm.Interface.HCMConfig;
import com.qidian.hcm.Interface.HCMConfigImpl;
import com.qidian.hcm.Interface.EmployeeService;
import com.qidian.hcm.Interface.EmployeeServiceImpl;
import com.qidian.hcm.Interface.EmployeePermissionService;
import com.qidian.hcm.Interface.EmployeePermissionServiceImpl;
import com.qidian.hcm.Interface.RolePermissionDTO;
import com.qidian.hcm.Interface.RolePermissionDTOImpl;
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
public HCMConfig hcmconfig(){

return  new HCMConfigImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public HCMConfig hcmconfig(){

return  new HCMConfigImpl(); 
    }



@Bean
public HCMConfig hcmconfig(){

return  new HCMConfigImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public EmployeePermissionService employeepermissionservice(){

return  new EmployeePermissionServiceImpl(); 
    }



@Bean
public RolePermissionDTO rolepermissiondto(){

return  new RolePermissionDTOImpl(); 
    }



}