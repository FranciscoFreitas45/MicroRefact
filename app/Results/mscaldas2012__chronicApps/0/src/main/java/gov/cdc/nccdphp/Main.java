package gov.cdc.nccdphp;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import gov.cdc.nccdphp.Interface.ManagerRepository;
import gov.cdc.nccdphp.Interface.ManagerRepositoryImpl;
import gov.cdc.nccdphp.Interface.DivisionRepository;
import gov.cdc.nccdphp.Interface.DivisionRepositoryImpl;
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
public ManagerRepository managerrepository(){

return  new ManagerRepositoryImpl(); 
    }



@Bean
public DivisionRepository divisionrepository(){

return  new DivisionRepositoryImpl(); 
    }



}