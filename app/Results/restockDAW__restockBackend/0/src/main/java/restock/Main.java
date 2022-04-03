package restock;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import restock.Interface.BotigaBusiness;
import restock.Interface.BotigaBusinessImpl;
import restock.Interface.ProveidorBusiness;
import restock.Interface.ProveidorBusinessImpl;
import restock.Interface.ProducteBusiness;
import restock.Interface.ProducteBusinessImpl;
import restock.Interface.BotigaBusiness;
import restock.Interface.BotigaBusinessImpl;
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
public BotigaBusiness botigabusiness(){

return  new BotigaBusinessImpl(); 
    }



@Bean
public ProveidorBusiness proveidorbusiness(){

return  new ProveidorBusinessImpl(); 
    }



@Bean
public ProducteBusiness productebusiness(){

return  new ProducteBusinessImpl(); 
    }



@Bean
public BotigaBusiness botigabusiness(){

return  new BotigaBusinessImpl(); 
    }



}