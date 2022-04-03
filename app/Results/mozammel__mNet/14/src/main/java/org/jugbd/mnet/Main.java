package org.jugbd.mnet;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.jugbd.mnet.Interface.RegisterService;
import org.jugbd.mnet.Interface.RegisterServiceImpl;
import org.jugbd.mnet.Interface.RegisterDao;
import org.jugbd.mnet.Interface.RegisterDaoImpl;
import org.jugbd.mnet.Interface.RegisterService;
import org.jugbd.mnet.Interface.RegisterServiceImpl;
import org.jugbd.mnet.Interface.RegisterDao;
import org.jugbd.mnet.Interface.RegisterDaoImpl;
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
public RegisterService registerservice(){

return  new RegisterServiceImpl(); 
    }



@Bean
public RegisterDao registerdao(){

return  new RegisterDaoImpl(); 
    }



@Bean
public RegisterService registerservice(){

return  new RegisterServiceImpl(); 
    }



@Bean
public RegisterDao registerdao(){

return  new RegisterDaoImpl(); 
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