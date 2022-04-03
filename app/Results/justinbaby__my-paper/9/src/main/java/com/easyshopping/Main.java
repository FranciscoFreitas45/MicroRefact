package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.ProductDao;
import com.easyshopping.Interface.ProductDaoImpl;
import com.easyshopping.Interface.ProductDao;
import com.easyshopping.Interface.ProductDaoImpl;
import com.easyshopping.Interface.PromotionDao;
import com.easyshopping.Interface.PromotionDaoImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.ProductCategoryService;
import com.easyshopping.Interface.ProductCategoryServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
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
public ProductDao productdao(){

return  new ProductDaoImpl(); 
    }



@Bean
public ProductDao productdao(){

return  new ProductDaoImpl(); 
    }



@Bean
public PromotionDao promotiondao(){

return  new PromotionDaoImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
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