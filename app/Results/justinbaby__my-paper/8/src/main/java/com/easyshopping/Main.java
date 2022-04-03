package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.ArticleCategoryService;
import com.easyshopping.Interface.ArticleCategoryServiceImpl;
import com.easyshopping.Interface.ProductCategoryService;
import com.easyshopping.Interface.ProductCategoryServiceImpl;
import com.easyshopping.Interface.ProductCategoryService;
import com.easyshopping.Interface.ProductCategoryServiceImpl;
import com.easyshopping.Interface.PaymentMethodService;
import com.easyshopping.Interface.PaymentMethodServiceImpl;
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
public ArticleCategoryService articlecategoryservice(){

return  new ArticleCategoryServiceImpl(); 
    }



@Bean
public ProductCategoryService productcategoryservice(){

return  new ProductCategoryServiceImpl(); 
    }



@Bean
public ProductCategoryService productcategoryservice(){

return  new ProductCategoryServiceImpl(); 
    }



@Bean
public PaymentMethodService paymentmethodservice(){

return  new PaymentMethodServiceImpl(); 
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