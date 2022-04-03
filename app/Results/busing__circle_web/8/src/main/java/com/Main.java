package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.IGoodDao;
import com.Interface.IGoodDaoImpl;
import com.Interface.IShopCartDAO;
import com.Interface.IShopCartDAOImpl;
import com.Interface.ICommissionService;
import com.Interface.ICommissionServiceImpl;
import com.Interface.ICircleService;
import com.Interface.ICircleServiceImpl;
import com.Interface.IShopCartService;
import com.Interface.IShopCartServiceImpl;
import com.Interface.ICircleService;
import com.Interface.ICircleServiceImpl;
import com.Interface.IGoodService;
import com.Interface.IGoodServiceImpl;
import com.Interface.IReceiveInfoService;
import com.Interface.IReceiveInfoServiceImpl;
import com.Interface.IBatchSellService;
import com.Interface.IBatchSellServiceImpl;
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
public IGoodDao igooddao(){

return  new IGoodDaoImpl(); 
    }



@Bean
public IShopCartDAO ishopcartdao(){

return  new IShopCartDAOImpl(); 
    }



@Bean
public ICommissionService icommissionservice(){

return  new ICommissionServiceImpl(); 
    }



@Bean
public ICircleService icircleservice(){

return  new ICircleServiceImpl(); 
    }



@Bean
public IShopCartService ishopcartservice(){

return  new IShopCartServiceImpl(); 
    }



@Bean
public ICircleService icircleservice(){

return  new ICircleServiceImpl(); 
    }



@Bean
public IGoodService igoodservice(){

return  new IGoodServiceImpl(); 
    }



@Bean
public IReceiveInfoService ireceiveinfoservice(){

return  new IReceiveInfoServiceImpl(); 
    }



@Bean
public IBatchSellService ibatchsellservice(){

return  new IBatchSellServiceImpl(); 
    }



}