package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.StadiumRepository;
import com.gp.cricket.Interface.StadiumRepositoryImpl;
import com.gp.cricket.Interface.TournamentRepository;
import com.gp.cricket.Interface.TournamentRepositoryImpl;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.Interface.TournamentClubRepositoryImpl;
import com.gp.cricket.Interface.TournamentClubPlayerRepository;
import com.gp.cricket.Interface.TournamentClubPlayerRepositoryImpl;
import com.gp.cricket.Interface.SelectedPlayerRepository;
import com.gp.cricket.Interface.SelectedPlayerRepositoryImpl;
import com.gp.cricket.Interface.SelectedPlayerService;
import com.gp.cricket.Interface.SelectedPlayerServiceImpl;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.Interface.TournamentClubRepositoryImpl;
import com.gp.cricket.Interface.TournamentClubCaptainRepository;
import com.gp.cricket.Interface.TournamentClubCaptainRepositoryImpl;
import com.gp.cricket.Interface.TournamentClubRepository;
import com.gp.cricket.Interface.TournamentClubRepositoryImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.UserServiceImpl;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.UserServiceImpl;
import com.gp.cricket.Interface.JwtInMemoryUserDetailsService;
import com.gp.cricket.Interface.JwtInMemoryUserDetailsServiceImpl;
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
public StadiumRepository stadiumrepository(){

return  new StadiumRepositoryImpl(); 
    }



@Bean
public TournamentRepository tournamentrepository(){

return  new TournamentRepositoryImpl(); 
    }



@Bean
public TournamentClubRepository tournamentclubrepository(){

return  new TournamentClubRepositoryImpl(); 
    }



@Bean
public TournamentClubPlayerRepository tournamentclubplayerrepository(){

return  new TournamentClubPlayerRepositoryImpl(); 
    }



@Bean
public SelectedPlayerRepository selectedplayerrepository(){

return  new SelectedPlayerRepositoryImpl(); 
    }



@Bean
public SelectedPlayerService selectedplayerservice(){

return  new SelectedPlayerServiceImpl(); 
    }



@Bean
public TournamentClubRepository tournamentclubrepository(){

return  new TournamentClubRepositoryImpl(); 
    }



@Bean
public TournamentClubCaptainRepository tournamentclubcaptainrepository(){

return  new TournamentClubCaptainRepositoryImpl(); 
    }



@Bean
public TournamentClubRepository tournamentclubrepository(){

return  new TournamentClubRepositoryImpl(); 
    }



@Bean
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public JwtInMemoryUserDetailsService jwtinmemoryuserdetailsservice(){

return  new JwtInMemoryUserDetailsServiceImpl(); 
    }



}