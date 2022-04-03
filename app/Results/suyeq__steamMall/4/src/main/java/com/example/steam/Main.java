package com.example.steam;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.LabelService;
import com.example.steam.Interface.LabelServiceImpl;
import com.example.steam.Interface.LocalStoreService;
import com.example.steam.Interface.LocalStoreServiceImpl;
import com.example.steam.Interface.CommentService;
import com.example.steam.Interface.CommentServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.UserService;
import com.example.steam.Interface.UserServiceImpl;
import com.example.steam.Interface.SystemNeedService;
import com.example.steam.Interface.SystemNeedServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.RecentGameService;
import com.example.steam.Interface.RecentGameServiceImpl;
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
public ImageService imageservice(){

return  new ImageServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public LabelService labelservice(){

return  new LabelServiceImpl(); 
    }



@Bean
public LocalStoreService localstoreservice(){

return  new LocalStoreServiceImpl(); 
    }



@Bean
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



@Bean
public UserGameService usergameservice(){

return  new UserGameServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public SystemNeedService systemneedservice(){

return  new SystemNeedServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
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
public RecentGameService recentgameservice(){

return  new RecentGameServiceImpl(); 
    }



}