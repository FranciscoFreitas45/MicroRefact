package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.AdminService;
import Interface.AdminServiceImpl;
import Interface.MessageService;
import Interface.MessageServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.BoxService;
import Interface.BoxServiceImpl;
import Interface.SocialProfileService;
import Interface.SocialProfileServiceImpl;
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
public ConfigurationService configurationservice(){

return  new ConfigurationServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public ParadeService paradeservice(){

return  new ParadeServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public BoxService boxservice(){

return  new BoxServiceImpl(); 
    }



@Bean
public SocialProfileService socialprofileservice(){

return  new SocialProfileServiceImpl(); 
    }



}