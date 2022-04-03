package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
import com.Interface.ICommonDao;
import com.Interface.ICommonDaoImpl;
import com.Interface.ICircleService;
import com.Interface.ICircleServiceImpl;
import com.Interface.IBatchSellService;
import com.Interface.IBatchSellServiceImpl;
import com.Interface.IGoodDao;
import com.Interface.IGoodDaoImpl;
import com.Interface.IMsgDao;
import com.Interface.IMsgDaoImpl;
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
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public ICircleService icircleservice(){

return  new ICircleServiceImpl(); 
    }



@Bean
public IBatchSellService ibatchsellservice(){

return  new IBatchSellServiceImpl(); 
    }



@Bean
public IGoodDao igooddao(){

return  new IGoodDaoImpl(); 
    }



@Bean
public IMsgDao imsgdao(){

return  new IMsgDaoImpl(); 
    }



}