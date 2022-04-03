package com.wxcrm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.wxcrm.Interface.IWeixinService;
import com.wxcrm.Interface.IWeixinServiceImpl;
import com.wxcrm.Interface.IWeixinEnterService;
import com.wxcrm.Interface.IWeixinEnterServiceImpl;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IJdbcDaoImpl;
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
public IWeixinService iweixinservice(){

return  new IWeixinServiceImpl(); 
    }



@Bean
public IWeixinEnterService iweixinenterservice(){

return  new IWeixinEnterServiceImpl(); 
    }



@Bean
public IJdbcDao ijdbcdao(){

return  new IJdbcDaoImpl(); 
    }



}