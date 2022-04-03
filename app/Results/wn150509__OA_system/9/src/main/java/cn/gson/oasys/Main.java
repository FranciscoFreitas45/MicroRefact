package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.UserService;
import cn.gson.oasys.Interface.UserServiceImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
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
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public StatusDao statusdao(){

return  new StatusDaoImpl(); 
    }



}