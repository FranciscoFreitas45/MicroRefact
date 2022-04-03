package goorum.goorum;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import goorum.goorum.Interface.BoardService;
import goorum.goorum.Interface.BoardServiceImpl;
import goorum.goorum.Interface.MemberService;
import goorum.goorum.Interface.MemberServiceImpl;
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
public BoardService boardservice(){

return  new BoardServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
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