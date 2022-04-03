package lk.sliit.csse.procurementsystem;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import lk.sliit.csse.procurementsystem.Interface.SiteManager;
import lk.sliit.csse.procurementsystem.Interface.SiteManagerImpl;
import lk.sliit.csse.procurementsystem.Interface.SiteManagerRepository;
import lk.sliit.csse.procurementsystem.Interface.SiteManagerRepositoryImpl;
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
public SiteManager sitemanager(){

return  new SiteManagerImpl(); 
    }



@Bean
public SiteManagerRepository sitemanagerrepository(){

return  new SiteManagerRepositoryImpl(); 
    }



}