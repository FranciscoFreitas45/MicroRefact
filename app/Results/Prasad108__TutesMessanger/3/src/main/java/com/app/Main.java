package com.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.app.Interface.RoleService;
import com.app.Interface.RoleServiceImpl;
import com.app.Interface.TeacherService;
import com.app.Interface.TeacherServiceImpl;
import com.app.Interface.LoginService;
import com.app.Interface.LoginServiceImpl;
import com.app.Interface.PermissionsService;
import com.app.Interface.PermissionsServiceImpl;
import com.app.Interface.RoleService;
import com.app.Interface.RoleServiceImpl;
import com.app.Interface.TeacherService;
import com.app.Interface.TeacherServiceImpl;
import com.app.Interface.LoginService;
import com.app.Interface.LoginServiceImpl;
import com.app.Interface.PermissionsService;
import com.app.Interface.PermissionsServiceImpl;
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
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public TeacherService teacherservice(){

return  new TeacherServiceImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



@Bean
public PermissionsService permissionsservice(){

return  new PermissionsServiceImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public TeacherService teacherservice(){

return  new TeacherServiceImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



@Bean
public PermissionsService permissionsservice(){

return  new PermissionsServiceImpl(); 
    }



}