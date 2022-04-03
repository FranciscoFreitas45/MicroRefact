package com.example.steam;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.example.steam.Interface.TypeService;
import com.example.steam.Interface.TypeServiceImpl;
import com.example.steam.Interface.GameService;
import com.example.steam.Interface.GameServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.CommentService;
import com.example.steam.Interface.CommentServiceImpl;
import com.example.steam.Interface.ShoppingCartService;
import com.example.steam.Interface.ShoppingCartServiceImpl;
import com.example.steam.Interface.SpikeGameService;
import com.example.steam.Interface.SpikeGameServiceImpl;
import com.example.steam.Interface.LabelService;
import com.example.steam.Interface.LabelServiceImpl;
import com.example.steam.Interface.SensitiveWordService;
import com.example.steam.Interface.SensitiveWordServiceImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.MQProducer;
import com.example.steam.Interface.MQProducerImpl;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.RedisServiceImpl;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.ImageServiceImpl;
import com.example.steam.Interface.MQProducer;
import com.example.steam.Interface.MQProducerImpl;
import com.example.steam.Interface.FileUploadUtil;
import com.example.steam.Interface.FileUploadUtilImpl;
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
public TypeService typeservice(){

return  new TypeServiceImpl(); 
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
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



@Bean
public ShoppingCartService shoppingcartservice(){

return  new ShoppingCartServiceImpl(); 
    }



@Bean
public SpikeGameService spikegameservice(){

return  new SpikeGameServiceImpl(); 
    }



@Bean
public LabelService labelservice(){

return  new LabelServiceImpl(); 
    }



@Bean
public SensitiveWordService sensitivewordservice(){

return  new SensitiveWordServiceImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public ImageService imageservice(){

return  new ImageServiceImpl(); 
    }



@Bean
public MQProducer mqproducer(){

return  new MQProducerImpl(); 
    }



@Bean
public RedisService redisservice(){

return  new RedisServiceImpl(); 
    }



@Bean
public ImageService imageservice(){

return  new ImageServiceImpl(); 
    }



@Bean
public MQProducer mqproducer(){

return  new MQProducerImpl(); 
    }



@Bean
public FileUploadUtil fileuploadutil(){

return  new FileUploadUtilImpl(); 
    }



}