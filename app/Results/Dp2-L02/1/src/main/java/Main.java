package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.BoxService;
import Interface.BoxServiceImpl;
import Interface.MessageService;
import Interface.MessageServiceImpl;
import Interface.SocialProfileService;
import Interface.SocialProfileServiceImpl;
import Interface.EnrolmentService;
import Interface.EnrolmentServiceImpl;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.FloatService;
import Interface.FloatServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.RequestService;
import Interface.RequestServiceImpl;
import Interface.SponsorshipService;
import Interface.SponsorshipServiceImpl;
import Interface.ChapterService;
import Interface.ChapterServiceImpl;
import Interface.SegmentService;
import Interface.SegmentServiceImpl;
import Interface.AdminService;
import Interface.AdminServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.SponsorService;
import Interface.SponsorServiceImpl;
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
public BoxService boxservice(){

return  new BoxServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public SocialProfileService socialprofileservice(){

return  new SocialProfileServiceImpl(); 
    }



@Bean
public EnrolmentService enrolmentservice(){

return  new EnrolmentServiceImpl(); 
    }



@Bean
public ParadeService paradeservice(){

return  new ParadeServiceImpl(); 
    }



@Bean
public FloatService floatservice(){

return  new FloatServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



@Bean
public RequestService requestservice(){

return  new RequestServiceImpl(); 
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
public SegmentService segmentservice(){

return  new SegmentServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public ConfigurationService configurationservice(){

return  new ConfigurationServiceImpl(); 
    }



@Bean
public SponsorService sponsorservice(){

return  new SponsorServiceImpl(); 
    }



}