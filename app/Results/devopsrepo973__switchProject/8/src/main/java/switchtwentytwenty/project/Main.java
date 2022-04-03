package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.IPersonRepository;
import switchtwentytwenty.project.Interface.IPersonRepositoryImpl;
import switchtwentytwenty.project.Interface.ILedgerRepository;
import switchtwentytwenty.project.Interface.ILedgerRepositoryImpl;
import switchtwentytwenty.project.Interface.ILedgerIDGenerator;
import switchtwentytwenty.project.Interface.ILedgerIDGeneratorImpl;
import switchtwentytwenty.project.Interface.IAuthorizationService;
import switchtwentytwenty.project.Interface.IAuthorizationServiceImpl;
import switchtwentytwenty.project.Interface.RegistrationDate;
import switchtwentytwenty.project.Interface.RegistrationDateImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.PersonName;
import switchtwentytwenty.project.Interface.PersonNameImpl;
import switchtwentytwenty.project.Interface.BirthDate;
import switchtwentytwenty.project.Interface.BirthDateImpl;
import switchtwentytwenty.project.Interface.VAT;
import switchtwentytwenty.project.Interface.VATImpl;
import switchtwentytwenty.project.Interface.Address;
import switchtwentytwenty.project.Interface.AddressImpl;
import switchtwentytwenty.project.Interface.TelephoneNumberList;
import switchtwentytwenty.project.Interface.TelephoneNumberListImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.RegistrationDate;
import switchtwentytwenty.project.Interface.RegistrationDateImpl;
import switchtwentytwenty.project.Interface.AccountID;
import switchtwentytwenty.project.Interface.AccountIDImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.Email;
import switchtwentytwenty.project.Interface.EmailImpl;
import switchtwentytwenty.project.Interface.Person;
import switchtwentytwenty.project.Interface.PersonImpl;
import switchtwentytwenty.project.Interface.Ledger;
import switchtwentytwenty.project.Interface.LedgerImpl;
import switchtwentytwenty.project.Interface.Ledger;
import switchtwentytwenty.project.Interface.LedgerImpl;
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
public ILedgerRepository iledgerrepository(){

return  new ILedgerRepositoryImpl(); 
    }



@Bean
public ILedgerIDGenerator iledgeridgenerator(){

return  new ILedgerIDGeneratorImpl(); 
    }



@Bean
public IAuthorizationService iauthorizationservice(){

return  new IAuthorizationServiceImpl(); 
    }



@Bean
public RegistrationDate registrationdate(){

return  new RegistrationDateImpl(); 
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
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public PersonName personname(){

return  new PersonNameImpl(); 
    }



@Bean
public BirthDate birthdate(){

return  new BirthDateImpl(); 
    }



@Bean
public VAT vat(){

return  new VATImpl(); 
    }



@Bean
public Address address(){

return  new AddressImpl(); 
    }



@Bean
public TelephoneNumberList telephonenumberlist(){

return  new TelephoneNumberListImpl(); 
    }



@Bean
public Email email(){

return  new EmailImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public Email email(){

return  new EmailImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public Email email(){

return  new EmailImpl(); 
    }



@Bean
public RegistrationDate registrationdate(){

return  new RegistrationDateImpl(); 
    }



@Bean
public AccountID accountid(){

return  new AccountIDImpl(); 
    }



@Bean
public Email email(){

return  new EmailImpl(); 
    }



@Bean
public Email email(){

return  new EmailImpl(); 
    }



@Bean
public Person person(){

return  new PersonImpl(); 
    }



@Bean
public Ledger ledger(){

return  new LedgerImpl(); 
    }



@Bean
public Ledger ledger(){

return  new LedgerImpl(); 
    }



}