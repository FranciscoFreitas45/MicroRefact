package ink.champ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import ink.champ.Interface.User;
import ink.champ.Interface.UserImpl;
import ink.champ.Interface.AppService;
import ink.champ.Interface.AppServiceImpl;
import ink.champ.Interface.RepositoryService;
import ink.champ.Interface.RepositoryServiceImpl;
import ink.champ.Interface.Player;
import ink.champ.Interface.PlayerImpl;
import ink.champ.Interface.User;
import ink.champ.Interface.UserImpl;
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
public User user(){

return  new UserImpl(); 
    }



@Bean
public AppService appservice(){

return  new AppServiceImpl(); 
    }



@Bean
public RepositoryService repositoryservice(){

return  new RepositoryServiceImpl(); 
    }



@Bean
public Player player(){

return  new PlayerImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}