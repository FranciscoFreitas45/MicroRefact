package es.us.isa.ideas.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.us.isa.ideas.app.Interface.ResearcherService;
import es.us.isa.ideas.app.Interface.ResearcherServiceImpl;
import es.us.isa.ideas.app.Interface.StudioConfiguration;
import es.us.isa.ideas.app.Interface.StudioConfigurationImpl;
import es.us.isa.ideas.app.Interface.UserAccountRepository;
import es.us.isa.ideas.app.Interface.UserAccountRepositoryImpl;
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
public ResearcherService researcherservice(){

return  new ResearcherServiceImpl(); 
    }



@Bean
public StudioConfiguration studioconfiguration(){

return  new StudioConfigurationImpl(); 
    }



@Bean
public UserAccountRepository useraccountrepository(){

return  new UserAccountRepositoryImpl(); 
    }



}