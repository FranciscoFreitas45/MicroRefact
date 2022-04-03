package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.GroupService;
import org.live.Interface.GroupServiceImpl;
import org.live.Interface.RoleRepository;
import org.live.Interface.RoleRepositoryImpl;
import org.live.Interface.GroupRepository;
import org.live.Interface.GroupRepositoryImpl;
import org.live.Interface.PermissionRepository;
import org.live.Interface.PermissionRepositoryImpl;
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
public GroupService groupservice(){

return  new GroupServiceImpl(); 
    }



@Bean
public RoleRepository rolerepository(){

return  new RoleRepositoryImpl(); 
    }



@Bean
public GroupRepository grouprepository(){

return  new GroupRepositoryImpl(); 
    }



@Bean
public PermissionRepository permissionrepository(){

return  new PermissionRepositoryImpl(); 
    }



}