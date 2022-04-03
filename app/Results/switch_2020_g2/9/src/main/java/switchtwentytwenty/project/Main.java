package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.MoneyValue;
import switchtwentytwenty.project.Interface.MoneyValueImpl;
import switchtwentytwenty.project.Interface.CategoryID;
import switchtwentytwenty.project.Interface.CategoryIDImpl;
import switchtwentytwenty.project.Interface.TransactionDate;
import switchtwentytwenty.project.Interface.TransactionDateImpl;
import switchtwentytwenty.project.Interface.TransactionDescription;
import switchtwentytwenty.project.Interface.TransactionDescriptionImpl;
import switchtwentytwenty.project.Interface.TransactionService;
import switchtwentytwenty.project.Interface.TransactionServiceImpl;
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
public Email email(){

return  new EmailImpl(); 
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
public MoneyValue moneyvalue(){

return  new MoneyValueImpl(); 
    }



@Bean
public CategoryID categoryid(){

return  new CategoryIDImpl(); 
    }



@Bean
public TransactionDate transactiondate(){

return  new TransactionDateImpl(); 
    }



@Bean
public TransactionDescription transactiondescription(){

return  new TransactionDescriptionImpl(); 
    }



@Bean
public TransactionService transactionservice(){

return  new TransactionServiceImpl(); 
    }



}