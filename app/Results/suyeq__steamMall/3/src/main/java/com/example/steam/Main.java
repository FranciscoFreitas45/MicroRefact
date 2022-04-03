package com.example.steam;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.GameServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.GameServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
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
public GameService gameservice(){

return  new GameServiceImpl(); 
    }



@Bean
public ImageService imageservice(){

return  new ImageServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public GameService gameservice(){

return  new GameServiceImpl(); 
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
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



}