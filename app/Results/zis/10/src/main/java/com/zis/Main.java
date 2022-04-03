package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.ShopService;
import com.zis.Interface.ShopServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.SysService;
import com.zis.Interface.SysServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.ShopService;
import com.zis.Interface.ShopServiceImpl;
import com.zis.Interface.ShopService;
import com.zis.Interface.ShopServiceImpl;
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
public ShopService shopservice(){

return  new ShopServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public SysService sysservice(){

return  new SysServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public ShopService shopservice(){

return  new ShopServiceImpl(); 
    }



@Bean
public ShopService shopservice(){

return  new ShopServiceImpl(); 
    }



}