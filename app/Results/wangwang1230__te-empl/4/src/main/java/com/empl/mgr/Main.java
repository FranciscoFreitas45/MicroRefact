package com.empl.mgr;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.empl.mgr.Interface.RoleDao;
import com.empl.mgr.Interface.RoleDaoImpl;
import com.empl.mgr.Interface.AccountRoleDao;
import com.empl.mgr.Interface.AccountRoleDaoImpl;
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
public RoleDao roledao(){

return  new RoleDaoImpl(); 
    }



@Bean
public AccountRoleDao accountroledao(){

return  new AccountRoleDaoImpl(); 
    }



}