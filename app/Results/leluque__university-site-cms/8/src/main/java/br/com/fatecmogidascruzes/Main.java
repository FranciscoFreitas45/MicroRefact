package br.com.fatecmogidascruzes;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import br.com.fatecmogidascruzes.Interface.EventService;
import br.com.fatecmogidascruzes.Interface.EventServiceImpl;
import br.com.fatecmogidascruzes.Interface.EmployeeService;
import br.com.fatecmogidascruzes.Interface.EmployeeServiceImpl;
import br.com.fatecmogidascruzes.Interface.NewsService;
import br.com.fatecmogidascruzes.Interface.NewsServiceImpl;
import br.com.fatecmogidascruzes.Interface.UserService;
import br.com.fatecmogidascruzes.Interface.UserServiceImpl;
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
public EventService eventservice(){

return  new EventServiceImpl(); 
    }



@Bean
public EmployeeService employeeservice(){

return  new EmployeeServiceImpl(); 
    }



@Bean
public NewsService newsservice(){

return  new NewsServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}