package com.metservice.kanban;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.metservice.kanban.Interface.KanbanProject;
import com.metservice.kanban.Interface.KanbanProjectImpl;
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
public KanbanProject kanbanproject(){

return  new KanbanProjectImpl(); 
    }



}