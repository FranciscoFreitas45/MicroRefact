package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.IPersonRepositoryImpl;
import switchtwentytwenty.project.Interface.IFamilyRepository;
import switchtwentytwenty.project.Interface.IFamilyRepositoryImpl;
import switchtwentytwenty.project.Interface.IAccountRepository;
import switchtwentytwenty.project.Interface.IAccountRepositoryImpl;
import switchtwentytwenty.project.Interface.ILedgerRepository;
import switchtwentytwenty.project.Interface.ILedgerRepositoryImpl;
import switchtwentytwenty.project.Interface.ICategoryRepository;
import switchtwentytwenty.project.Interface.ICategoryRepositoryImpl;
import switchtwentytwenty.project.Interface.IAuthorizationService;
import switchtwentytwenty.project.Interface.IAuthorizationServiceImpl;
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
public IPersonRepository ipersonrepository(){

return  new IPersonRepositoryImpl(); 
    }



@Bean
public IFamilyRepository ifamilyrepository(){

return  new IFamilyRepositoryImpl(); 
    }



@Bean
public IAccountRepository iaccountrepository(){

return  new IAccountRepositoryImpl(); 
    }



@Bean
public ILedgerRepository iledgerrepository(){

return  new ILedgerRepositoryImpl(); 
    }



@Bean
public ICategoryRepository icategoryrepository(){

return  new ICategoryRepositoryImpl(); 
    }



@Bean
public IAuthorizationService iauthorizationservice(){

return  new IAuthorizationServiceImpl(); 
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



}