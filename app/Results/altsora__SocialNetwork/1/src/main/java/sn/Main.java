package sn;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import sn.Interface.JwtConfig;
import sn.Interface.JwtConfigImpl;
import sn.Interface.PersonRepository;
import sn.Interface.PersonRepositoryImpl;
import sn.Interface.AccountService;
import sn.Interface.AccountServiceImpl;
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
public JwtConfig jwtconfig(){

return  new JwtConfigImpl(); 
    }



@Bean
public PersonRepository personrepository(){

return  new PersonRepositoryImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



}