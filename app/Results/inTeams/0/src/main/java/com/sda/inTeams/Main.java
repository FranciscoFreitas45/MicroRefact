package com.sda.inTeams;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.TeamRepositoryImpl;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.UserRepositoryImpl;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.UserRepositoryImpl;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.UserServiceImpl;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.Interface.AuthorizationServiceImpl;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.TeamRepositoryImpl;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.UserServiceImpl;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.Interface.AuthorizationServiceImpl;
import com.sda.inTeams.Interface.TeamService;
import com.sda.inTeams.Interface.TeamServiceImpl;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.Interface.AuthorizationServiceImpl;
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
public TeamRepository teamrepository(){

return  new TeamRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }





@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public AuthorizationService authorizationservice(){

return  new AuthorizationServiceImpl(); 
    }

@Bean
public TeamService teamservice(){

return  new TeamServiceImpl(); 
    }



}