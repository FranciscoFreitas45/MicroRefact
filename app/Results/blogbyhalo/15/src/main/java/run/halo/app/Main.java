package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.OptionRepository;
import run.halo.app.Interface.OptionRepositoryImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
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
public OptionRepository optionrepository(){

return  new OptionRepositoryImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



}