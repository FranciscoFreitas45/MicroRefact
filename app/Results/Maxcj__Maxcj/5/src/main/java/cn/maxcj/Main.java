package cn.maxcj;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.maxcj.Interface.IMenuService;
import cn.maxcj.Interface.IMenuServiceImpl;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.Interface.IUserServiceImpl;
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
public IMenuService imenuservice(){

return  new IMenuServiceImpl(); 
    }



@Bean
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



}