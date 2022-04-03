package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.SalaryOrderRepository;
import com.hmm.Interface.SalaryOrderRepositoryImpl;
import com.hmm.Interface.RoomOrderRepository;
import com.hmm.Interface.RoomOrderRepositoryImpl;
import com.hmm.Interface.InStorageRepository;
import com.hmm.Interface.InStorageRepositoryImpl;
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
public SalaryOrderRepository salaryorderrepository(){

return  new SalaryOrderRepositoryImpl(); 
    }



@Bean
public RoomOrderRepository roomorderrepository(){

return  new RoomOrderRepositoryImpl(); 
    }



@Bean
public InStorageRepository instoragerepository(){

return  new InStorageRepositoryImpl(); 
    }



}