package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.ICategoryRepository;
import switchtwentytwenty.project.Interface.ICategoryRepositoryImpl;
import switchtwentytwenty.project.Interface.ICategoryIDGenerator;
import switchtwentytwenty.project.Interface.ICategoryIDGeneratorImpl;
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
public ICategoryRepository icategoryrepository(){

return  new ICategoryRepositoryImpl(); 
    }



@Bean
public ICategoryIDGenerator icategoryidgenerator(){

return  new ICategoryIDGeneratorImpl(); 
    }



}