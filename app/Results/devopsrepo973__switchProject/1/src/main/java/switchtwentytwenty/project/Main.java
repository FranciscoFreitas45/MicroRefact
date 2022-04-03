package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.CategoryID;
import switchtwentytwenty.project.Interface.CategoryIDImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.CategoryID;
import switchtwentytwenty.project.Interface.CategoryIDImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.CategoryID;
import switchtwentytwenty.project.Interface.CategoryIDImpl;
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
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public CategoryID categoryid(){

return  new CategoryIDImpl(); 
    }



@Bean
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public CategoryID categoryid(){

return  new CategoryIDImpl(); 
    }



@Bean
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public CategoryID categoryid(){

return  new CategoryIDImpl(); 
    }



}