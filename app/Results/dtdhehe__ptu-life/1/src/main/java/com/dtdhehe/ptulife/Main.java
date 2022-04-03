package com.dtdhehe.ptulife;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.dtdhehe.ptulife.Interface.NewsService;
import com.dtdhehe.ptulife.Interface.NewsServiceImpl;
import com.dtdhehe.ptulife.Interface.AnswerService;
import com.dtdhehe.ptulife.Interface.AnswerServiceImpl;
import com.dtdhehe.ptulife.Interface.OrgCodeService;
import com.dtdhehe.ptulife.Interface.OrgCodeServiceImpl;
import com.dtdhehe.ptulife.Interface.MarketService;
import com.dtdhehe.ptulife.Interface.MarketServiceImpl;
import com.dtdhehe.ptulife.Interface.MailService;
import com.dtdhehe.ptulife.Interface.MailServiceImpl;
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
public NewsService newsservice(){

return  new NewsServiceImpl(); 
    }



@Bean
public AnswerService answerservice(){

return  new AnswerServiceImpl(); 
    }



@Bean
public OrgCodeService orgcodeservice(){

return  new OrgCodeServiceImpl(); 
    }



@Bean
public MarketService marketservice(){

return  new MarketServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



}