package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
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
public ThemeService themeservice(){

return  new ThemeServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



}