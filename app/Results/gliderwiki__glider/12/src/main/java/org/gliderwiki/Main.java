package org.gliderwiki;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.WikiService;
import org.gliderwiki.Interface.WikiServiceImpl;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.CommonServiceImpl;
import org.gliderwiki.Interface.UserActionService;
import org.gliderwiki.Interface.UserActionServiceImpl;
import org.gliderwiki.Interface.RequestManager;
import org.gliderwiki.Interface.RequestManagerImpl;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.EntityServiceImpl;
import org.gliderwiki.Interface.AdminUserService;
import org.gliderwiki.Interface.AdminUserServiceImpl;
import org.gliderwiki.Interface.AdminGroupService;
import org.gliderwiki.Interface.AdminGroupServiceImpl;
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
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public WikiService wikiservice(){

return  new WikiServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



@Bean
public UserActionService useractionservice(){

return  new UserActionServiceImpl(); 
    }



@Bean
public RequestManager requestmanager(){

return  new RequestManagerImpl(); 
    }



@Bean
public EntityService entityservice(){

return  new EntityServiceImpl(); 
    }



@Bean
public AdminUserService adminuserservice(){

return  new AdminUserServiceImpl(); 
    }



@Bean
public AdminGroupService admingroupservice(){

return  new AdminGroupServiceImpl(); 
    }



}