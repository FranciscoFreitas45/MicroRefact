package kr.ac.sejong.api;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import kr.ac.sejong.api.Interface.UploadVidRepository;
import kr.ac.sejong.api.Interface.UploadVidRepositoryImpl;
import kr.ac.sejong.api.Interface.UploadRepository;
import kr.ac.sejong.api.Interface.UploadRepositoryImpl;
import kr.ac.sejong.api.Interface.UploadVid;
import kr.ac.sejong.api.Interface.UploadVidImpl;
import kr.ac.sejong.api.Interface.UploadVidRepository;
import kr.ac.sejong.api.Interface.UploadVidRepositoryImpl;
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
public UploadVidRepository uploadvidrepository(){

return  new UploadVidRepositoryImpl(); 
    }



@Bean
public UploadRepository uploadrepository(){

return  new UploadRepositoryImpl(); 
    }



@Bean
public UploadVid uploadvid(){

return  new UploadVidImpl(); 
    }



@Bean
public UploadVidRepository uploadvidrepository(){

return  new UploadVidRepositoryImpl(); 
    }



}