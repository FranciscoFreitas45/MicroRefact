package ink.champ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import ink.champ.Interface.RepositoryService;
import ink.champ.Interface.RepositoryServiceImpl;
import ink.champ.Interface.Team;
import ink.champ.Interface.TeamImpl;
import ink.champ.Interface.Team;
import ink.champ.Interface.TeamImpl;
import ink.champ.Interface.Team;
import ink.champ.Interface.TeamImpl;
import ink.champ.Interface.Sport;
import ink.champ.Interface.SportImpl;
import ink.champ.Interface.User;
import ink.champ.Interface.UserImpl;
import ink.champ.Interface.RepositoryService;
import ink.champ.Interface.RepositoryServiceImpl;
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
public RepositoryService repositoryservice(){

return  new RepositoryServiceImpl(); 
    }



@Bean
public Team team(){

return  new TeamImpl(); 
    }



@Bean
public Team team(){

return  new TeamImpl(); 
    }



@Bean
public Team team(){

return  new TeamImpl(); 
    }



@Bean
public Sport sport(){

return  new SportImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public RepositoryService repositoryservice(){

return  new RepositoryServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}