package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.Client;
import Interface.ClientImpl;
import Interface.ClientRussianPost;
import Interface.ClientRussianPostImpl;
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
public Client client(){

return  new ClientImpl() 
    }



@Bean
public ClientRussianPost clientrussianpost(){

return  new ClientRussianPostImpl() 
    }



}