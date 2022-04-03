package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
import Interface.FloatService;
import Interface.FloatServiceImpl;
import Interface.SponsorshipService;
import Interface.SponsorshipServiceImpl;
import Interface.ChapterService;
import Interface.ChapterServiceImpl;
import Interface.SegmentService;
import Interface.SegmentServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
import Interface.AdminService;
import Interface.AdminServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.SponsorService;
import Interface.SponsorServiceImpl;
import Interface.Brotherhood;
import Interface.BrotherhoodImpl;
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
public FloatService floatservice(){

return  new FloatServiceImpl(); 
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
public ActorService actorservice(){

return  new ActorServiceImpl(); 
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



@Bean
public Brotherhood brotherhood(){

return  new BrotherhoodImpl(); 
    }



}