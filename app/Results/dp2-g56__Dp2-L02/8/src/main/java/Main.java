package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ConfigurationService;
import Interface.ConfigurationServiceImpl;
import Interface.MemberService;
import Interface.MemberServiceImpl;
import Interface.AdminService;
import Interface.AdminServiceImpl;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public BrotherhoodService brotherhoodservice(){

return  new BrotherhoodServiceImpl(); 
    }



@Bean
public SocialProfileService socialprofileservice(){

return  new SocialProfileServiceImpl(); 
    }



}