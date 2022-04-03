package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.SysDicRepository;
import Interface.SysDicRepositoryImpl;
import Interface.AgentUserRepository;
import Interface.AgentUserRepositoryImpl;
import Interface.AreaTypeRepository;
import Interface.AreaTypeRepositoryImpl;
import Interface.ChatMessageRepository;
import Interface.ChatMessageRepositoryImpl;
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
public SysDicRepository sysdicrepository(){

return  new SysDicRepositoryImpl(); 
    }



@Bean
public AgentUserRepository agentuserrepository(){

return  new AgentUserRepositoryImpl(); 
    }



@Bean
public AreaTypeRepository areatyperepository(){

return  new AreaTypeRepositoryImpl(); 
    }



@Bean
public ChatMessageRepository chatmessagerepository(){

return  new ChatMessageRepositoryImpl(); 
    }



}