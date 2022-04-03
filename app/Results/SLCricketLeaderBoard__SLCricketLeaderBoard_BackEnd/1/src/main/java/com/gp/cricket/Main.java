package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepositoryImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepositoryImpl;
import com.gp.cricket.Interface.BatmanRecordRepository;
import com.gp.cricket.Interface.BatmanRecordRepositoryImpl;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.UserServiceImpl;
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
public TournamentClubPlayerRepository tournamentclubplayerrepository(){

return  new TournamentClubPlayerRepositoryImpl(); 
    }



@Bean
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



@Bean
public TournamentClubPlayerRepository tournamentclubplayerrepository(){

return  new TournamentClubPlayerRepositoryImpl(); 
    }



@Bean
public BatmanRecordRepository batmanrecordrepository(){

return  new BatmanRecordRepositoryImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public BallerRecordRepository ballerrecordrepository(){

return  new BallerRecordRepositoryImpl(); 
    }



}