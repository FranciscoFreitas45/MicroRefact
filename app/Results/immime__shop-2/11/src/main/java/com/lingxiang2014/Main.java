package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.CaptchaService;
import com.lingxiang2014.Interface.CaptchaServiceImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
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
public CaptchaService captchaservice(){

return  new CaptchaServiceImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



}