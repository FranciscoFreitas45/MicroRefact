package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.CartService;
import com.easyshopping.Interface.CartServiceImpl;
import com.easyshopping.Interface.ConsultationDao;
import com.easyshopping.Interface.ConsultationDaoImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
import com.easyshopping.Interface.DepositDao;
import com.easyshopping.Interface.DepositDaoImpl;
import com.easyshopping.Interface.ReviewService;
import com.easyshopping.Interface.ReviewServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.ReviewService;
import com.easyshopping.Interface.ReviewServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.DepositService;
import com.easyshopping.Interface.DepositServiceImpl;
import com.easyshopping.Interface.PluginService;
import com.easyshopping.Interface.PluginServiceImpl;
import com.easyshopping.Interface.OrderService;
import com.easyshopping.Interface.OrderServiceImpl;
import com.easyshopping.Interface.CouponCodeService;
import com.easyshopping.Interface.CouponCodeServiceImpl;
import com.easyshopping.Interface.MessageService;
import com.easyshopping.Interface.MessageServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.ReviewService;
import com.easyshopping.Interface.ReviewServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.MessageService;
import com.easyshopping.Interface.MessageServiceImpl;
import com.easyshopping.Interface.MemberRankService;
import com.easyshopping.Interface.MemberRankServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.CartService;
import com.easyshopping.Interface.CartServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.ReceiverService;
import com.easyshopping.Interface.ReceiverServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.MemberRankService;
import com.easyshopping.Interface.MemberRankServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.AdminService;
import com.easyshopping.Interface.AdminServiceImpl;
import com.easyshopping.Interface.ProductCategoryService;
import com.easyshopping.Interface.ProductCategoryServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.CacheService;
import com.easyshopping.Interface.CacheServiceImpl;
import com.easyshopping.Interface.TemplateService;
import com.easyshopping.Interface.TemplateServiceImpl;
import com.easyshopping.Interface.FileService;
import com.easyshopping.Interface.FileServiceImpl;
import com.easyshopping.Interface.CacheService;
import com.easyshopping.Interface.CacheServiceImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
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
public CartService cartservice(){

return  new CartServiceImpl(); 
    }



@Bean
public ConsultationDao consultationdao(){

return  new ConsultationDaoImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public DepositDao depositdao(){

return  new DepositDaoImpl(); 
    }



@Bean
public ReviewService reviewservice(){

return  new ReviewServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public ReviewService reviewservice(){

return  new ReviewServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public DepositService depositservice(){

return  new DepositServiceImpl(); 
    }



@Bean
public PluginService pluginservice(){

return  new PluginServiceImpl(); 
    }



@Bean
public OrderService orderservice(){

return  new OrderServiceImpl(); 
    }



@Bean
public CouponCodeService couponcodeservice(){

return  new CouponCodeServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public ReviewService reviewservice(){

return  new ReviewServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public MessageService messageservice(){

return  new MessageServiceImpl(); 
    }



@Bean
public MemberRankService memberrankservice(){

return  new MemberRankServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public CartService cartservice(){

return  new CartServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public ReceiverService receiverservice(){

return  new ReceiverServiceImpl(); 
    }



@Bean
public ProductService productservice(){

return  new ProductServiceImpl(); 
    }



@Bean
public MemberRankService memberrankservice(){

return  new MemberRankServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public AdminService adminservice(){

return  new AdminServiceImpl(); 
    }



@Bean
public ProductCategoryService productcategoryservice(){

return  new ProductCategoryServiceImpl(); 
    }



@Bean
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public CacheService cacheservice(){

return  new CacheServiceImpl(); 
    }



@Bean
public TemplateService templateservice(){

return  new TemplateServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public CacheService cacheservice(){

return  new CacheServiceImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



}