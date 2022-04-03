package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.AttachService;
import cn.gson.oasys.Interface.AttachServiceImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
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
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
    }



@Bean
public AttachService attachservice(){

return  new AttachServiceImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



}