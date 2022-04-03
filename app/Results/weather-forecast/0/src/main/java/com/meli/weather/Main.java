package com.meli.weather;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.meli.weather.Interface.WeatherForecastJobInteractor;
import com.meli.weather.Interface.WeatherForecastJobInteractorImpl;
import com.meli.weather.Interface.ClearWeatherForecastInteractor;
import com.meli.weather.Interface.ClearWeatherForecastInteractorImpl;
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
public WeatherForecastJobInteractor weatherforecastjobinteractor(){

return  new WeatherForecastJobInteractorImpl(); 
    }



@Bean
public ClearWeatherForecastInteractor clearweatherforecastinteractor(){

return  new ClearWeatherForecastInteractorImpl(); 
    }



}