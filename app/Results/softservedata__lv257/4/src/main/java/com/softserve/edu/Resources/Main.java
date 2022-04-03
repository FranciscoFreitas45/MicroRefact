package com.softserve.edu.Resources;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.softserve.edu.Resources.Interface.ResourceCategoryService;
import com.softserve.edu.Resources.Interface.ResourceCategoryServiceImpl;
import com.softserve.edu.Resources.Interface.RequestService;
import com.softserve.edu.Resources.Interface.RequestServiceImpl;
import com.softserve.edu.Resources.Interface.UserService;
import com.softserve.edu.Resources.Interface.UserServiceImpl;
import com.softserve.edu.Resources.Interface.ResourceCategoryService;
import com.softserve.edu.Resources.Interface.ResourceCategoryServiceImpl;
import com.softserve.edu.Resources.Interface.ResourceService;
import com.softserve.edu.Resources.Interface.ResourceServiceImpl;
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
public ResourceCategoryService resourcecategoryservice(){

return  new ResourceCategoryServiceImpl(); 
    }



@Bean
public RequestService requestservice(){

return  new RequestServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public ResourceCategoryService resourcecategoryservice(){

return  new ResourceCategoryServiceImpl(); 
    }



@Bean
public ResourceService resourceservice(){

return  new ResourceServiceImpl(); 
    }



}