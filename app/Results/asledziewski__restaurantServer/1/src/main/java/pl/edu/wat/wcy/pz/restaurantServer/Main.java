package pl.edu.wat.wcy.pz.restaurantServer;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepositoryImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepositoryImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.MailService;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.MailServiceImpl;
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



}