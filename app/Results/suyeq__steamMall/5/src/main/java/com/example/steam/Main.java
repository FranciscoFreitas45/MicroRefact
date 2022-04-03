package com.example.steam;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.SensitiveWordService;
import com.example.steam.Interface.SensitiveWordServiceImpl;
import com.example.steam.Interface.UserService;
import com.example.steam.Interface.UserServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.SensitiveWordUtil;
import com.example.steam.Interface.SensitiveWordUtilImpl;
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
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public SensitiveWordService sensitivewordservice(){

return  new SensitiveWordServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public ImageService imageservice(){

return  new ImageServiceImpl(); 
    }



@Bean
public UserGameService usergameservice(){

return  new UserGameServiceImpl(); 
    }



@Bean
public UserGameService usergameservice(){

return  new UserGameServiceImpl(); 
    }



@Bean
public SensitiveWordUtil sensitivewordutil(){

return  new SensitiveWordUtilImpl(); 
    }



}