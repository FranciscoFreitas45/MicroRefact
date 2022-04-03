package upce.semprace.eshop;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.Interface.UzivatelRepositoryImpl;
import upce.semprace.eshop.Interface.RoleRepository;
import upce.semprace.eshop.Interface.RoleRepositoryImpl;
import upce.semprace.eshop.Interface.JwtProvider;
import upce.semprace.eshop.Interface.JwtProviderImpl;
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
public UzivatelRepository uzivatelrepository(){

return  new UzivatelRepositoryImpl(); 
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