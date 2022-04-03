package app.qienuren;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.TraineeServiceImpl;
import app.qienuren.Interface.InterneMedewerkerService;
import app.qienuren.Interface.InterneMedewerkerServiceImpl;
import app.qienuren.Interface.PersoonService;
import app.qienuren.Interface.PersoonServiceImpl;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.FormulierServiceImpl;
import app.qienuren.Interface.TijdelijkeTraineeService;
import app.qienuren.Interface.TijdelijkeTraineeServiceImpl;
import app.qienuren.Interface.TijdelijkeInterneMedewerkerService;
import app.qienuren.Interface.TijdelijkeInterneMedewerkerServiceImpl;
import app.qienuren.Interface.InterneMedewerkerService;
import app.qienuren.Interface.InterneMedewerkerServiceImpl;
import app.qienuren.Interface.TraineeRepository;
import app.qienuren.Interface.TraineeRepositoryImpl;
import app.qienuren.Interface.RandomPasswordGenerator;
import app.qienuren.Interface.RandomPasswordGeneratorImpl;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.EmailServiceImpl;
import app.qienuren.Interface.PersoonRepository;
import app.qienuren.Interface.PersoonRepositoryImpl;
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
public TraineeService traineeservice(){

return  new TraineeServiceImpl(); 
    }



@Bean
public InterneMedewerkerService internemedewerkerservice(){

return  new InterneMedewerkerServiceImpl(); 
    }



@Bean
public PersoonService persoonservice(){

return  new PersoonServiceImpl(); 
    }



@Bean
public FormulierService formulierservice(){

return  new FormulierServiceImpl(); 
    }



@Bean
public TijdelijkeTraineeService tijdelijketraineeservice(){

return  new TijdelijkeTraineeServiceImpl(); 
    }



@Bean
public TijdelijkeInterneMedewerkerService tijdelijkeinternemedewerkerservice(){

return  new TijdelijkeInterneMedewerkerServiceImpl(); 
    }



@Bean
public InterneMedewerkerService internemedewerkerservice(){

return  new InterneMedewerkerServiceImpl(); 
    }



@Bean
public TraineeRepository traineerepository(){

return  new TraineeRepositoryImpl(); 
    }



@Bean
public RandomPasswordGenerator randompasswordgenerator(){

return  new RandomPasswordGeneratorImpl(); 
    }



@Bean
public EmailService emailservice(){

return  new EmailServiceImpl(); 
    }



@Bean
public PersoonRepository persoonrepository(){

return  new PersoonRepositoryImpl(); 
    }



}