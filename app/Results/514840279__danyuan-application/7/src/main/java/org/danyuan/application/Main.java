package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDao;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDaoImpl;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDao;
import org.danyuan.application.Interface.SysCrawlerRulerInfoDaoImpl;
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
public SysCrawlerRulerInfoDao syscrawlerrulerinfodao(){

return  new SysCrawlerRulerInfoDaoImpl(); 
    }



@Bean
public SysCrawlerRulerInfoDao syscrawlerrulerinfodao(){

return  new SysCrawlerRulerInfoDaoImpl(); 
    }



}