package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.CaptchaService;
import com.lingxiang2014.Interface.CaptchaServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.MemberRankService;
import com.lingxiang2014.Interface.MemberRankServiceImpl;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.DepositServiceImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.WithdrawServiceImpl;
import com.lingxiang2014.Interface.MessageService;
import com.lingxiang2014.Interface.MessageServiceImpl;
import com.lingxiang2014.Interface.RSAService;
import com.lingxiang2014.Interface.RSAServiceImpl;
import com.lingxiang2014.Interface.CaptchaService;
import com.lingxiang2014.Interface.CaptchaServiceImpl;
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
public CaptchaService captchaservice(){

return  new CaptchaServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MemberRankService memberrankservice(){

return  new MemberRankServiceImpl(); 
    }



@Bean
public DepositService depositservice(){

return  new DepositServiceImpl(); 
    }



@Bean
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public WithdrawService withdrawservice(){

return  new WithdrawServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public RSAService rsaservice(){

return  new RSAServiceImpl(); 
    }



@Bean
public CaptchaService captchaservice(){

return  new CaptchaServiceImpl(); 
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