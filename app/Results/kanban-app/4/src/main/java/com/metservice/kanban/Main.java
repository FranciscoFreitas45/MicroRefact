package com.metservice.kanban;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.metservice.kanban.Interface.WorkItemTree;
import com.metservice.kanban.Interface.WorkItemTreeImpl;
import com.metservice.kanban.Interface.WorkItemTypeCollection;
import com.metservice.kanban.Interface.WorkItemTypeCollectionImpl;
import com.metservice.kanban.Interface.WorkItemType;
import com.metservice.kanban.Interface.WorkItemTypeImpl;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItemImpl;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItemImpl;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItemImpl;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItemImpl;
import com.metservice.kanban.Interface.WorkItemType;
import com.metservice.kanban.Interface.WorkItemTypeImpl;
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
public WorkItemTree workitemtree(){

return  new WorkItemTreeImpl(); 
    }



@Bean
public WorkItemTypeCollection workitemtypecollection(){

return  new WorkItemTypeCollectionImpl(); 
    }



@Bean
public WorkItemType workitemtype(){

return  new WorkItemTypeImpl(); 
    }



@Bean
public WorkItem workitem(){

return  new WorkItemImpl(); 
    }



@Bean
public WorkItem workitem(){

return  new WorkItemImpl(); 
    }



@Bean
public WorkItem workitem(){

return  new WorkItemImpl(); 
    }



@Bean
public WorkItem workitem(){

return  new WorkItemImpl(); 
    }



@Bean
public WorkItemType workitemtype(){

return  new WorkItemTypeImpl(); 
    }



}