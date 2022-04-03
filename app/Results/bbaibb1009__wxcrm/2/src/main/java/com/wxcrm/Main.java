package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IRoleService;
import com.wxcrm.Interface.IRoleServiceImpl;
import com.wxcrm.Interface.IMemcachedService;
import com.wxcrm.Interface.IMemcachedServiceImpl;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IJdbcDaoImpl;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.Interface.IHibernateDaoImpl;
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
public IRoleService iroleservice(){

return  new IRoleServiceImpl(); 
    }



@Bean
public IMemcachedService imemcachedservice(){

return  new IMemcachedServiceImpl(); 
    }



@Bean
public IJdbcDao ijdbcdao(){

return  new IJdbcDaoImpl(); 
    }



@Bean
public IHibernateDao ihibernatedao(){

return  new IHibernateDaoImpl(); 
    }



}