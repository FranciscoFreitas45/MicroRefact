package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.TaskDao;
import cn.gson.oasys.Interface.TaskDaoImpl;
import cn.gson.oasys.Interface.ScheduleDao;
import cn.gson.oasys.Interface.ScheduleDaoImpl;
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
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public TaskDao taskdao(){

return  new TaskDaoImpl(); 
    }



@Bean
public ScheduleDao scheduledao(){

return  new ScheduleDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



}