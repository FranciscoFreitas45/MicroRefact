package pl.edu.wat.wcy.pz.restaurantServer;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserService;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserServiceImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.MailService;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.MailServiceImpl;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RTableService;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.RTableServiceImpl;
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
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public RTableService rtableservice(){

return  new RTableServiceImpl(); 
    }



}