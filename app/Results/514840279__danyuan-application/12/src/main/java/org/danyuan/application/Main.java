package org.danyuan.application;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.danyuan.application.Interface.SysCrawlerResultRulerInfoDao;
import org.danyuan.application.Interface.SysCrawlerResultRulerInfoDaoImpl;
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
public SysCrawlerResultRulerInfoDao syscrawlerresultrulerinfodao(){

return  new SysCrawlerResultRulerInfoDaoImpl(); 
    }



}