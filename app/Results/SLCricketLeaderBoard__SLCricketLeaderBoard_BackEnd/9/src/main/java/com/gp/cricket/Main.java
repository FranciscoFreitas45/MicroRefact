package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepositoryImpl;
import com.gp.cricket.Interface.BatmanRecordRepository;
import com.gp.cricket.Interface.BatmanRecordRepositoryImpl;
import com.gp.cricket.Interface.BallerRecordRepository;
import com.gp.cricket.Interface.BallerRecordRepositoryImpl;
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
public PlayerRepository playerrepository(){

return  new PlayerRepositoryImpl(); 
    }



@Bean
public BatmanRecordRepository batmanrecordrepository(){

return  new BatmanRecordRepositoryImpl(); 
    }



@Bean
public BallerRecordRepository ballerrecordrepository(){

return  new BallerRecordRepositoryImpl(); 
    }



}