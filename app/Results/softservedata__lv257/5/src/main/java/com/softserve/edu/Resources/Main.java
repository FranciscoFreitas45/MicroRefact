package com.softserve.edu.Resources;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.softserve.edu.Resources.Interface.ResourceType;
import com.softserve.edu.Resources.Interface.ResourceTypeImpl;
import com.softserve.edu.Resources.Interface.ResourceTypeService;
import com.softserve.edu.Resources.Interface.ResourceTypeServiceImpl;
import com.softserve.edu.Resources.Interface.ResourceTypeDAO;
import com.softserve.edu.Resources.Interface.ResourceTypeDAOImpl;
import com.softserve.edu.Resources.Interface.ConstrainedProperty;
import com.softserve.edu.Resources.Interface.ConstrainedPropertyImpl;
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
public ResourceType resourcetype(){

return  new ResourceTypeImpl(); 
    }



@Bean
public ResourceTypeService resourcetypeservice(){

return  new ResourceTypeServiceImpl(); 
    }



@Bean
public ResourceTypeDAO resourcetypedao(){

return  new ResourceTypeDAOImpl(); 
    }



@Bean
public ConstrainedProperty constrainedproperty(){

return  new ConstrainedPropertyImpl(); 
    }



}