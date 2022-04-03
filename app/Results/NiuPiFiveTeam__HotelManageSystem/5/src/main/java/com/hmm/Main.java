package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
import com.hmm.Interface.EmployeeDao;
import com.hmm.Interface.EmployeeDaoImpl;
import com.hmm.Interface.InDetailedRepository;
import com.hmm.Interface.InDetailedRepositoryImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
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
public IWorkflowService iworkflowservice(){

return  new IWorkflowServiceImpl(); 
    }



@Bean
public EmployeeDao employeedao(){

return  new EmployeeDaoImpl(); 
    }



@Bean
public InDetailedRepository indetailedrepository(){

return  new InDetailedRepositoryImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



}