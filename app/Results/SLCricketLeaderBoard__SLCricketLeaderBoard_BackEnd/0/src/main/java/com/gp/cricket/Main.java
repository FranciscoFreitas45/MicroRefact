package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.PlayerService;
import com.gp.cricket.Interface.PlayerServiceImpl;
import com.gp.cricket.Interface.TournamentService;
import com.gp.cricket.Interface.TournamentServiceImpl;
import com.gp.cricket.Interface.MatchService;
import com.gp.cricket.Interface.MatchServiceImpl;
import com.gp.cricket.Interface.ClubService;
import com.gp.cricket.Interface.ClubServiceImpl;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepositoryImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
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
public PlayerService playerservice(){

return  new PlayerServiceImpl(); 
    }



@Bean
public TournamentService tournamentservice(){

return  new TournamentServiceImpl(); 
    }



@Bean
public MatchService matchservice(){

return  new MatchServiceImpl(); 
    }



@Bean
public ClubService clubservice(){

return  new ClubServiceImpl(); 
    }



@Bean
public PlayerRepository playerrepository(){

return  new PlayerRepositoryImpl(); 
    }



@Bean
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
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