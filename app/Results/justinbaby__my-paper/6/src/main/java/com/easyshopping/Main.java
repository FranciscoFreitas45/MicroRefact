package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.MemberDao;
import com.easyshopping.Interface.MemberDaoImpl;
import com.easyshopping.Interface.MemberRankService;
import com.easyshopping.Interface.MemberRankServiceImpl;
import com.easyshopping.Interface.ProductCategoryService;
import com.easyshopping.Interface.ProductCategoryServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.BrandService;
import com.easyshopping.Interface.BrandServiceImpl;
import com.easyshopping.Interface.CouponService;
import com.easyshopping.Interface.CouponServiceImpl;
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
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public MemberDao memberdao(){

return  new MemberDaoImpl(); 
    }



@Bean
public MemberRankService memberrankservice(){

return  new MemberRankServiceImpl(); 
    }



@Bean
public ProductCategoryService productcategoryservice(){

return  new ProductCategoryServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public BrandService brandservice(){

return  new BrandServiceImpl(); 
    }



@Bean
public CouponService couponservice(){

return  new CouponServiceImpl(); 
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