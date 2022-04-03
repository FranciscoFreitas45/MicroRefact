package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.MetadataTable;
import Interface.MetadataTableImpl;
import Interface.ReporterRepository;
import Interface.ReporterRepositoryImpl;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.AgentServiceRepository;
import Interface.AgentServiceRepositoryImpl;
import Interface.TagRepository;
import Interface.TagRepositoryImpl;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
import Interface.Topic;
import Interface.TopicImpl;
import Interface.User;
import Interface.UserImpl;
import Interface.TableProperties;
import Interface.TablePropertiesImpl;
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
public MetadataTable metadatatable(){

return  new MetadataTableImpl(); 
    }



@Bean
public ReporterRepository reporterrepository(){

return  new ReporterRepositoryImpl(); 
    }



@Bean
public MetadataRepository metadatarepository(){

return  new MetadataRepositoryImpl(); 
    }



@Bean
public AgentServiceRepository agentservicerepository(){

return  new AgentServiceRepositoryImpl(); 
    }



@Bean
public TagRepository tagrepository(){

return  new TagRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public Topic topic(){

return  new TopicImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public TableProperties tableproperties(){

return  new TablePropertiesImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}