package app.qienuren;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import app.qienuren.Interface.MedewerkerRepository;
import app.qienuren.Interface.MedewerkerRepositoryImpl;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.EmailServiceImpl;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.TraineeServiceImpl;
import app.qienuren.Interface.KlantContactPersoonService;
import app.qienuren.Interface.KlantContactPersoonServiceImpl;
import app.qienuren.Interface.PersoonService;
import app.qienuren.Interface.PersoonServiceImpl;
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
public MedewerkerRepository medewerkerrepository(){

return  new MedewerkerRepositoryImpl(); 
    }



@Bean
public EmailService emailservice(){

return  new EmailServiceImpl(); 
    }



@Bean
public TraineeService traineeservice(){

return  new TraineeServiceImpl(); 
    }



@Bean
public KlantContactPersoonService klantcontactpersoonservice(){

return  new KlantContactPersoonServiceImpl(); 
    }



@Bean
public PersoonService persoonservice(){

return  new PersoonServiceImpl(); 
    }



}