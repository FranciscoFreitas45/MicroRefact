package com.cg.oms;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cg.oms.Interface.Role;
import com.cg.oms.Interface.RoleImpl;
import com.cg.oms.Interface.Address;
import com.cg.oms.Interface.AddressImpl;
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
public Role role(){

return  new RoleImpl(); 
    }



@Bean
public Address address(){

return  new AddressImpl(); 
    }



}