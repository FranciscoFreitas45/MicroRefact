package goorum.goorum;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import goorum.goorum.Interface.MemberLikeBoardService;
import goorum.goorum.Interface.MemberLikeBoardServiceImpl;
import goorum.goorum.Interface.CategoryService;
import goorum.goorum.Interface.CategoryServiceImpl;
import goorum.goorum.Interface.ReplyService;
import goorum.goorum.Interface.ReplyServiceImpl;
import goorum.goorum.Interface.MemberLikeBoardService;
import goorum.goorum.Interface.MemberLikeBoardServiceImpl;
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
public MemberLikeBoardService memberlikeboardservice(){

return  new MemberLikeBoardServiceImpl(); 
    }



@Bean
public CategoryService categoryservice(){

return  new CategoryServiceImpl(); 
    }



@Bean
public ReplyService replyservice(){

return  new ReplyServiceImpl(); 
    }



@Bean
public MemberLikeBoardService memberlikeboardservice(){

return  new MemberLikeBoardServiceImpl(); 
    }



}