package com.ushahidi.swiftriver.core;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ushahidi.swiftriver.core.Interface.FormField;
import com.ushahidi.swiftriver.core.Interface.FormFieldImpl;
import com.ushahidi.swiftriver.core.Interface.Form;
import com.ushahidi.swiftriver.core.Interface.FormImpl;
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
public FormField formfield(){

return  new FormFieldImpl(); 
    }



@Bean
public Form form(){

return  new FormImpl(); 
    }



}