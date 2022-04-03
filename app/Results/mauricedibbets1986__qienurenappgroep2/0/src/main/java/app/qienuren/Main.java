package app.qienuren;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.FormulierServiceImpl;
import app.qienuren.Interface.BedrijfRepository;
import app.qienuren.Interface.BedrijfRepositoryImpl;
import app.qienuren.Interface.KlantContactPersoonRepository;
import app.qienuren.Interface.KlantContactPersoonRepositoryImpl;
import app.qienuren.Interface.FormulierRepository;
import app.qienuren.Interface.FormulierRepositoryImpl;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.EmailServiceImpl;
import app.qienuren.Interface.MedewerkerService;
import app.qienuren.Interface.MedewerkerServiceImpl;
import app.qienuren.Interface.KlantContactPersoonRepository;
import app.qienuren.Interface.KlantContactPersoonRepositoryImpl;
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
public FormulierService formulierservice(){

return  new FormulierServiceImpl(); 
    }



@Bean
public BedrijfRepository bedrijfrepository(){

return  new BedrijfRepositoryImpl(); 
    }



@Bean
public KlantContactPersoonRepository klantcontactpersoonrepository(){

return  new KlantContactPersoonRepositoryImpl(); 
    }



@Bean
public FormulierRepository formulierrepository(){

return  new FormulierRepositoryImpl(); 
    }



@Bean
public EmailService emailservice(){

return  new EmailServiceImpl(); 
    }



@Bean
public MedewerkerService medewerkerservice(){

return  new MedewerkerServiceImpl(); 
    }



@Bean
public KlantContactPersoonRepository klantcontactpersoonrepository(){

return  new KlantContactPersoonRepositoryImpl(); 
    }



}