package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.BookInfoDao;
import com.zis.Interface.BookInfoDaoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
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
public BookInfoDao bookinfodao(){

return  new BookInfoDaoImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
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
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
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
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



}