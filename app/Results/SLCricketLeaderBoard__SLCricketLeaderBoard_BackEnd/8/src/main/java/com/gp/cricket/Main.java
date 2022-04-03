package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepositoryImpl;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentRepositoryImpl;
import com.gp.cricket.Interface.MatchRepository;
import com.gp.cricket.Interface.MatchRepositoryImpl;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepositoryImpl;
import com.gp.cricket.Interface.ClubRankingService;
import com.gp.cricket.Interface.ClubRankingServiceImpl;
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
public TournamentRepository tournamentrepository(){

return  new TournamentRepositoryImpl(); 
    }



@Bean
public MatchRepository matchrepository(){

return  new MatchRepositoryImpl(); 
    }



@Bean
public PlayerRepository playerrepository(){

return  new PlayerRepositoryImpl(); 
    }



@Bean
public ClubRankingService clubrankingservice(){

return  new ClubRankingServiceImpl(); 
    }



}