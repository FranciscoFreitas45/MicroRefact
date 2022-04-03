package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.MessageService;
import Interface.MessageServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.BoxService;
import Interface.BoxServiceImpl;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.RequestService;
import Interface.RequestServiceImpl;
import Interface.AreaService;
import Interface.AreaServiceImpl;
import Interface.PositionService;
import Interface.PositionServiceImpl;
import Interface.FinderService;
import Interface.FinderServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.HistoryService;
import Interface.HistoryServiceImpl;
import Interface.SponsorshipService;
import Interface.SponsorshipServiceImpl;
import Interface.FinderRepository;
import Interface.FinderRepositoryImpl;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
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
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public BoxService boxservice(){

return  new BoxServiceImpl(); 
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
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public PositionService positionservice(){

return  new PositionServiceImpl(); 
    }



@Bean
public FinderService finderservice(){

return  new FinderServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public HistoryService historyservice(){

return  new HistoryServiceImpl(); 
    }



@Bean
public SponsorshipService sponsorshipservice(){

return  new SponsorshipServiceImpl(); 
    }



@Bean
public FinderRepository finderrepository(){

return  new FinderRepositoryImpl(); 
    }



@Bean
public ConfigurationService configurationservice(){

return  new ConfigurationServiceImpl(); 
    }



}