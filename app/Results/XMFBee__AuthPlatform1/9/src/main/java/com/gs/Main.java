package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.CarBrand;
import com.gs.Interface.CarBrandImpl;
import com.gs.Interface.CarColor;
import com.gs.Interface.CarColorImpl;
import com.gs.Interface.CarModel;
import com.gs.Interface.CarModelImpl;
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
public CarBrand carbrand(){

return  new CarBrandImpl(); 
    }



@Bean
public CarColor carcolor(){

return  new CarColorImpl(); 
    }



@Bean
public CarModel carmodel(){

return  new CarModelImpl(); 
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