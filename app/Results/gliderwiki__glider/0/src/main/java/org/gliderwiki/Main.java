package org.gliderwiki;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.gliderwiki.Interface.SpaceService;
import org.gliderwiki.Interface.SpaceServiceImpl;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.CommonServiceImpl;
import org.gliderwiki.Interface.AdminSpaceService;
import org.gliderwiki.Interface.AdminSpaceServiceImpl;
import org.gliderwiki.Interface.AdminWikiService;
import org.gliderwiki.Interface.AdminWikiServiceImpl;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.Interface.CommonServiceImpl;
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
public SpaceService spaceservice(){

return  new SpaceServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



@Bean
public AdminSpaceService adminspaceservice(){

return  new AdminSpaceServiceImpl(); 
    }



@Bean
public AdminWikiService adminwikiservice(){

return  new AdminWikiServiceImpl(); 
    }



@Bean
public CommonService commonservice(){

return  new CommonServiceImpl(); 
    }



}