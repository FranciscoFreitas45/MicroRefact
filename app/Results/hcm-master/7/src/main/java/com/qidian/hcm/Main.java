package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.SalaryItemRepository;
import com.qidian.hcm.Interface.SalaryItemRepositoryImpl;
import com.qidian.hcm.Interface.SocialSecurityPlanRepository;
import com.qidian.hcm.Interface.SocialSecurityPlanRepositoryImpl;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.EmployeeRepositoryImpl;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.FileServiceImpl;
import com.qidian.hcm.Interface.CalculateService;
import com.qidian.hcm.Interface.CalculateServiceImpl;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.EmployeePositionServiceImpl;
import com.qidian.hcm.Interface.EmployeeSalaryMonthlyService;
import com.qidian.hcm.Interface.EmployeeSalaryMonthlyServiceImpl;
import com.qidian.hcm.Interface.CalculateService;
import com.qidian.hcm.Interface.CalculateServiceImpl;
import com.qidian.hcm.Interface.CalculateService;
import com.qidian.hcm.Interface.CalculateServiceImpl;
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
public SalaryItemRepository salaryitemrepository(){

return  new SalaryItemRepositoryImpl(); 
    }



@Bean
public SocialSecurityPlanRepository socialsecurityplanrepository(){

return  new SocialSecurityPlanRepositoryImpl(); 
    }



@Bean
public EmployeeRepository employeerepository(){

return  new EmployeeRepositoryImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public CalculateService calculateservice(){

return  new CalculateServiceImpl(); 
    }



@Bean
public EmployeePositionService employeepositionservice(){

return  new EmployeePositionServiceImpl(); 
    }



@Bean
public EmployeeSalaryMonthlyService employeesalarymonthlyservice(){

return  new EmployeeSalaryMonthlyServiceImpl(); 
    }



@Bean
public CalculateService calculateservice(){

return  new CalculateServiceImpl(); 
    }



@Bean
public CalculateService calculateservice(){

return  new CalculateServiceImpl(); 
    }



}