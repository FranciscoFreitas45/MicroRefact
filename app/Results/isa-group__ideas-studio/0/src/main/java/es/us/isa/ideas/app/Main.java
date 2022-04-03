package es.us.isa.ideas.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.us.isa.ideas.app.Interface.WorkspaceService;
import es.us.isa.ideas.app.Interface.WorkspaceServiceImpl;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.UserAccountServiceImpl;
import es.us.isa.ideas.app.Interface.ResearcherService;
import es.us.isa.ideas.app.Interface.ResearcherServiceImpl;
import es.us.isa.ideas.app.Interface.WorkspaceService;
import es.us.isa.ideas.app.Interface.WorkspaceServiceImpl;
import es.us.isa.ideas.app.Interface.CustomMailer;
import es.us.isa.ideas.app.Interface.CustomMailerImpl;
import es.us.isa.ideas.app.Interface.LoginService;
import es.us.isa.ideas.app.Interface.LoginServiceImpl;
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
public WorkspaceService workspaceservice(){

return  new WorkspaceServiceImpl(); 
    }



@Bean
public UserAccountService useraccountservice(){

return  new UserAccountServiceImpl(); 
    }



@Bean
public ResearcherService researcherservice(){

return  new ResearcherServiceImpl(); 
    }



@Bean
public WorkspaceService workspaceservice(){

return  new WorkspaceServiceImpl(); 
    }



@Bean
public CustomMailer custommailer(){

return  new CustomMailerImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



}