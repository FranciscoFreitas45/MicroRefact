package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.AreaTypeRepository;
import Interface.AreaTypeRepositoryImpl;
import Interface.SysDicRepository;
import Interface.SysDicRepositoryImpl;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.ReporterRepository;
import Interface.ReporterRepositoryImpl;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
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
public AreaTypeRepository areatyperepository(){

return  new AreaTypeRepositoryImpl(); 
    }



@Bean
public SysDicRepository sysdicrepository(){

return  new SysDicRepositoryImpl(); 
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}