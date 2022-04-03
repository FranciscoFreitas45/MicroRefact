package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.Interface.MoneyValueImpl;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.IPersonRepositoryImpl;
import switchtwentytwenty.project.Interface.IFamilyRepository;
import switchtwentytwenty.project.Interface.IFamilyRepositoryImpl;
import switchtwentytwenty.project.Interface.AuthorizationService;
import switchtwentytwenty.project.Interface.AuthorizationServiceImpl;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.Interface.MoneyValueImpl;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.Interface.MoneyValueImpl;
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
public MoneyValue moneyvalue(){

return  new MoneyValueImpl(); 
    }



@Bean
public IPersonRepository ipersonrepository(){

return  new IPersonRepositoryImpl(); 
    }



@Bean
public IFamilyRepository ifamilyrepository(){

return  new IFamilyRepositoryImpl(); 
    }



@Bean
public AuthorizationService authorizationservice(){

return  new AuthorizationServiceImpl(); 
    }



@Bean
public MoneyValue moneyvalue(){

return  new MoneyValueImpl(); 
    }



@Bean
public MoneyValue moneyvalue(){

return  new MoneyValueImpl(); 
    }



}