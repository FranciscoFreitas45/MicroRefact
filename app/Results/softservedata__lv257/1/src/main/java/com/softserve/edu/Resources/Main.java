package com.softserve.edu.Resources;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.softserve.edu.Resources.Interface.RoleService;
import com.softserve.edu.Resources.Interface.RoleServiceImpl;
import com.softserve.edu.Resources.Interface.PrivilegeService;
import com.softserve.edu.Resources.Interface.PrivilegeServiceImpl;
import com.softserve.edu.Resources.Interface.RoleService;
import com.softserve.edu.Resources.Interface.RoleServiceImpl;
import com.softserve.edu.Resources.Interface.PrivilegeService;
import com.softserve.edu.Resources.Interface.PrivilegeServiceImpl;
import com.softserve.edu.Resources.Interface.RoleDAO;
import com.softserve.edu.Resources.Interface.RoleDAOImpl;
import com.softserve.edu.Resources.Interface.VerificationTokenDAO;
import com.softserve.edu.Resources.Interface.VerificationTokenDAOImpl;
import com.softserve.edu.Resources.Interface.RoleDAO;
import com.softserve.edu.Resources.Interface.RoleDAOImpl;
import com.softserve.edu.Resources.Interface.User;
import com.softserve.edu.Resources.Interface.UserImpl;
import com.softserve.edu.Resources.Interface.RoleDAO;
import com.softserve.edu.Resources.Interface.RoleDAOImpl;
import com.softserve.edu.Resources.Interface.PropertyService;
import com.softserve.edu.Resources.Interface.PropertyServiceImpl;
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
public PrivilegeService privilegeservice(){

return  new PrivilegeServiceImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public PrivilegeService privilegeservice(){

return  new PrivilegeServiceImpl(); 
    }



@Bean
public RoleDAO roledao(){

return  new RoleDAOImpl(); 
    }



@Bean
public VerificationTokenDAO verificationtokendao(){

return  new VerificationTokenDAOImpl(); 
    }



@Bean
public RoleDAO roledao(){

return  new RoleDAOImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public RoleDAO roledao(){

return  new RoleDAOImpl(); 
    }



@Bean
public PropertyService propertyservice(){

return  new PropertyServiceImpl(); 
    }



}