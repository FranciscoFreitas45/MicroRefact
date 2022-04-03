package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.TemplateRepository;
import Interface.TemplateRepositoryImpl;
import Interface.SysDicRepository;
import Interface.SysDicRepositoryImpl;
import Interface.OrganRepository;
import Interface.OrganRepositoryImpl;
import Interface.DataSourceService;
import Interface.DataSourceServiceImpl;
import Interface.DSDataEvent;
import Interface.DSDataEventImpl;
import Interface.User;
import Interface.UserImpl;
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
public TemplateRepository templaterepository(){

return  new TemplateRepositoryImpl(); 
    }



@Bean
public SysDicRepository sysdicrepository(){

return  new SysDicRepositoryImpl(); 
    }



@Bean
public OrganRepository organrepository(){

return  new OrganRepositoryImpl(); 
    }



@Bean
public DataSourceService datasourceservice(){

return  new DataSourceServiceImpl(); 
    }



@Bean
public DSDataEvent dsdataevent(){

return  new DSDataEventImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}