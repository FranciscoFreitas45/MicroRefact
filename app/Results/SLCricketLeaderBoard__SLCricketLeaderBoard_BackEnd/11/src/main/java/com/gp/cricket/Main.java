package com.gp.cricket;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gp.cricket.Interface.UserRepository;
import com.gp.cricket.Interface.UserRepositoryImpl;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.UserServiceImpl;
import com.gp.cricket.Interface.JwtInMemoryUserDetailsService;
import com.gp.cricket.Interface.JwtInMemoryUserDetailsServiceImpl;
import com.gp.cricket.Interface.ClubRepository;
import com.gp.cricket.Interface.ClubRepositoryImpl;
import com.gp.cricket.Interface.UserService;
import com.gp.cricket.Interface.UserServiceImpl;
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public JwtInMemoryUserDetailsService jwtinmemoryuserdetailsservice(){

return  new JwtInMemoryUserDetailsServiceImpl(); 
    }



@Bean
public ClubRepository clubrepository(){

return  new ClubRepositoryImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}