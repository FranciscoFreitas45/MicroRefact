package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
import Interface.SponsorService;
import Interface.SponsorServiceImpl;
import Interface.PathService;
import Interface.PathServiceImpl;
import Interface.SegmentService;
import Interface.SegmentServiceImpl;
import Interface.FinderService;
import Interface.FinderServiceImpl;
import Interface.SponsorshipService;
import Interface.SponsorshipServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.MessageService;
import Interface.MessageServiceImpl;
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
public BrotherhoodService brotherhoodservice(){

return  new BrotherhoodServiceImpl(); 
    }



@Bean
public SponsorService sponsorservice(){

return  new SponsorServiceImpl(); 
    }



@Bean
public PathService pathservice(){

return  new PathServiceImpl(); 
    }



@Bean
public SegmentService segmentservice(){

return  new SegmentServiceImpl(); 
    }



@Bean
public FinderService finderservice(){

return  new FinderServiceImpl(); 
    }



@Bean
public SponsorshipService sponsorshipservice(){

return  new SponsorshipServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



}