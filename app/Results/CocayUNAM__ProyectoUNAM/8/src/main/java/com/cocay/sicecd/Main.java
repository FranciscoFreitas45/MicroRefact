package com.cocay.sicecd;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cocay.sicecd.Interface.SendMailService;
import com.cocay.sicecd.Interface.SendMailServiceImpl;
import com.cocay.sicecd.Interface.SendMailService;
import com.cocay.sicecd.Interface.SendMailServiceImpl;
import com.cocay.sicecd.Interface.ProfesorDto;
import com.cocay.sicecd.Interface.ProfesorDtoImpl;
import com.cocay.sicecd.Interface.InscripcionDto;
import com.cocay.sicecd.Interface.InscripcionDtoImpl;
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
public SendMailService sendmailservice(){

return  new SendMailServiceImpl(); 
    }



@Bean
public SendMailService sendmailservice(){

return  new SendMailServiceImpl(); 
    }



@Bean
public ProfesorDto profesordto(){

return  new ProfesorDtoImpl(); 
    }



@Bean
public InscripcionDto inscripciondto(){

return  new InscripcionDtoImpl(); 
    }



}