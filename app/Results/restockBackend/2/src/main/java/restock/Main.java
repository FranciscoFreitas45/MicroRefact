package restock;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import restock.Interface.UsuariRepository;
import restock.Interface.UsuariRepositoryImpl;
import restock.Interface.ComandaBusiness;
import restock.Interface.ComandaBusinessImpl;
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
public UsuariRepository usuarirepository(){

return  new UsuariRepositoryImpl() 
    }



@Bean
public ComandaBusiness comandabusiness(){

return  new ComandaBusinessImpl() 
    }



}