package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.PaymentService;
import com.easyshopping.Interface.PaymentServiceImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
import com.easyshopping.Interface.OrderService;
import com.easyshopping.Interface.OrderServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public PaymentService paymentservice(){

return  new PaymentServiceImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



}