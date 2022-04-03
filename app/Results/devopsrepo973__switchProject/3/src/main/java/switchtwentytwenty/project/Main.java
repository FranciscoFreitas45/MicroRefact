package switchtwentytwenty.project;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.FamilyIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.FamilyIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
import switchtwentytwenty.project.Interface.FamilyID;
import switchtwentytwenty.project.Interface.FamilyIDImpl;
import switchtwentytwenty.project.Interface.LedgerID;
import switchtwentytwenty.project.Interface.LedgerIDImpl;
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
public FamilyID familyid(){

return  new FamilyIDImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public FamilyID familyid(){

return  new FamilyIDImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public FamilyID familyid(){

return  new FamilyIDImpl(); 
    }



@Bean
public LedgerID ledgerid(){

return  new LedgerIDImpl(); 
    }



@Bean
public Ledger ledger(){

return  new LedgerImpl(); 
    }



}