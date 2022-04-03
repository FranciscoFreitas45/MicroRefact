package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.ParadeService;
import Interface.ParadeServiceImpl;
import Interface.BrotherhoodService;
import Interface.BrotherhoodServiceImpl;
import Interface.ChapterService;
import Interface.ChapterServiceImpl;
import Interface.ActorService;
import Interface.ActorServiceImpl;
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
public ParadeService paradeservice(){

return  new ParadeServiceImpl(); 
    }



@Bean
public BrotherhoodService brotherhoodservice(){

return  new BrotherhoodServiceImpl(); 
    }



@Bean
public ChapterService chapterservice(){

return  new ChapterServiceImpl(); 
    }



@Bean
public ActorService actorservice(){

return  new ActorServiceImpl(); 
    }



}