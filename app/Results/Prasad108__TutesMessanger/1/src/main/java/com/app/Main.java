package com.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.app.Interface.InstituteService;
import com.app.Interface.InstituteServiceImpl;
import com.app.Interface.PermissionsService;
import com.app.Interface.PermissionsServiceImpl;
import com.app.Interface.StudentService;
import com.app.Interface.StudentServiceImpl;
import com.app.Interface.BranchService;
import com.app.Interface.BranchServiceImpl;
import com.app.Interface.ClassesService;
import com.app.Interface.ClassesServiceImpl;
import com.app.Interface.DivisionService;
import com.app.Interface.DivisionServiceImpl;
import com.app.Interface.InstituteService;
import com.app.Interface.InstituteServiceImpl;
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
public InstituteService instituteservice(){

return  new InstituteServiceImpl(); 
    }



@Bean
public PermissionsService permissionsservice(){

return  new PermissionsServiceImpl(); 
    }



@Bean
public StudentService studentservice(){

return  new StudentServiceImpl(); 
    }



@Bean
public BranchService branchservice(){

return  new BranchServiceImpl(); 
    }



@Bean
public ClassesService classesservice(){

return  new ClassesServiceImpl(); 
    }



@Bean
public DivisionService divisionservice(){

return  new DivisionServiceImpl(); 
    }



@Bean
public InstituteService instituteservice(){

return  new InstituteServiceImpl(); 
    }



}