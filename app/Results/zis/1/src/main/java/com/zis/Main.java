package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.SysService;
import com.zis.Interface.SysServiceImpl;
import com.zis.Interface.OrderService;
import com.zis.Interface.OrderServiceImpl;
import com.zis.Interface.Bookinfo;
import com.zis.Interface.BookinfoImpl;
import com.zis.Interface.Bookinfo;
import com.zis.Interface.BookinfoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.InwarehousePositionDao;
import com.zis.Interface.InwarehousePositionDaoImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.ShopService;
import com.zis.Interface.ShopServiceImpl;
import com.zis.Interface.SimpleMailSender;
import com.zis.Interface.SimpleMailSenderImpl;
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
public SysService sysservice(){

return  new SysServiceImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public Bookinfo bookinfo(){

return  new BookinfoImpl(); 
    }



@Bean
public Bookinfo bookinfo(){

return  new BookinfoImpl(); 
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
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
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
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public InwarehousePositionDao inwarehousepositiondao(){

return  new InwarehousePositionDaoImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public ShopService shopservice(){

return  new ShopServiceImpl(); 
    }



@Bean
public SimpleMailSender simplemailsender(){

return  new SimpleMailSenderImpl(); 
    }



}