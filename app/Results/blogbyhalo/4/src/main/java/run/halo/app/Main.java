package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.FileHandlers;
import run.halo.app.Interface.FileHandlersImpl;
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
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public FileHandlers filehandlers(){

return  new FileHandlersImpl(); 
    }



}