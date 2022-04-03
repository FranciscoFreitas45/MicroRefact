package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
import com.Interface.IMsgService;
import com.Interface.IMsgServiceImpl;
import com.Interface.IUserService;
import com.Interface.IUserServiceImpl;
import com.Interface.IGoodService;
import com.Interface.IGoodServiceImpl;
import com.Interface.IBatchSellService;
import com.Interface.IBatchSellServiceImpl;
import com.Interface.User;
import com.Interface.UserImpl;
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
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public IMsgService imsgservice(){

return  new IMsgServiceImpl(); 
    }



@Bean
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



@Bean
public IGoodService igoodservice(){

return  new IGoodServiceImpl(); 
    }



@Bean
public IBatchSellService ibatchsellservice(){

return  new IBatchSellServiceImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}