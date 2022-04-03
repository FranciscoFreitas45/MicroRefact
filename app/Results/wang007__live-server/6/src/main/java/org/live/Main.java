package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.AttentionRepository;
import org.live.Interface.AttentionRepositoryImpl;
import org.live.Interface.LiveRoomRepository;
import org.live.Interface.LiveRoomRepositoryImpl;
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
public AttentionRepository attentionrepository(){

return  new AttentionRepositoryImpl(); 
    }



@Bean
public LiveRoomRepository liveroomrepository(){

return  new LiveRoomRepositoryImpl(); 
    }



}