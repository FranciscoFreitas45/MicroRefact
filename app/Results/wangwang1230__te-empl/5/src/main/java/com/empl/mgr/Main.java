package com.empl.mgr;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.empl.mgr.Interface.EmployeesBasicDao;
import com.empl.mgr.Interface.EmployeesBasicDaoImpl;
import com.empl.mgr.Interface.EmployeesBasicDao;
import com.empl.mgr.Interface.EmployeesBasicDaoImpl;
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
public EmployeesBasicDao employeesbasicdao(){

return  new EmployeesBasicDaoImpl(); 
    }



@Bean
public EmployeesBasicDao employeesbasicdao(){

return  new EmployeesBasicDaoImpl(); 
    }



}