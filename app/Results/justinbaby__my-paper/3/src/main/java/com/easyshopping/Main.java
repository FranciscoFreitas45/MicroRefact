package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.MemberDao;
import com.easyshopping.Interface.MemberDaoImpl;
import com.easyshopping.Interface.ProductDao;
import com.easyshopping.Interface.ProductDaoImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.AdminService;
import com.easyshopping.Interface.AdminServiceImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
import com.easyshopping.Interface.Message;
import com.easyshopping.Interface.MessageImpl;
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
public MemberDao memberdao(){

return  new MemberDaoImpl(); 
    }



@Bean
public ProductDao productdao(){

return  new ProductDaoImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
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
public Message message(){

return  new MessageImpl(); 
    }



@Bean
public Message message(){

return  new MessageImpl(); 
    }



}