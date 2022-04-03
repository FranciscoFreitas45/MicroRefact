package edu.nju.careerbridge;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import edu.nju.careerbridge.Interface.JobDetailRepository;
import edu.nju.careerbridge.Interface.JobDetailRepositoryImpl;
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
public JobDetailRepository jobdetailrepository(){

return  new JobDetailRepositoryImpl(); 
    }



}