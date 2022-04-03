package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.MobileUserRepository;
import org.live.Interface.MobileUserRepositoryImpl;
import org.live.Interface.LiveRoomRepository;
import org.live.Interface.LiveRoomRepositoryImpl;
import org.live.Interface.AnchorRepository;
import org.live.Interface.AnchorRepositoryImpl;
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
public MobileUserRepository mobileuserrepository(){

return  new MobileUserRepositoryImpl(); 
    }



@Bean
public LiveRoomRepository liveroomrepository(){

return  new LiveRoomRepositoryImpl(); 
    }



@Bean
public AnchorRepository anchorrepository(){

return  new AnchorRepositoryImpl(); 
    }



}