package app.qienuren;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import app.qienuren.Interface.TraineeService;
import app.qienuren.Interface.TraineeServiceImpl;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.FormulierServiceImpl;
import app.qienuren.Interface.TraineeRepository;
import app.qienuren.Interface.TraineeRepositoryImpl;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.EmailServiceImpl;
import app.qienuren.Interface.FormulierService;
import app.qienuren.Interface.FormulierServiceImpl;
import app.qienuren.Interface.RandomPasswordGenerator;
import app.qienuren.Interface.RandomPasswordGeneratorImpl;
import app.qienuren.Interface.EmailService;
import app.qienuren.Interface.EmailServiceImpl;
import app.qienuren.Interface.PersoonRepository;
import app.qienuren.Interface.PersoonRepositoryImpl;
import app.qienuren.Interface.FormulierRepository;
import app.qienuren.Interface.FormulierRepositoryImpl;
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
public FormulierService formulierservice(){

return  new FormulierServiceImpl(); 
    }



@Bean
public TraineeRepository traineerepository(){

return  new TraineeRepositoryImpl(); 
    }



@Bean
public EmailService emailservice(){

return  new EmailServiceImpl(); 
    }



@Bean
public FormulierService formulierservice(){

return  new FormulierServiceImpl(); 
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



@Bean
public FormulierRepository formulierrepository(){

return  new FormulierRepositoryImpl(); 
    }



}