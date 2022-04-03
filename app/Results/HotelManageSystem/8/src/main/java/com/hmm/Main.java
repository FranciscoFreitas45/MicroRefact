package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.IInStorageService;
import com.hmm.Interface.IInStorageServiceImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.InDetailedRepository;
import com.hmm.Interface.InDetailedRepositoryImpl;
import com.hmm.Interface.OutDetailedRepository;
import com.hmm.Interface.OutDetailedRepositoryImpl;
import com.hmm.Interface.OutStorageRepository;
import com.hmm.Interface.OutStorageRepositoryImpl;
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
public IInStorageService iinstorageservice(){

return  new IInStorageServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public InDetailedRepository indetailedrepository(){

return  new InDetailedRepositoryImpl(); 
    }



@Bean
public OutDetailedRepository outdetailedrepository(){

return  new OutDetailedRepositoryImpl(); 
    }



@Bean
public OutStorageRepository outstoragerepository(){

return  new OutStorageRepositoryImpl(); 
    }



}