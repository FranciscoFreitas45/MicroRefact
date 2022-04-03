package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.CaptchaService;
import com.easyshopping.Interface.CaptchaServiceImpl;
import com.easyshopping.Interface.OrderService;
import com.easyshopping.Interface.OrderServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.MessageService;
import com.easyshopping.Interface.MessageServiceImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
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
public CaptchaService captchaservice(){

return  new CaptchaServiceImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
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