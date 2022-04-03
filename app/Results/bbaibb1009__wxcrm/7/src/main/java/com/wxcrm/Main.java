package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IHibernateDaoImpl;
import com.wxcrm.Interface.IRoleService;
import com.wxcrm.Interface.IRoleServiceImpl;
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
public IHibernateDao ihibernatedao(){

return  new IHibernateDaoImpl(); 
    }



@Bean
public IRoleService iroleservice(){

return  new IRoleServiceImpl(); 
    }



}