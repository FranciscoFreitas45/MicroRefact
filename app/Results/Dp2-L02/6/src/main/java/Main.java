package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.FinderRepository;
import Interface.FinderRepositoryImpl;
import Interface.BoxService;
import Interface.BoxServiceImpl;
import Interface.AreaService;
import Interface.AreaServiceImpl;
import Interface.MessageService;
import Interface.MessageServiceImpl;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.SponsorService;
import Interface.SponsorServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
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
public FinderRepository finderrepository(){

return  new FinderRepositoryImpl(); 
    }



@Bean
public BoxService boxservice(){

return  new BoxServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public BrotherhoodService brotherhoodservice(){

return  new BrotherhoodServiceImpl(); 
    }



@Bean
public ConfigurationService configurationservice(){

return  new ConfigurationServiceImpl(); 
    }



@Bean
public SponsorService sponsorservice(){

return  new SponsorServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public Area area(){

return  new AreaImpl(); 
    }



}