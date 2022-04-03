package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.EmployeeFinancialRepository;
import com.qidian.hcm.Interface.EmployeeFinancialRepositoryImpl;
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
public EmployeeFinancialRepository employeefinancialrepository(){

return  new EmployeeFinancialRepositoryImpl(); 
    }



@Bean
public CalculateService calculateservice(){

return  new CalculateServiceImpl(); 
    }



}