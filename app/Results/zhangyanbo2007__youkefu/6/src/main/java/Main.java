package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
import Interface.OnlineUserRepository;
import Interface.OnlineUserRepositoryImpl;
import Interface.UserEventRepository;
import Interface.UserEventRepositoryImpl;
import Interface.TenantRepository;
import Interface.TenantRepositoryImpl;
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
public MetadataRepository metadatarepository(){

return  new MetadataRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public OnlineUserRepository onlineuserrepository(){

return  new OnlineUserRepositoryImpl(); 
    }



@Bean
public UserEventRepository usereventrepository(){

return  new UserEventRepositoryImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}