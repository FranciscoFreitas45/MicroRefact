package es.us.isa.ideas.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.UserAccountServiceImpl;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationServiceImpl;
import es.us.isa.ideas.app.Interface.LoginService;
import es.us.isa.ideas.app.Interface.LoginServiceImpl;
import es.us.isa.ideas.app.Interface.UserAccountController;
import es.us.isa.ideas.app.Interface.UserAccountControllerImpl;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.UserAccountServiceImpl;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationService;
import es.us.isa.ideas.app.Interface.SocialNetworkConfigurationServiceImpl;
import es.us.isa.ideas.app.Interface.LoginService;
import es.us.isa.ideas.app.Interface.LoginServiceImpl;
import es.us.isa.ideas.app.Interface.CustomMailer;
import es.us.isa.ideas.app.Interface.CustomMailerImpl;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.UserAccountServiceImpl;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMailImpl;
import es.us.isa.ideas.app.Interface.TemplateMail;
import es.us.isa.ideas.app.Interface.TemplateMailImpl;
import es.us.isa.ideas.app.Interface.ResearcherRepository;
import es.us.isa.ideas.app.Interface.ResearcherRepositoryImpl;
import es.us.isa.ideas.app.Interface.UserAccountService;
import es.us.isa.ideas.app.Interface.UserAccountServiceImpl;
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
public UserAccountService useraccountservice(){

return  new UserAccountServiceImpl(); 
    }



@Bean
public SocialNetworkConfigurationService socialnetworkconfigurationservice(){

return  new SocialNetworkConfigurationServiceImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



@Bean
public UserAccountController useraccountcontroller(){

return  new UserAccountControllerImpl(); 
    }



@Bean
public UserAccountService useraccountservice(){

return  new UserAccountServiceImpl(); 
    }



@Bean
public SocialNetworkConfigurationService socialnetworkconfigurationservice(){

return  new SocialNetworkConfigurationServiceImpl(); 
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl(); 
    }



@Bean
public CustomMailer custommailer(){

return  new CustomMailerImpl(); 
    }



@Bean
public UserAccountService useraccountservice(){

return  new UserAccountServiceImpl(); 
    }



@Bean
public TemplateMail templatemail(){

return  new TemplateMailImpl(); 
    }



@Bean
public TemplateMail templatemail(){

return  new TemplateMailImpl(); 
    }



@Bean
public ResearcherRepository researcherrepository(){

return  new ResearcherRepositoryImpl(); 
    }



@Bean
public UserAccountService useraccountservice(){

return  new UserAccountServiceImpl(); 
    }



}