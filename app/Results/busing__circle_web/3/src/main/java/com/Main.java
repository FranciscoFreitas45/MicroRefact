package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.ILoginService;
import com.Interface.ILoginServiceImpl;
import com.Interface.IUserService;
import com.Interface.IUserServiceImpl;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
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
public ILoginService iloginservice(){

return  new ILoginServiceImpl(); 
    }



@Bean
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



}