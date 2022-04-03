package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.CarColor;
import com.gs.Interface.CarColorImpl;
import com.gs.Interface.CarPlate;
import com.gs.Interface.CarPlateImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
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
public CarColor carcolor(){

return  new CarColorImpl(); 
    }



@Bean
public CarPlate carplate(){

return  new CarPlateImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}