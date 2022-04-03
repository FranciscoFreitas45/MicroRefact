package net.shangtech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import net.shangtech.Interface.NewsDao;
import net.shangtech.Interface.NewsDaoImpl;
import net.shangtech.Interface.NewsTypeDao;
import net.shangtech.Interface.NewsTypeDaoImpl;
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
public NewsDao newsdao(){

return  new NewsDaoImpl(); 
    }



@Bean
public NewsTypeDao newstypedao(){

return  new NewsTypeDaoImpl(); 
    }



}