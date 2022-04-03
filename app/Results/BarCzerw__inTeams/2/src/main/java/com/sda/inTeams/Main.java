package com.sda.inTeams;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.UserRepositoryImpl;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.TeamRepositoryImpl;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.Interface.ProjectRepositoryImpl;
import com.sda.inTeams.Interface.TaskRepository;
import com.sda.inTeams.Interface.TaskRepositoryImpl;
import com.sda.inTeams.Interface.CommentRepository;
import com.sda.inTeams.Interface.CommentRepositoryImpl;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.Interface.AuthorizationServiceImpl;
import com.sda.inTeams.Interface.TeamService;
import com.sda.inTeams.Interface.TeamServiceImpl;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.UserServiceImpl;
import com.sda.inTeams.Interface.ProjectService;
import com.sda.inTeams.Interface.ProjectServiceImpl;
import com.sda.inTeams.Interface.TaskService;
import com.sda.inTeams.Interface.TaskServiceImpl;
import com.sda.inTeams.Interface.CommentService;
import com.sda.inTeams.Interface.CommentServiceImpl;
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
public TeamRepository teamrepository(){

return  new TeamRepositoryImpl(); 
    }



@Bean
public ProjectRepository projectrepository(){

return  new ProjectRepositoryImpl(); 
    }



@Bean
public TaskRepository taskrepository(){

return  new TaskRepositoryImpl(); 
    }



@Bean
public CommentRepository commentrepository(){

return  new CommentRepositoryImpl(); 
    }



@Bean
public AuthorizationService authorizationservice(){

return  new AuthorizationServiceImpl(); 
    }



@Bean
public TeamService teamservice(){

return  new TeamServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public ProjectService projectservice(){

return  new ProjectServiceImpl(); 
    }



@Bean
public TaskService taskservice(){

return  new TaskServiceImpl(); 
    }



@Bean
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



}