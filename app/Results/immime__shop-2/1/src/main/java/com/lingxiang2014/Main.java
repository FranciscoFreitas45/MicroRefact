package com.lingxiang2014;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.InComeService;
import com.lingxiang2014.Interface.InComeServiceImpl;
import com.lingxiang2014.Interface.BSystemAccountService;
import com.lingxiang2014.Interface.BSystemAccountServiceImpl;
import com.lingxiang2014.Interface.DepositDao;
import com.lingxiang2014.Interface.DepositDaoImpl;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AreaServiceImpl;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.StaticBonudsRankServiceImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AreaServiceImpl;
import com.lingxiang2014.Interface.TransferService;
import com.lingxiang2014.Interface.TransferServiceImpl;
import com.lingxiang2014.Interface.BankService;
import com.lingxiang2014.Interface.BankServiceImpl;
import com.lingxiang2014.Interface.SnService;
import com.lingxiang2014.Interface.SnServiceImpl;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AreaServiceImpl;
import com.lingxiang2014.Interface.AdminService;
import com.lingxiang2014.Interface.AdminServiceImpl;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.StaticBonudsRankServiceImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.DepositServiceImpl;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.WithdrawServiceImpl;
import com.lingxiang2014.Interface.MessageService;
import com.lingxiang2014.Interface.MessageServiceImpl;
import com.lingxiang2014.Interface.BonudsService;
import com.lingxiang2014.Interface.BonudsServiceImpl;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.StaticBonudsRankServiceImpl;
import com.lingxiang2014.Interface.DepositService;
import com.lingxiang2014.Interface.DepositServiceImpl;
import com.lingxiang2014.Interface.WithdrawService;
import com.lingxiang2014.Interface.WithdrawServiceImpl;
import com.lingxiang2014.Interface.AreaService;
import com.lingxiang2014.Interface.AreaServiceImpl;
import com.lingxiang2014.Interface.StaticBonudsRankService;
import com.lingxiang2014.Interface.StaticBonudsRankServiceImpl;
import com.lingxiang2014.Interface.MailService;
import com.lingxiang2014.Interface.MailServiceImpl;
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
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public InComeService incomeservice(){

return  new InComeServiceImpl(); 
    }



@Bean
public BSystemAccountService bsystemaccountservice(){

return  new BSystemAccountServiceImpl(); 
    }



@Bean
public DepositDao depositdao(){

return  new DepositDaoImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public StaticBonudsRankService staticbonudsrankservice(){

return  new StaticBonudsRankServiceImpl(); 
    }



@Bean
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public TransferService transferservice(){

return  new TransferServiceImpl(); 
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
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public StaticBonudsRankService staticbonudsrankservice(){

return  new StaticBonudsRankServiceImpl(); 
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
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public BonudsService bonudsservice(){

return  new BonudsServiceImpl(); 
    }



@Bean
public StaticBonudsRankService staticbonudsrankservice(){

return  new StaticBonudsRankServiceImpl(); 
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
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public StaticBonudsRankService staticbonudsrankservice(){

return  new StaticBonudsRankServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
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