package com.ushahidi.swiftriver.core;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ushahidi.swiftriver.core.Interface.FormDao;
import com.ushahidi.swiftriver.core.Interface.FormDaoImpl;
import com.ushahidi.swiftriver.core.Interface.FormFieldDao;
import com.ushahidi.swiftriver.core.Interface.FormFieldDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RiverService;
import com.ushahidi.swiftriver.core.Interface.RiverServiceImpl;
import com.ushahidi.swiftriver.core.Interface.BucketService;
import com.ushahidi.swiftriver.core.Interface.BucketServiceImpl;
import com.ushahidi.swiftriver.core.Interface.UserDao;
import com.ushahidi.swiftriver.core.Interface.UserDaoImpl;
import com.ushahidi.swiftriver.core.Interface.ActivityDao;
import com.ushahidi.swiftriver.core.Interface.ActivityDaoImpl;
import com.ushahidi.swiftriver.core.Interface.UserTokenDao;
import com.ushahidi.swiftriver.core.Interface.UserTokenDaoImpl;
import com.ushahidi.swiftriver.core.Interface.ClientDao;
import com.ushahidi.swiftriver.core.Interface.ClientDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RoleDao;
import com.ushahidi.swiftriver.core.Interface.RoleDaoImpl;
import com.ushahidi.swiftriver.core.Interface.CrowdmapIDClient;
import com.ushahidi.swiftriver.core.Interface.CrowdmapIDClientImpl;
import com.ushahidi.swiftriver.core.Interface.EmailHelper;
import com.ushahidi.swiftriver.core.Interface.EmailHelperImpl;
import com.ushahidi.swiftriver.core.Interface.DropIndexService;
import com.ushahidi.swiftriver.core.Interface.DropIndexServiceImpl;
import com.ushahidi.swiftriver.core.Interface.BucketService;
import com.ushahidi.swiftriver.core.Interface.BucketServiceImpl;
import com.ushahidi.swiftriver.core.Interface.RiverService;
import com.ushahidi.swiftriver.core.Interface.RiverServiceImpl;
import com.ushahidi.swiftriver.core.Interface.AccountDTO;
import com.ushahidi.swiftriver.core.Interface.AccountDTOImpl;
import com.ushahidi.swiftriver.core.Interface.AccountDTO;
import com.ushahidi.swiftriver.core.Interface.AccountDTOImpl;
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
public FormDao formdao(){

return  new FormDaoImpl(); 
    }



@Bean
public FormFieldDao formfielddao(){

return  new FormFieldDaoImpl(); 
    }



@Bean
public RiverService riverservice(){

return  new RiverServiceImpl(); 
    }



@Bean
public BucketService bucketservice(){

return  new BucketServiceImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public ActivityDao activitydao(){

return  new ActivityDaoImpl(); 
    }



@Bean
public UserTokenDao usertokendao(){

return  new UserTokenDaoImpl(); 
    }



@Bean
public ClientDao clientdao(){

return  new ClientDaoImpl(); 
    }



@Bean
public RoleDao roledao(){

return  new RoleDaoImpl(); 
    }



@Bean
public CrowdmapIDClient crowdmapidclient(){

return  new CrowdmapIDClientImpl(); 
    }



@Bean
public EmailHelper emailhelper(){

return  new EmailHelperImpl(); 
    }



@Bean
public DropIndexService dropindexservice(){

return  new DropIndexServiceImpl(); 
    }



@Bean
public BucketService bucketservice(){

return  new BucketServiceImpl(); 
    }



@Bean
public RiverService riverservice(){

return  new RiverServiceImpl(); 
    }



@Bean
public AccountDTO accountdto(){

return  new AccountDTOImpl(); 
    }



@Bean
public AccountDTO accountdto(){

return  new AccountDTOImpl(); 
    }



}