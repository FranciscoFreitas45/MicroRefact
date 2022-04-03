package sn;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import sn.Interface.PersonRepository;
import sn.Interface.PersonRepositoryImpl;
import sn.Interface.PostService;
import sn.Interface.PostServiceImpl;
import sn.Interface.CommentService;
import sn.Interface.CommentServiceImpl;
import sn.Interface.NotificationService;
import sn.Interface.NotificationServiceImpl;
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
public PersonRepository personrepository(){

return  new PersonRepositoryImpl(); 
    }



@Bean
public PostService postservice(){

return  new PostServiceImpl(); 
    }



@Bean
public CommentService commentservice(){

return  new CommentServiceImpl(); 
    }



@Bean
public NotificationService notificationservice(){

return  new NotificationServiceImpl(); 
    }



}