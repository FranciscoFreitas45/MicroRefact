package sn;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import sn.Interface.AccountService;
import sn.Interface.AccountServiceImpl;
import sn.Interface.AccountService;
import sn.Interface.AccountServiceImpl;
import sn.Interface.PersonRepository;
import sn.Interface.PersonRepositoryImpl;
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
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public PersonRepository personrepository(){

return  new PersonRepositoryImpl(); 
    }



}