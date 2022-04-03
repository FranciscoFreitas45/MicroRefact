package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.IMsgService;
import com.Interface.IMsgServiceImpl;
import com.Interface.ICircleService;
import com.Interface.ICircleServiceImpl;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
import com.Interface.IShopCartService;
import com.Interface.IShopCartServiceImpl;
import com.Interface.IOrderService;
import com.Interface.IOrderServiceImpl;
import com.Interface.ICircleService;
import com.Interface.ICircleServiceImpl;
import com.Interface.IMsgService;
import com.Interface.IMsgServiceImpl;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
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
public IMsgService imsgservice(){

return  new IMsgServiceImpl(); 
    }



@Bean
public ICircleService icircleservice(){

return  new ICircleServiceImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public IShopCartService ishopcartservice(){

return  new IShopCartServiceImpl(); 
    }



@Bean
public IOrderService iorderservice(){

return  new IOrderServiceImpl(); 
    }



@Bean
public ICircleService icircleservice(){

return  new ICircleServiceImpl(); 
    }



@Bean
public IMsgService imsgservice(){

return  new IMsgServiceImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



}