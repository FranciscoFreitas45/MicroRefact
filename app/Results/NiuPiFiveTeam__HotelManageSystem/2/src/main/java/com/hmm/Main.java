package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.Department;
import com.hmm.Interface.DepartmentImpl;
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
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public Department department(){

return  new DepartmentImpl(); 
    }



}