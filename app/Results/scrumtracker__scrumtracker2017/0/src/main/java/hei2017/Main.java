package hei2017;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import hei2017.Interface.SprintDAO;
import hei2017.Interface.SprintDAOImpl;
import hei2017.Interface.TaskDAO;
import hei2017.Interface.TaskDAOImpl;
import hei2017.Interface.ProjectService;
import hei2017.Interface.ProjectServiceImpl;
import hei2017.Interface.SprintService;
import hei2017.Interface.SprintServiceImpl;
import hei2017.Interface.UserService;
import hei2017.Interface.UserServiceImpl;
import hei2017.Interface.ProjectService;
import hei2017.Interface.ProjectServiceImpl;
import hei2017.Interface.SprintService;
import hei2017.Interface.SprintServiceImpl;
import hei2017.Interface.UserService;
import hei2017.Interface.UserServiceImpl;
import hei2017.Interface.ProjectService;
import hei2017.Interface.ProjectServiceImpl;
import hei2017.Interface.SprintService;
import hei2017.Interface.SprintServiceImpl;
import hei2017.Interface.UserService;
import hei2017.Interface.UserServiceImpl;
import hei2017.Interface.TaskDAO;
import hei2017.Interface.TaskDAOImpl;
import hei2017.Interface.UserDAO;
import hei2017.Interface.UserDAOImpl;
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
public SprintDAO sprintdao(){

return  new SprintDAOImpl(); 
    }



@Bean
public TaskDAO taskdao(){

return  new TaskDAOImpl(); 
    }



@Bean
public ProjectService projectservice(){

return  new ProjectServiceImpl(); 
    }



@Bean
public SprintService sprintservice(){

return  new SprintServiceImpl(); 
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
public SprintService sprintservice(){

return  new SprintServiceImpl(); 
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
public SprintService sprintservice(){

return  new SprintServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public TaskDAO taskdao(){

return  new TaskDAOImpl(); 
    }



@Bean
public UserDAO userdao(){

return  new UserDAOImpl(); 
    }



}