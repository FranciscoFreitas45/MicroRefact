package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.FieldingScoreService;
import com.gp.cricket.Interface.FieldingScoreServiceImpl;
import com.gp.cricket.Interface.BatmanScoreRepository;
import com.gp.cricket.Interface.BatmanScoreRepositoryImpl;
import com.gp.cricket.Interface.BallerScoreService;
import com.gp.cricket.Interface.BallerScoreServiceImpl;
import com.gp.cricket.Interface.SelectedPlayerRepository;
import com.gp.cricket.Interface.SelectedPlayerRepositoryImpl;
import com.gp.cricket.Interface.SelectedPlayerService;
import com.gp.cricket.Interface.SelectedPlayerServiceImpl;
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
public FieldingScoreService fieldingscoreservice(){

return  new FieldingScoreServiceImpl(); 
    }



@Bean
public BatmanScoreRepository batmanscorerepository(){

return  new BatmanScoreRepositoryImpl(); 
    }



@Bean
public BallerScoreService ballerscoreservice(){

return  new BallerScoreServiceImpl(); 
    }



@Bean
public SelectedPlayerRepository selectedplayerrepository(){

return  new SelectedPlayerRepositoryImpl(); 
    }



@Bean
public SelectedPlayerService selectedplayerservice(){

return  new SelectedPlayerServiceImpl(); 
    }



}