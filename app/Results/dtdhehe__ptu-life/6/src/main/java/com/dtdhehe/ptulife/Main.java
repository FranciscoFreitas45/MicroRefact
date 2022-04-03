package com.dtdhehe.ptulife;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.dtdhehe.ptulife.Interface.RedisUtils;
import com.dtdhehe.ptulife.Interface.RedisUtilsImpl;
import com.dtdhehe.ptulife.Interface.RedisUtils;
import com.dtdhehe.ptulife.Interface.RedisUtilsImpl;
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
public RedisUtils redisutils(){

return  new RedisUtilsImpl(); 
    }



@Bean
public RedisUtils redisutils(){

return  new RedisUtilsImpl(); 
    }



}