package com.metservice.kanban;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.metservice.kanban.Interface.EstimatesDao;
import com.metservice.kanban.Interface.EstimatesDaoImpl;
import com.metservice.kanban.Interface.KanbanCommentsFile;
import com.metservice.kanban.Interface.KanbanCommentsFileImpl;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItemImpl;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFileImpl;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFileImpl;
import com.metservice.kanban.Interface.KanbanPropertiesFile;
import com.metservice.kanban.Interface.KanbanPropertiesFileImpl;
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
public EstimatesDao estimatesdao(){

return  new EstimatesDaoImpl(); 
    }



@Bean
public KanbanCommentsFile kanbancommentsfile(){

return  new KanbanCommentsFileImpl(); 
    }



@Bean
public WorkItem workitem(){

return  new WorkItemImpl(); 
    }



@Bean
public KanbanPropertiesFile kanbanpropertiesfile(){

return  new KanbanPropertiesFileImpl(); 
    }



@Bean
public KanbanPropertiesFile kanbanpropertiesfile(){

return  new KanbanPropertiesFileImpl(); 
    }



@Bean
public KanbanPropertiesFile kanbanpropertiesfile(){

return  new KanbanPropertiesFileImpl(); 
    }



}