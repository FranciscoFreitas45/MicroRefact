package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.DeptServiceImpl;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.DeptServiceImpl;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.DeptServiceImpl;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
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
public DeptService deptservice(){

return  new DeptServiceImpl(); 
    }



@Bean
public IWorkflowService iworkflowservice(){

return  new IWorkflowServiceImpl(); 
    }



@Bean
public DeptService deptservice(){

return  new DeptServiceImpl(); 
    }



@Bean
public IWorkflowService iworkflowservice(){

return  new IWorkflowServiceImpl(); 
    }



@Bean
public DeptService deptservice(){

return  new DeptServiceImpl(); 
    }



@Bean
public IWorkflowService iworkflowservice(){

return  new IWorkflowServiceImpl(); 
    }



}