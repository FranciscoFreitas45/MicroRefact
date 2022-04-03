package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.DiscussDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.ReplyDao;
import cn.gson.oasys.Interface.ReplyDaoImpl;
import cn.gson.oasys.Interface.ReplyDao;
import cn.gson.oasys.Interface.ReplyDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.DiscussDaoImpl;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.DiscussDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.ReplyDao;
import cn.gson.oasys.Interface.ReplyDaoImpl;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.DiscussDaoImpl;
import cn.gson.oasys.Interface.ReplyDao;
import cn.gson.oasys.Interface.ReplyDaoImpl;
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
public DiscussDao discussdao(){

return  new DiscussDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public ReplyDao replydao(){

return  new ReplyDaoImpl(); 
    }



@Bean
public ReplyDao replydao(){

return  new ReplyDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DiscussDao discussdao(){

return  new DiscussDaoImpl(); 
    }



@Bean
public DiscussDao discussdao(){

return  new DiscussDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public ReplyDao replydao(){

return  new ReplyDaoImpl(); 
    }



@Bean
public DiscussDao discussdao(){

return  new DiscussDaoImpl(); 
    }



@Bean
public ReplyDao replydao(){

return  new ReplyDaoImpl(); 
    }



}