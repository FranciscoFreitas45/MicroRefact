package com.byr.warehouse;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.byr.warehouse.Interface.MailService;
import com.byr.warehouse.Interface.MailServiceImpl;
import com.byr.warehouse.Interface.LogService;
import com.byr.warehouse.Interface.LogServiceImpl;
import com.byr.warehouse.Interface.DaliyComputeShedule;
import com.byr.warehouse.Interface.DaliyComputeSheduleImpl;
import com.byr.warehouse.Interface.ApplyEnterRepository;
import com.byr.warehouse.Interface.ApplyEnterRepositoryImpl;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepositoryImpl;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepositoryImpl;
import com.byr.warehouse.Interface.EntrepotStatusService;
import com.byr.warehouse.Interface.EntrepotStatusServiceImpl;
import com.byr.warehouse.Interface.LogService;
import com.byr.warehouse.Interface.LogServiceImpl;
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
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



@Bean
public DaliyComputeShedule daliycomputeshedule(){

return  new DaliyComputeSheduleImpl(); 
    }



@Bean
public ApplyEnterRepository applyenterrepository(){

return  new ApplyEnterRepositoryImpl(); 
    }



@Bean
public EntrepotStatusRepository entrepotstatusrepository(){

return  new EntrepotStatusRepositoryImpl(); 
    }



@Bean
public EntrepotStatusRepository entrepotstatusrepository(){

return  new EntrepotStatusRepositoryImpl(); 
    }



@Bean
public EntrepotStatusService entrepotstatusservice(){

return  new EntrepotStatusServiceImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



}