package main;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import main.Interface.PostRepository;
import main.Interface.PostRepositoryImpl;
import main.Interface.UserRepository;
import main.Interface.UserRepositoryImpl;
import main.Interface.AuthConfiguration;
import main.Interface.AuthConfigurationImpl;
import main.Interface.TagRepository;
import main.Interface.TagRepositoryImpl;
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
public PostRepository postrepository(){

return  new PostRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public AuthConfiguration authconfiguration(){

return  new AuthConfigurationImpl(); 
    }



@Bean
public TagRepository tagrepository(){

return  new TagRepositoryImpl(); 
    }



}