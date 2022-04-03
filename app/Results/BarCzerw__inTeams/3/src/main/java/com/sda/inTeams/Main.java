package com.sda.inTeams;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.sda.inTeams.Interface.TeamRepository;
import com.sda.inTeams.Interface.TeamRepositoryImpl;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.UserRepositoryImpl;
import com.sda.inTeams.Interface.ProjectRepository;
import com.sda.inTeams.Interface.ProjectRepositoryImpl;
import com.sda.inTeams.Interface.TaskRepository;
import com.sda.inTeams.Interface.TaskRepositoryImpl;
import com.sda.inTeams.Interface.CommentRepository;
import com.sda.inTeams.Interface.CommentRepositoryImpl;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.UserRepositoryImpl;
import com.sda.inTeams.Interface.AccountRoleRepository;
import com.sda.inTeams.Interface.AccountRoleRepositoryImpl;
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
public TeamRepository teamrepository(){

return  new TeamRepositoryImpl(); 
    }



@Bean
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
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
public UserRepository userrepository(){

return  new UserRepositoryImpl(); 
    }



@Bean
public AccountRoleRepository accountrolerepository(){

return  new AccountRoleRepositoryImpl(); 
    }



}