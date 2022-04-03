package upce.semprace.eshop;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import upce.semprace.eshop.Interface.UzivatelRepository;
import upce.semprace.eshop.Interface.UzivatelRepositoryImpl;
import upce.semprace.eshop.Interface.ProduktRepository;
import upce.semprace.eshop.Interface.ProduktRepositoryImpl;
import upce.semprace.eshop.Interface.NakupRepository;
import upce.semprace.eshop.Interface.NakupRepositoryImpl;
import upce.semprace.eshop.Interface.NakoupenaPolozkaRepository;
import upce.semprace.eshop.Interface.NakoupenaPolozkaRepositoryImpl;
import upce.semprace.eshop.Interface.DopravaRepository;
import upce.semprace.eshop.Interface.DopravaRepositoryImpl;
import upce.semprace.eshop.Interface.PlatbaRepository;
import upce.semprace.eshop.Interface.PlatbaRepositoryImpl;
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
public ProduktRepository produktrepository(){

return  new ProduktRepositoryImpl(); 
    }



@Bean
public NakupRepository nakuprepository(){

return  new NakupRepositoryImpl(); 
    }



@Bean
public NakoupenaPolozkaRepository nakoupenapolozkarepository(){

return  new NakoupenaPolozkaRepositoryImpl(); 
    }



@Bean
public DopravaRepository dopravarepository(){

return  new DopravaRepositoryImpl(); 
    }



@Bean
public PlatbaRepository platbarepository(){

return  new PlatbaRepositoryImpl(); 
    }



}