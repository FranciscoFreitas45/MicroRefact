package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Supply;
import com.gs.Interface.SupplyImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Supply;
import com.gs.Interface.SupplyImpl;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.IncomingOutgoingServiceImpl;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.IncomingOutgoingServiceImpl;
import com.gs.Interface.RemindService;
import com.gs.Interface.RemindServiceImpl;
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
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Supply supply(){

return  new SupplyImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Supply supply(){

return  new SupplyImpl(); 
    }



@Bean
public IncomingOutgoingService incomingoutgoingservice(){

return  new IncomingOutgoingServiceImpl(); 
    }



@Bean
public IncomingOutgoingService incomingoutgoingservice(){

return  new IncomingOutgoingServiceImpl(); 
    }



@Bean
public RemindService remindservice(){

return  new RemindServiceImpl(); 
    }



}