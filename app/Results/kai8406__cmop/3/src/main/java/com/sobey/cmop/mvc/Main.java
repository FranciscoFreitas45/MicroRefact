package com.sobey.cmop.mvc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.sobey.cmop.mvc.Interface.CommonService;
import com.sobey.cmop.mvc.Interface.CommonServiceImpl;
import com.sobey.cmop.mvc.Interface.CommonService;
import com.sobey.cmop.mvc.Interface.CommonServiceImpl;
import com.sobey.cmop.mvc.Interface.PropertiesLoader;
import com.sobey.cmop.mvc.Interface.PropertiesLoaderImpl;
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
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



@Bean
public PropertiesLoader propertiesloader(){

return  new PropertiesLoaderImpl(); 
    }



}