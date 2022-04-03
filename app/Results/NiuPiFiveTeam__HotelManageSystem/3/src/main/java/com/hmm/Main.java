package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.EmployeeDao;
import com.hmm.Interface.EmployeeDaoImpl;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.DeptServiceImpl;
import com.hmm.Interface.GroupRoleService;
import com.hmm.Interface.GroupRoleServiceImpl;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
import com.hmm.Interface.DeptService;
import com.hmm.Interface.DeptServiceImpl;
import com.hmm.Interface.GroupRoleService;
import com.hmm.Interface.GroupRoleServiceImpl;
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
public EmployeeDao employeedao(){

return  new EmployeeDaoImpl(); 
    }



@Bean
public DeptService deptservice(){

return  new DeptServiceImpl(); 
    }



@Bean
public GroupRoleService grouproleservice(){

return  new GroupRoleServiceImpl(); 
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
public GroupRoleService grouproleservice(){

return  new GroupRoleServiceImpl(); 
    }



@Bean
public IWorkflowService iworkflowservice(){

return  new IWorkflowServiceImpl(); 
    }



}