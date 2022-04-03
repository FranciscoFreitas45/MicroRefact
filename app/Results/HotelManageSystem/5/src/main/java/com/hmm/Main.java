package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.IWorkflowServiceImpl;
import com.hmm.Interface.IDeptService;
import com.hmm.Interface.IDeptServiceImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.IDeptService;
import com.hmm.Interface.IDeptServiceImpl;
import com.hmm.Interface.SchedulEventService;
import com.hmm.Interface.SchedulEventServiceImpl;
import com.hmm.Interface.Department;
import com.hmm.Interface.DepartmentImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.IDeptService;
import com.hmm.Interface.IDeptServiceImpl;
import com.hmm.Interface.ILeaveService;
import com.hmm.Interface.ILeaveServiceImpl;
import com.hmm.Interface.TravelService;
import com.hmm.Interface.TravelServiceImpl;
import com.hmm.Interface.SchedulEventService;
import com.hmm.Interface.SchedulEventServiceImpl;
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
public IDeptService ideptservice(){

return  new IDeptServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public IDeptService ideptservice(){

return  new IDeptServiceImpl(); 
    }



@Bean
public SchedulEventService scheduleventservice(){

return  new SchedulEventServiceImpl(); 
    }



@Bean
public Department department(){

return  new DepartmentImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public IDeptService ideptservice(){

return  new IDeptServiceImpl(); 
    }



@Bean
public ILeaveService ileaveservice(){

return  new ILeaveServiceImpl(); 
    }



@Bean
public TravelService travelservice(){

return  new TravelServiceImpl(); 
    }



@Bean
public SchedulEventService scheduleventservice(){

return  new SchedulEventServiceImpl(); 
    }



}