package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.OrganRepository;
import Interface.OrganRepositoryImpl;
import Interface.MetadataRepository;
import Interface.MetadataRepositoryImpl;
import Interface.UserRepository;
import Interface.UserRepositoryImpl;
import Interface.OrgiSkillRelRepository;
import Interface.OrgiSkillRelRepositoryImpl;
import Interface.CacheInstance;
import Interface.CacheInstanceImpl;
import Interface.UserEventRepository;
import Interface.UserEventRepositoryImpl;
import Interface.ContactsRepository;
import Interface.ContactsRepositoryImpl;
import Interface.ServiceSummaryRepository;
import Interface.ServiceSummaryRepositoryImpl;
import Interface.TagRepository;
import Interface.TagRepositoryImpl;
import Interface.AgentUserContactsRepository;
import Interface.AgentUserContactsRepositoryImpl;
import Interface.SessionTypeRepository;
import Interface.SessionTypeRepositoryImpl;
import Interface.SysDicRepository;
import Interface.SysDicRepositoryImpl;
import Interface.ConsultInviteRepository;
import Interface.ConsultInviteRepositoryImpl;
import Interface.AttachmentRepository;
import Interface.AttachmentRepositoryImpl;
import Interface.SNSAccountRepository;
import Interface.SNSAccountRepositoryImpl;
import Interface.BlackListRepository;
import Interface.BlackListRepositoryImpl;
import Interface.QuickReplyRepository;
import Interface.QuickReplyRepositoryImpl;
import Interface.QuickTypeRepository;
import Interface.QuickTypeRepositoryImpl;
import Interface.StatusEventRepository;
import Interface.StatusEventRepositoryImpl;
import Interface.PbxHostRepository;
import Interface.PbxHostRepositoryImpl;
import Interface.ServiceAiRepository;
import Interface.ServiceAiRepositoryImpl;
import Interface.JobDetailRepository;
import Interface.JobDetailRepositoryImpl;
import Interface.Extention;
import Interface.ExtentionImpl;
import Interface.SNSAccount;
import Interface.SNSAccountImpl;
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
public OrganRepository organrepository(){

return  new OrganRepositoryImpl(); 
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
public OrgiSkillRelRepository orgiskillrelrepository(){

return  new OrgiSkillRelRepositoryImpl(); 
    }



@Bean
public CacheInstance cacheinstance(){

return  new CacheInstanceImpl(); 
    }



@Bean
public UserEventRepository usereventrepository(){

return  new UserEventRepositoryImpl(); 
    }



@Bean
public ContactsRepository contactsrepository(){

return  new ContactsRepositoryImpl(); 
    }



@Bean
public ServiceSummaryRepository servicesummaryrepository(){

return  new ServiceSummaryRepositoryImpl(); 
    }



@Bean
public TagRepository tagrepository(){

return  new TagRepositoryImpl(); 
    }



@Bean
public AgentUserContactsRepository agentusercontactsrepository(){

return  new AgentUserContactsRepositoryImpl(); 
    }



@Bean
public SessionTypeRepository sessiontyperepository(){

return  new SessionTypeRepositoryImpl(); 
    }



@Bean
public SysDicRepository sysdicrepository(){

return  new SysDicRepositoryImpl(); 
    }



@Bean
public ConsultInviteRepository consultinviterepository(){

return  new ConsultInviteRepositoryImpl(); 
    }



@Bean
public AttachmentRepository attachmentrepository(){

return  new AttachmentRepositoryImpl(); 
    }



@Bean
public SNSAccountRepository snsaccountrepository(){

return  new SNSAccountRepositoryImpl(); 
    }



@Bean
public BlackListRepository blacklistrepository(){

return  new BlackListRepositoryImpl(); 
    }



@Bean
public QuickReplyRepository quickreplyrepository(){

return  new QuickReplyRepositoryImpl(); 
    }



@Bean
public QuickTypeRepository quicktyperepository(){

return  new QuickTypeRepositoryImpl(); 
    }



@Bean
public StatusEventRepository statuseventrepository(){

return  new StatusEventRepositoryImpl(); 
    }



@Bean
public PbxHostRepository pbxhostrepository(){

return  new PbxHostRepositoryImpl(); 
    }



@Bean
public ServiceAiRepository serviceairepository(){

return  new ServiceAiRepositoryImpl(); 
    }



@Bean
public JobDetailRepository jobdetailrepository(){

return  new JobDetailRepositoryImpl(); 
    }



@Bean
public Extention extention(){

return  new ExtentionImpl(); 
    }



@Bean
public SNSAccount snsaccount(){

return  new SNSAccountImpl(); 
    }



@Bean
public TenantRepository tenantrepository(){

return  new TenantRepositoryImpl(); 
    }



}