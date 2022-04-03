package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.OrganizationRepositoryImpl;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.EmployeePositionServiceImpl;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.FileServiceImpl;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.EmployeeRepositoryImpl;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.EmployeeRepositoryImpl;
import com.qidian.hcm.Interface.SalaryThresholdRepository;
import com.qidian.hcm.Interface.SalaryThresholdRepositoryImpl;
import com.qidian.hcm.Interface.HousingFundPlanRepository;
import com.qidian.hcm.Interface.HousingFundPlanRepositoryImpl;
import com.qidian.hcm.Interface.SocialSecurityPlanRepository;
import com.qidian.hcm.Interface.SocialSecurityPlanRepositoryImpl;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.EmployeeRepositoryImpl;
import com.qidian.hcm.Interface.CommonRepositoryUtil;
import com.qidian.hcm.Interface.CommonRepositoryUtilImpl;
import com.qidian.hcm.Interface.SalaryThresholdService;
import com.qidian.hcm.Interface.SalaryThresholdServiceImpl;
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
public OrganizationRepository organizationrepository(){

return  new OrganizationRepositoryImpl(); 
    }



@Bean
public EmployeePositionService employeepositionservice(){

return  new EmployeePositionServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public EmployeeRepository employeerepository(){

return  new EmployeeRepositoryImpl(); 
    }



@Bean
public EmployeeRepository employeerepository(){

return  new EmployeeRepositoryImpl(); 
    }



@Bean
public SalaryThresholdRepository salarythresholdrepository(){

return  new SalaryThresholdRepositoryImpl(); 
    }



@Bean
public HousingFundPlanRepository housingfundplanrepository(){

return  new HousingFundPlanRepositoryImpl(); 
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
public CommonRepositoryUtil commonrepositoryutil(){

return  new CommonRepositoryUtilImpl(); 
    }



@Bean
public SalaryThresholdService salarythresholdservice(){

return  new SalaryThresholdServiceImpl(); 
    }



}