package com.softserve.edu.Resources;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.softserve.edu.Resources.Interface.UserDAO;
import com.softserve.edu.Resources.Interface.UserDAOImpl;
import com.softserve.edu.Resources.Interface.UserService;
import com.softserve.edu.Resources.Interface.UserServiceImpl;
import com.softserve.edu.Resources.Interface.User;
import com.softserve.edu.Resources.Interface.UserImpl;
import com.softserve.edu.Resources.Interface.ResourceTypeService;
import com.softserve.edu.Resources.Interface.ResourceTypeServiceImpl;
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
public UserDAO userdao(){

return  new UserDAOImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public ResourceTypeService resourcetypeservice(){

return  new ResourceTypeServiceImpl(); 
    }



}