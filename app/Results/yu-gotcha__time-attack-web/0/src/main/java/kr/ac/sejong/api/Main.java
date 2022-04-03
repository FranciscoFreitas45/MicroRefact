package kr.ac.sejong.api;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import kr.ac.sejong.api.Interface.UploadRepository;
import kr.ac.sejong.api.Interface.UploadRepositoryImpl;
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
public UploadRepository uploadrepository(){

return  new UploadRepositoryImpl(); 
    }



}