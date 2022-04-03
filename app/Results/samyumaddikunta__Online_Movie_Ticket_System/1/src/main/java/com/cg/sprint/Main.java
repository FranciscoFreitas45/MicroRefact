package com.cg.sprint;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cg.sprint.Interface.CityDao;
import com.cg.sprint.Interface.CityDaoImpl;
import com.cg.sprint.Interface.TheatreDao;
import com.cg.sprint.Interface.TheatreDaoImpl;
import com.cg.sprint.Interface.MoviesDao;
import com.cg.sprint.Interface.MoviesDaoImpl;
import com.cg.sprint.Interface.ShowsDao;
import com.cg.sprint.Interface.ShowsDaoImpl;
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
public CityDao citydao(){

return  new CityDaoImpl(); 
    }



@Bean
public TheatreDao theatredao(){

return  new TheatreDaoImpl(); 
    }



@Bean
public MoviesDao moviesdao(){

return  new MoviesDaoImpl(); 
    }



@Bean
public ShowsDao showsdao(){

return  new ShowsDaoImpl(); 
    }



}