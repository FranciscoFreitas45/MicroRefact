package com.meli.weather;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.meli.weather.Interface.WeatherForecastRepository;
import com.meli.weather.Interface.WeatherForecastRepositoryImpl;
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
public WeatherForecastRepository weatherforecastrepository(){

return  new WeatherForecastRepositoryImpl(); 
    }



}