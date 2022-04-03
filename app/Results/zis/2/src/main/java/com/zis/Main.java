package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.StorageProductDao;
import com.zis.Interface.StorageProductDaoImpl;
import com.zis.Interface.CompanyDao;
import com.zis.Interface.CompanyDaoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.OrderService;
import com.zis.Interface.OrderServiceImpl;
import com.zis.Interface.OrderDao;
import com.zis.Interface.OrderDaoImpl;
import com.zis.Interface.OrderOuterDao;
import com.zis.Interface.OrderOuterDaoImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.StorageProductDao;
import com.zis.Interface.StorageProductDaoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.TaobaoCsvDataGenerateBO;
import com.zis.Interface.TaobaoCsvDataGenerateBOImpl;
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
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public StorageProductDao storageproductdao(){

return  new StorageProductDaoImpl(); 
    }



@Bean
public CompanyDao companydao(){

return  new CompanyDaoImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public OrderDao orderdao(){

return  new OrderDaoImpl(); 
    }



@Bean
public OrderOuterDao orderouterdao(){

return  new OrderOuterDaoImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public StorageProductDao storageproductdao(){

return  new StorageProductDaoImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public TaobaoCsvDataGenerateBO taobaocsvdatageneratebo(){

return  new TaobaoCsvDataGenerateBOImpl(); 
    }



}