package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentRepositoryImpl;
import com.gp.cricket.Interface.PlayerRepository;
import com.gp.cricket.Interface.PlayerRepositoryImpl;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentRepositoryImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentRepositoryImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
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
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



@Bean
public TournamentRepository tournamentrepository(){

return  new TournamentRepositoryImpl(); 
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
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



@Bean
public TournamentRepository tournamentrepository(){

return  new TournamentRepositoryImpl(); 
    }



@Bean
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



}