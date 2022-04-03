package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AreaServiceImpl;
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



}