package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.CgFormHeadEntity;
import Interface.CgFormHeadEntityImpl;
import Interface.SystemService;
import Interface.SystemServiceImpl;
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
public CgFormHeadEntity cgformheadentity(){

return  new CgFormHeadEntityImpl(); 
    }



@Bean
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



}