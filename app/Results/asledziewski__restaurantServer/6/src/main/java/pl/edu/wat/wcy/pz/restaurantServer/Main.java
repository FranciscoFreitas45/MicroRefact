package pl.edu.wat.wcy.pz.restaurantServer;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepositoryImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RoleRepository;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RoleRepositoryImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.JwtProvider;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.JwtProviderImpl;
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
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



@Bean
public JwtProvider jwtprovider(){

return  new JwtProviderImpl(); 
    }



}