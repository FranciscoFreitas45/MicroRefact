package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
import Interface.StatusEventRepository;
import Interface.StatusEventRepositoryImpl;
import Interface.WorkOrdersRepository;
import Interface.WorkOrdersRepositoryImpl;
import Interface.AgentServiceRepository;
import Interface.AgentServiceRepositoryImpl;
import Interface.QualityMissionHisRepository;
import Interface.QualityMissionHisRepositoryImpl;
import Interface.CallOutTaskRepository;
import Interface.CallOutTaskRepositoryImpl;
import Interface.OrganRepository;
import Interface.OrganRepositoryImpl;
import Interface.MetadataTable;
import Interface.MetadataTableImpl;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.DatabaseRepository;
import Interface.DatabaseRepositoryImpl;
import Interface.FormFilterRepository;
import Interface.FormFilterRepositoryImpl;
import Interface.FormFilter;
import Interface.FormFilterImpl;
import Interface.CallAgent;
import Interface.CallAgentImpl;
import Interface.CallOutTask;
import Interface.CallOutTaskImpl;
import Interface.CallOutFilter;
import Interface.CallOutFilterImpl;
import Interface.CallOutFilterRepository;
import Interface.CallOutFilterRepositoryImpl;
import Interface.User;
import Interface.UserImpl;
import Interface.Organ;
import Interface.OrganImpl;
import Interface.ContactsRepository;
import Interface.ContactsRepositoryImpl;
import Interface.TableProperties;
import Interface.TablePropertiesImpl;
import Interface.QuickReplyRepository;
import Interface.QuickReplyRepositoryImpl;
import Interface.EntCustomerRepository;
import Interface.EntCustomerRepositoryImpl;
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public StatusEventRepository statuseventrepository(){

return  new StatusEventRepositoryImpl(); 
    }



@Bean
public WorkOrdersRepository workordersrepository(){

return  new WorkOrdersRepositoryImpl(); 
    }



@Bean
public AgentServiceRepository agentservicerepository(){

return  new AgentServiceRepositoryImpl(); 
    }



@Bean
public QualityMissionHisRepository qualitymissionhisrepository(){

return  new QualityMissionHisRepositoryImpl(); 
    }



@Bean
public CallOutTaskRepository callouttaskrepository(){

return  new CallOutTaskRepositoryImpl(); 
    }



@Bean
public OrganRepository organrepository(){

return  new OrganRepositoryImpl(); 
    }



@Bean
public MetadataTable metadatatable(){

return  new MetadataTableImpl(); 
    }



@Bean
public MetadataRepository metadatarepository(){

return  new MetadataRepositoryImpl(); 
    }



@Bean
public DatabaseRepository databaserepository(){

return  new DatabaseRepositoryImpl(); 
    }



@Bean
public FormFilterRepository formfilterrepository(){

return  new FormFilterRepositoryImpl(); 
    }



@Bean
public FormFilter formfilter(){

return  new FormFilterImpl(); 
    }



@Bean
public CallAgent callagent(){

return  new CallAgentImpl(); 
    }



@Bean
public CallOutTask callouttask(){

return  new CallOutTaskImpl(); 
    }



@Bean
public CallOutFilter calloutfilter(){

return  new CallOutFilterImpl(); 
    }



@Bean
public CallOutFilterRepository calloutfilterrepository(){

return  new CallOutFilterRepositoryImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public Organ organ(){

return  new OrganImpl(); 
    }



@Bean
public ContactsRepository contactsrepository(){

return  new ContactsRepositoryImpl(); 
    }



@Bean
public TableProperties tableproperties(){

return  new TablePropertiesImpl(); 
    }



@Bean
public QuickReplyRepository quickreplyrepository(){

return  new QuickReplyRepositoryImpl(); 
    }



@Bean
public EntCustomerRepository entcustomerrepository(){

return  new EntCustomerRepositoryImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}