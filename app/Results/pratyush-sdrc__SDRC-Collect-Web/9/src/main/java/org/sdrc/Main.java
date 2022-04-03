package org.sdrc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.sdrc.Interface.DashboardController;
import org.sdrc.Interface.DashboardControllerImpl;
import org.sdrc.Interface.DashboardService;
import org.sdrc.Interface.DashboardServiceImpl;
import org.sdrc.Interface.DashboardService;
import org.sdrc.Interface.DashboardServiceImpl;
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
public DashboardController dashboardcontroller(){

return  new DashboardControllerImpl(); 
    }



@Bean
public DashboardService dashboardservice(){

return  new DashboardServiceImpl(); 
    }



@Bean
public DashboardService dashboardservice(){

return  new DashboardServiceImpl(); 
    }



}