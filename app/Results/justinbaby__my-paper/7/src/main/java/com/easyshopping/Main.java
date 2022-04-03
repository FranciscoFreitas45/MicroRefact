package com.easyshopping;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.easyshopping.Interface.SnDao;
import com.easyshopping.Interface.SnDaoImpl;
import com.easyshopping.Interface.ConsultationService;
import com.easyshopping.Interface.ConsultationServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.ReviewService;
import com.easyshopping.Interface.ReviewServiceImpl;
import com.easyshopping.Interface.MemberService;
import com.easyshopping.Interface.MemberServiceImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.PromotionServiceImpl;
import com.easyshopping.Interface.TagService;
import com.easyshopping.Interface.TagServiceImpl;
import com.easyshopping.Interface.MemberRankService;
import com.easyshopping.Interface.MemberRankServiceImpl;
import com.easyshopping.Interface.ProductImageService;
import com.easyshopping.Interface.ProductImageServiceImpl;
import com.easyshopping.Interface.SpecificationService;
import com.easyshopping.Interface.SpecificationServiceImpl;
import com.easyshopping.Interface.SpecificationValueService;
import com.easyshopping.Interface.SpecificationValueServiceImpl;
import com.easyshopping.Interface.FileService;
import com.easyshopping.Interface.FileServiceImpl;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.PromotionServiceImpl;
import com.easyshopping.Interface.TagService;
import com.easyshopping.Interface.TagServiceImpl;
import com.easyshopping.Interface.SearchService;
import com.easyshopping.Interface.SearchServiceImpl;
import com.easyshopping.Interface.StaticService;
import com.easyshopping.Interface.StaticServiceImpl;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.PromotionServiceImpl;
import com.easyshopping.Interface.TagService;
import com.easyshopping.Interface.TagServiceImpl;
import com.easyshopping.Interface.SnDao;
import com.easyshopping.Interface.SnDaoImpl;
import com.easyshopping.Interface.BrandDao;
import com.easyshopping.Interface.BrandDaoImpl;
import com.easyshopping.Interface.SeoService;
import com.easyshopping.Interface.SeoServiceImpl;
import com.easyshopping.Interface.PromotionService;
import com.easyshopping.Interface.PromotionServiceImpl;
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
public SnDao sndao(){

return  new SnDaoImpl(); 
    }



@Bean
public ConsultationService consultationservice(){

return  new ConsultationServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public ReviewService reviewservice(){

return  new ReviewServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public PromotionService promotionservice(){

return  new PromotionServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public MemberRankService memberrankservice(){

return  new MemberRankServiceImpl(); 
    }



@Bean
public ProductImageService productimageservice(){

return  new ProductImageServiceImpl(); 
    }



@Bean
public SpecificationService specificationservice(){

return  new SpecificationServiceImpl(); 
    }



@Bean
public SpecificationValueService specificationvalueservice(){

return  new SpecificationValueServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public PromotionService promotionservice(){

return  new PromotionServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public SearchService searchservice(){

return  new SearchServiceImpl(); 
    }



@Bean
public StaticService staticservice(){

return  new StaticServiceImpl(); 
    }



@Bean
public PromotionService promotionservice(){

return  new PromotionServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public SnDao sndao(){

return  new SnDaoImpl(); 
    }



@Bean
public BrandDao branddao(){

return  new BrandDaoImpl(); 
    }



@Bean
public SeoService seoservice(){

return  new SeoServiceImpl(); 
    }



@Bean
public PromotionService promotionservice(){

return  new PromotionServiceImpl(); 
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