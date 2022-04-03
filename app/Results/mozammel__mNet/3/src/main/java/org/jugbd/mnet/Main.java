package org.jugbd.mnet;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.jugbd.mnet.Interface.PatientDao;
import org.jugbd.mnet.Interface.PatientDaoImpl;
import org.jugbd.mnet.Interface.VitalDao;
import org.jugbd.mnet.Interface.VitalDaoImpl;
import org.jugbd.mnet.Interface.PatientService;
import org.jugbd.mnet.Interface.PatientServiceImpl;
import org.jugbd.mnet.Interface.User;
import org.jugbd.mnet.Interface.UserImpl;
import org.jugbd.mnet.Interface.User;
import org.jugbd.mnet.Interface.UserImpl;
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
public PatientDao patientdao(){

return  new PatientDaoImpl(); 
    }



@Bean
public VitalDao vitaldao(){

return  new VitalDaoImpl(); 
    }



@Bean
public PatientService patientservice(){

return  new PatientServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}