package edu.xr.campusweibo;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import edu.xr.campusweibo.Interface.MyUserService;
import edu.xr.campusweibo.Interface.MyUserServiceImpl;
import edu.xr.campusweibo.Interface.FriendService;
import edu.xr.campusweibo.Interface.FriendServiceImpl;
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
public MyUserService myuserservice(){

return  new MyUserServiceImpl(); 
    }



@Bean
public FriendService friendservice(){

return  new FriendServiceImpl(); 
    }



}