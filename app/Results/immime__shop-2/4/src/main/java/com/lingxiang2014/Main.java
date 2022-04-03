package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.StaticService;
import com.lingxiang2014.Interface.StaticServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.TagService;
import com.lingxiang2014.Interface.TagServiceImpl;
import com.lingxiang2014.Interface.StaticService;
import com.lingxiang2014.Interface.StaticServiceImpl;
import com.lingxiang2014.Interface.TagService;
import com.lingxiang2014.Interface.TagServiceImpl;
import com.lingxiang2014.Interface.SeoService;
import com.lingxiang2014.Interface.SeoServiceImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
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
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public SeoService seoservice(){

return  new SeoServiceImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



}