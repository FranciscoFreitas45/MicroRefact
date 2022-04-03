package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.TransferService;
import com.lingxiang2014.Interface.TransferServiceImpl;
import com.lingxiang2014.Interface.InComeService;
import com.lingxiang2014.Interface.InComeServiceImpl;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.DepositServiceImpl;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.WithdrawServiceImpl;
import com.lingxiang2014.Interface.LogService;
import com.lingxiang2014.Interface.LogServiceImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.Message;
import com.lingxiang2014.Interface.MessageImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.DepositServiceImpl;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.WithdrawServiceImpl;
import com.lingxiang2014.Interface.PluginService;
import com.lingxiang2014.Interface.PluginServiceImpl;
import com.lingxiang2014.Interface.FileService;
import com.lingxiang2014.Interface.FileServiceImpl;
import com.lingxiang2014.Interface.ArticleDao;
import com.lingxiang2014.Interface.ArticleDaoImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
import com.lingxiang2014.Interface.MemberRankService;
import com.lingxiang2014.Interface.MemberRankServiceImpl;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.StaticBonudsRankServiceImpl;
import com.lingxiang2014.Interface.MemberService;
import com.lingxiang2014.Interface.MemberServiceImpl;
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
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public TransferService transferservice(){

return  new TransferServiceImpl(); 
    }



@Bean
public InComeService incomeservice(){

return  new InComeServiceImpl(); 
    }



@Bean
public DepositService depositservice(){

return  new DepositServiceImpl(); 
    }



@Bean
public WithdrawService withdrawservice(){

return  new WithdrawServiceImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
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
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public DepositService depositservice(){

return  new DepositServiceImpl(); 
    }



@Bean
public WithdrawService withdrawservice(){

return  new WithdrawServiceImpl(); 
    }



@Bean
public PluginService pluginservice(){

return  new PluginServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public ArticleDao articledao(){

return  new ArticleDaoImpl(); 
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
public StaticBonudsRankService staticbonudsrankservice(){

return  new StaticBonudsRankServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



}