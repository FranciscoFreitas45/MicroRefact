package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.BoxService;
import Interface.BoxServiceImpl;
import Interface.MessageService;
import Interface.MessageServiceImpl;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.RequestService;
import Interface.RequestServiceImpl;
import Interface.EnrolmentService;
import Interface.EnrolmentServiceImpl;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.SponsorshipService;
import Interface.SponsorshipServiceImpl;
import Interface.ChapterService;
import Interface.ChapterServiceImpl;
import Interface.SponsorService;
import Interface.SponsorServiceImpl;
import Interface.Area;
import Interface.AreaImpl;
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
public BoxService boxservice(){

return  new BoxServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public ParadeService paradeservice(){

return  new ParadeServiceImpl(); 
    }



@Bean
public RequestService requestservice(){

return  new RequestServiceImpl(); 
    }



@Bean
public EnrolmentService enrolmentservice(){

return  new EnrolmentServiceImpl(); 
    }



@Bean
public BrotherhoodService brotherhoodservice(){

return  new BrotherhoodServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public SponsorshipService sponsorshipservice(){

return  new SponsorshipServiceImpl(); 
    }



@Bean
public ChapterService chapterservice(){

return  new ChapterServiceImpl(); 
    }



@Bean
public SponsorService sponsorservice(){

return  new SponsorServiceImpl(); 
    }



@Bean
public Area area(){

return  new AreaImpl(); 
    }



}