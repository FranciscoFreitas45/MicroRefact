package com.cg.sprint;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cg.sprint.Interface.AccountDao;
import com.cg.sprint.Interface.AccountDaoImpl;
import com.cg.sprint.Interface.TheatreDao;
import com.cg.sprint.Interface.TheatreDaoImpl;
import com.cg.sprint.Interface.ShowsDao;
import com.cg.sprint.Interface.ShowsDaoImpl;
import com.cg.sprint.Interface.LanguagesDao;
import com.cg.sprint.Interface.LanguagesDaoImpl;
import com.cg.sprint.Interface.SeatsDao;
import com.cg.sprint.Interface.SeatsDaoImpl;
import com.cg.sprint.Interface.CustomerDao;
import com.cg.sprint.Interface.CustomerDaoImpl;
import com.cg.sprint.Interface.PaymentDao;
import com.cg.sprint.Interface.PaymentDaoImpl;
import com.cg.sprint.Interface.RefundDao;
import com.cg.sprint.Interface.RefundDaoImpl;
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
public AccountDao accountdao(){

return  new AccountDaoImpl(); 
    }



@Bean
public TheatreDao theatredao(){

return  new TheatreDaoImpl(); 
    }



@Bean
public ShowsDao showsdao(){

return  new ShowsDaoImpl(); 
    }



@Bean
public LanguagesDao languagesdao(){

return  new LanguagesDaoImpl(); 
    }



@Bean
public SeatsDao seatsdao(){

return  new SeatsDaoImpl(); 
    }



@Bean
public CustomerDao customerdao(){

return  new CustomerDaoImpl(); 
    }



@Bean
public PaymentDao paymentdao(){

return  new PaymentDaoImpl(); 
    }



@Bean
public RefundDao refunddao(){

return  new RefundDaoImpl(); 
    }



}