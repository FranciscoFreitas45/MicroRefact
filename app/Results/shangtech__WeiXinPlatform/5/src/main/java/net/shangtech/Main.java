package net.shangtech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import net.shangtech.Interface.CustomServiceDao;
import net.shangtech.Interface.CustomServiceDaoImpl;
import net.shangtech.Interface.CustomServiceGroupDao;
import net.shangtech.Interface.CustomServiceGroupDaoImpl;
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
public CustomServiceDao customservicedao(){

return  new CustomServiceDaoImpl(); 
    }



@Bean
public CustomServiceGroupDao customservicegroupdao(){

return  new CustomServiceGroupDaoImpl(); 
    }



}