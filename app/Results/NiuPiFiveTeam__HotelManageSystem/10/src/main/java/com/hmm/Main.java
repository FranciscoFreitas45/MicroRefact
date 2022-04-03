package com.hmm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.OutStorageRepository;
import com.hmm.Interface.OutStorageRepositoryImpl;
import com.hmm.Interface.OutDetailedRepository;
import com.hmm.Interface.OutDetailedRepositoryImpl;
import com.hmm.Interface.IStockService;
import com.hmm.Interface.IStockServiceImpl;
import com.hmm.Interface.IStockService;
import com.hmm.Interface.IStockServiceImpl;
import com.hmm.Interface.EmployeeService;
import com.hmm.Interface.EmployeeServiceImpl;
import com.hmm.Interface.OutStorageRepository;
import com.hmm.Interface.OutStorageRepositoryImpl;
import com.hmm.Interface.OutDetailedRepository;
import com.hmm.Interface.OutDetailedRepositoryImpl;
import com.hmm.Interface.StockRepository;
import com.hmm.Interface.StockRepositoryImpl;
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
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public OutStorageRepository outstoragerepository(){

return  new OutStorageRepositoryImpl(); 
    }



@Bean
public OutDetailedRepository outdetailedrepository(){

return  new OutDetailedRepositoryImpl(); 
    }



@Bean
public IStockService istockservice(){

return  new IStockServiceImpl(); 
    }



@Bean
public IStockService istockservice(){

return  new IStockServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public OutStorageRepository outstoragerepository(){

return  new OutStorageRepositoryImpl(); 
    }



@Bean
public OutDetailedRepository outdetailedrepository(){

return  new OutDetailedRepositoryImpl(); 
    }



@Bean
public StockRepository stockrepository(){

return  new StockRepositoryImpl(); 
    }



}