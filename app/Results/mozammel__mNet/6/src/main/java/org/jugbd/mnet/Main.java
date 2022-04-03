package org.jugbd.mnet;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.jugbd.mnet.Interface.PatientService;
import org.jugbd.mnet.Interface.PatientServiceImpl;
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
public PatientService patientservice(){

return  new PatientServiceImpl(); 
    }



}