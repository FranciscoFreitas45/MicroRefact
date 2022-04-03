package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.CgFormFieldServiceI;
import Interface.CgFormFieldServiceIImpl;
import Interface.ICommonDao;
import Interface.ICommonDaoImpl;
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
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public CgFormFieldServiceI cgformfieldservicei(){

return  new CgFormFieldServiceIImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



}