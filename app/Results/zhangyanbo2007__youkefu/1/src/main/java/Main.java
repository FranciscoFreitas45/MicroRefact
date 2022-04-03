package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.TenantRepository;
import Interface.TenantRepositoryImpl;
import Interface.LogRepository;
import Interface.LogRepositoryImpl;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
import Interface.SecretRepository;
import Interface.SecretRepositoryImpl;
import Interface.ChatMessageRepository;
import Interface.ChatMessageRepositoryImpl;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.ReporterRepository;
import Interface.ReporterRepositoryImpl;
import Interface.AgentServiceRepository;
import Interface.AgentServiceRepositoryImpl;
import Interface.OrganRepository;
import Interface.OrganRepositoryImpl;
import Interface.SystemMessageRepository;
import Interface.SystemMessageRepositoryImpl;
import Interface.TemplateRepository;
import Interface.TemplateRepositoryImpl;
import Interface.AgentUserRepository;
import Interface.AgentUserRepositoryImpl;
import Interface.ServiceAiRepository;
import Interface.ServiceAiRepositoryImpl;
import Interface.OrgiSkillRelRepository;
import Interface.OrgiSkillRelRepositoryImpl;
import Interface.TagRepository;
import Interface.TagRepositoryImpl;
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
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



@Bean
public LogRepository logrepository(){

return  new LogRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public SecretRepository secretrepository(){

return  new SecretRepositoryImpl(); 
    }



@Bean
public ChatMessageRepository chatmessagerepository(){

return  new ChatMessageRepositoryImpl(); 
    }



@Bean
public MetadataRepository metadatarepository(){

return  new MetadataRepositoryImpl(); 
    }



@Bean
public ReporterRepository reporterrepository(){

return  new ReporterRepositoryImpl(); 
    }



@Bean
public AgentServiceRepository agentservicerepository(){

return  new AgentServiceRepositoryImpl(); 
    }



@Bean
public OrganRepository organrepository(){

return  new OrganRepositoryImpl(); 
    }



@Bean
public SystemMessageRepository systemmessagerepository(){

return  new SystemMessageRepositoryImpl(); 
    }



@Bean
public TemplateRepository templaterepository(){

return  new TemplateRepositoryImpl(); 
    }



@Bean
public AgentUserRepository agentuserrepository(){

return  new AgentUserRepositoryImpl(); 
    }



@Bean
public ServiceAiRepository serviceairepository(){

return  new ServiceAiRepositoryImpl(); 
    }



@Bean
public OrgiSkillRelRepository orgiskillrelrepository(){

return  new OrgiSkillRelRepositoryImpl(); 
    }



@Bean
public TagRepository tagrepository(){

return  new TagRepositoryImpl(); 
    }



}