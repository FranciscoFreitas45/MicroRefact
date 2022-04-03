package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.OrderLogDao;
import com.easyshopping.Interface.OrderLogDaoImpl;
import com.easyshopping.Interface.CartDao;
import com.easyshopping.Interface.CartDaoImpl;
import com.easyshopping.Interface.CouponCodeDao;
import com.easyshopping.Interface.CouponCodeDaoImpl;
import com.easyshopping.Interface.MemberDao;
import com.easyshopping.Interface.MemberDaoImpl;
import com.easyshopping.Interface.MemberRankDao;
import com.easyshopping.Interface.MemberRankDaoImpl;
import com.easyshopping.Interface.ProductDao;
import com.easyshopping.Interface.ProductDaoImpl;
import com.easyshopping.Interface.DepositDao;
import com.easyshopping.Interface.DepositDaoImpl;
import com.easyshopping.Interface.RefundsDao;
import com.easyshopping.Interface.RefundsDaoImpl;
import com.easyshopping.Interface.ReturnsDao;
import com.easyshopping.Interface.ReturnsDaoImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.ReceiverService;
import com.easyshopping.Interface.ReceiverServiceImpl;
import com.easyshopping.Interface.CartService;
import com.easyshopping.Interface.CartServiceImpl;
import com.easyshopping.Interface.ShippingMethodService;
import com.easyshopping.Interface.ShippingMethodServiceImpl;
import com.easyshopping.Interface.CouponCodeService;
import com.easyshopping.Interface.CouponCodeServiceImpl;
import com.easyshopping.Interface.PluginService;
import com.easyshopping.Interface.PluginServiceImpl;
import com.easyshopping.Interface.AreaService;
import com.easyshopping.Interface.AreaServiceImpl;
import com.easyshopping.Interface.ProductService;
import com.easyshopping.Interface.ProductServiceImpl;
import com.easyshopping.Interface.ShippingMethodService;
import com.easyshopping.Interface.ShippingMethodServiceImpl;
import com.easyshopping.Interface.DeliveryCorpService;
import com.easyshopping.Interface.DeliveryCorpServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.PluginService;
import com.easyshopping.Interface.PluginServiceImpl;
import com.easyshopping.Interface.CaptchaService;
import com.easyshopping.Interface.CaptchaServiceImpl;
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
public OrderLogDao orderlogdao(){

return  new OrderLogDaoImpl(); 
    }



@Bean
public CartDao cartdao(){

return  new CartDaoImpl(); 
    }



@Bean
public CouponCodeDao couponcodedao(){

return  new CouponCodeDaoImpl(); 
    }



@Bean
public MemberDao memberdao(){

return  new MemberDaoImpl(); 
    }



@Bean
public MemberRankDao memberrankdao(){

return  new MemberRankDaoImpl(); 
    }



@Bean
public ProductDao productdao(){

return  new ProductDaoImpl(); 
    }



@Bean
public DepositDao depositdao(){

return  new DepositDaoImpl(); 
    }



@Bean
public RefundsDao refundsdao(){

return  new RefundsDaoImpl(); 
    }



@Bean
public ReturnsDao returnsdao(){

return  new ReturnsDaoImpl(); 
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
public AreaService areaservice(){

return  new AreaServiceImpl(); 
    }



@Bean
public ReceiverService receiverservice(){

return  new ReceiverServiceImpl(); 
    }



@Bean
public CartService cartservice(){

return  new CartServiceImpl(); 
    }



@Bean
public ShippingMethodService shippingmethodservice(){

return  new ShippingMethodServiceImpl(); 
    }



@Bean
public CouponCodeService couponcodeservice(){

return  new CouponCodeServiceImpl(); 
    }



@Bean
public PluginService pluginservice(){

return  new PluginServiceImpl(); 
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
public ShippingMethodService shippingmethodservice(){

return  new ShippingMethodServiceImpl(); 
    }



@Bean
public DeliveryCorpService deliverycorpservice(){

return  new DeliveryCorpServiceImpl(); 
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
public PluginService pluginservice(){

return  new PluginServiceImpl(); 
    }



@Bean
public CaptchaService captchaservice(){

return  new CaptchaServiceImpl(); 
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