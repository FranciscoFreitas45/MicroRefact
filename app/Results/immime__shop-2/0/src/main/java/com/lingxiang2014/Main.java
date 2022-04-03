package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.BankService;
import com.lingxiang2014.Interface.BankServiceImpl;
import com.lingxiang2014.Interface.SnService;
import com.lingxiang2014.Interface.SnServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.PluginService;
import com.lingxiang2014.Interface.PluginServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public BankService bankservice(){

return  new BankServiceImpl(); 
    }



@Bean
public SnService snservice(){

return  new SnServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public PluginService pluginservice(){

return  new PluginServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
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