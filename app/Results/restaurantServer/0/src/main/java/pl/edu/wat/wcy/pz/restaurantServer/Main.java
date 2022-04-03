package pl.edu.wat.wcy.pz.restaurantServer;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserDetailsServiceImplImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserDetailsServiceImpl;
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
   public UserDetailsServiceImpl UserDetailsServiceImpl(){
      return  new UserDetailsServiceImplImpl();
   }



}