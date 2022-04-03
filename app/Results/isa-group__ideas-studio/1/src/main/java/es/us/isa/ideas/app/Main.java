package es.us.isa.ideas.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.us.isa.ideas.app.Interface.ConfirmationService;
import es.us.isa.ideas.app.Interface.ConfirmationServiceImpl;
import es.us.isa.ideas.app.Interface.ResearcherService;
import es.us.isa.ideas.app.Interface.ResearcherServiceImpl;
import es.us.isa.ideas.app.Interface.WorkspaceRepository;
import es.us.isa.ideas.app.Interface.WorkspaceRepositoryImpl;
import es.us.isa.ideas.app.Interface.CustomMailer;
import es.us.isa.ideas.app.Interface.CustomMailerImpl;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMailImpl;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMailImpl;
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
public ConfirmationService confirmationservice(){

return  new ConfirmationServiceImpl(); 
    }



@Bean
public ResearcherService researcherservice(){

return  new ResearcherServiceImpl(); 
    }



@Bean
public WorkspaceRepository workspacerepository(){

return  new WorkspaceRepositoryImpl(); 
    }



@Bean
public CustomMailer custommailer(){

return  new CustomMailerImpl(); 
    }



@Bean
public TemplateMail templatemail(){

return  new TemplateMailImpl(); 
    }



@Bean
public TemplateMail templatemail(){

return  new TemplateMailImpl(); 
    }



}