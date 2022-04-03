package edu.xr.campusweibo;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import edu.xr.campusweibo.Interface.WeiboService;
import edu.xr.campusweibo.Interface.WeiboServiceImpl;
import edu.xr.campusweibo.Interface.MyReplyService;
import edu.xr.campusweibo.Interface.MyReplyServiceImpl;
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
public WeiboService weiboservice(){

return  new WeiboServiceImpl(); 
    }



@Bean
public MyReplyService myreplyservice(){

return  new MyReplyServiceImpl(); 
    }



}