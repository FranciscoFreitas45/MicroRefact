package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.AgentServiceRepository;
import Interface.AgentServiceRepositoryImpl;
import Interface.WorkOrdersRepository;
import Interface.WorkOrdersRepositoryImpl;
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
public AgentServiceRepository agentservicerepository(){

return  new AgentServiceRepositoryImpl(); 
    }



@Bean
public WorkOrdersRepository workordersrepository(){

return  new WorkOrdersRepositoryImpl(); 
    }



}