package hei2017;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import hei2017.Interface.ProjectService;
import hei2017.Interface.ProjectServiceImpl;
import hei2017.Interface.StoryService;
import hei2017.Interface.StoryServiceImpl;
import hei2017.Interface.TaskService;
import hei2017.Interface.TaskServiceImpl;
import hei2017.Interface.ProjectService;
import hei2017.Interface.ProjectServiceImpl;
import hei2017.Interface.StoryService;
import hei2017.Interface.StoryServiceImpl;
import hei2017.Interface.TaskService;
import hei2017.Interface.TaskServiceImpl;
import hei2017.Interface.UserService;
import hei2017.Interface.UserServiceImpl;
import hei2017.Interface.ProjectDAO;
import hei2017.Interface.ProjectDAOImpl;
import hei2017.Interface.StoryDAO;
import hei2017.Interface.StoryDAOImpl;
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
public ProjectService projectservice(){

return  new ProjectServiceImpl(); 
    }



@Bean
public StoryService storyservice(){

return  new StoryServiceImpl(); 
    }



@Bean
public TaskService taskservice(){

return  new TaskServiceImpl(); 
    }



@Bean
public ProjectService projectservice(){

return  new ProjectServiceImpl(); 
    }



@Bean
public StoryService storyservice(){

return  new StoryServiceImpl(); 
    }



@Bean
public TaskService taskservice(){

return  new TaskServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public ProjectDAO projectdao(){

return  new ProjectDAOImpl(); 
    }



@Bean
public StoryDAO storydao(){

return  new StoryDAOImpl(); 
    }



}