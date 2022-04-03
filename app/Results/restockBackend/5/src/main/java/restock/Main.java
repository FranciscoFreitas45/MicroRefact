package restock;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import restock.Interface.ProveidorRepository;
import restock.Interface.ProveidorRepositoryImpl;
import restock.Interface.ProducteRepository;
import restock.Interface.ProducteRepositoryImpl;
import restock.Interface.BotigaRepository;
import restock.Interface.BotigaRepositoryImpl;
import restock.Interface.Botiga;
import restock.Interface.BotigaImpl;
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
public ProveidorRepository proveidorrepository(){

return  new ProveidorRepositoryImpl() 
    }



@Bean
public ProducteRepository producterepository(){

return  new ProducteRepositoryImpl() 
    }



@Bean
public BotigaRepository botigarepository(){

return  new BotigaRepositoryImpl() 
    }



@Bean
public Botiga botiga(){

return  new BotigaImpl() 
    }



}