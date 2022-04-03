package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.LiveRoomRepository;
import org.live.Interface.LiveRoomRepositoryImpl;
import org.live.Interface.UploadFilePathConfig;
import org.live.Interface.UploadFilePathConfigImpl;
import org.live.Interface.UploadFilePathConfig;
import org.live.Interface.UploadFilePathConfigImpl;
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
public LiveRoomRepository liveroomrepository(){

return  new LiveRoomRepositoryImpl(); 
    }



@Bean
public UploadFilePathConfig uploadfilepathconfig(){

return  new UploadFilePathConfigImpl(); 
    }



@Bean
public UploadFilePathConfig uploadfilepathconfig(){

return  new UploadFilePathConfigImpl(); 
    }



}