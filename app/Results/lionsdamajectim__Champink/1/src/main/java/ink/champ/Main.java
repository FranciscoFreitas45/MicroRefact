package ink.champ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import ink.champ.Interface.ChampRepository;
import ink.champ.Interface.ChampRepositoryImpl;
import ink.champ.Interface.TeamRepository;
import ink.champ.Interface.TeamRepositoryImpl;
import ink.champ.Interface.PlayerRepository;
import ink.champ.Interface.PlayerRepositoryImpl;
import ink.champ.Interface.UserRepository;
import ink.champ.Interface.UserRepositoryImpl;
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
public ChampRepository champrepository(){

return  new ChampRepositoryImpl(); 
    }



@Bean
public TeamRepository teamrepository(){

return  new TeamRepositoryImpl(); 
    }



@Bean
public PlayerRepository playerrepository(){

return  new PlayerRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



}