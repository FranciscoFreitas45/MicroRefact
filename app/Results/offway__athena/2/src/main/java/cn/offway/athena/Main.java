package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.PhOrderGoodsService;
import cn.offway.athena.Interface.PhOrderGoodsServiceImpl;
import cn.offway.athena.Interface.PhOrderInfoService;
import cn.offway.athena.Interface.PhOrderInfoServiceImpl;
import cn.offway.athena.Interface.QiniuService;
import cn.offway.athena.Interface.QiniuServiceImpl;
import cn.offway.athena.Interface.QiniuProperties;
import cn.offway.athena.Interface.QiniuPropertiesImpl;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.PhWardrobeRepository;
import cn.offway.athena.Interface.PhWardrobeRepositoryImpl;
import cn.offway.athena.Interface.PhOrderGoodsRepository;
import cn.offway.athena.Interface.PhOrderGoodsRepositoryImpl;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.QiniuProperties;
import cn.offway.athena.Interface.QiniuPropertiesImpl;
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
public PhOrderGoodsService phordergoodsservice(){

return  new PhOrderGoodsServiceImpl(); 
    }



@Bean
public PhOrderInfoService phorderinfoservice(){

return  new PhOrderInfoServiceImpl(); 
    }



@Bean
public QiniuService qiniuservice(){

return  new QiniuServiceImpl(); 
    }



@Bean
public QiniuProperties qiniuproperties(){

return  new QiniuPropertiesImpl(); 
    }



@Bean
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public PhWardrobeRepository phwardroberepository(){

return  new PhWardrobeRepositoryImpl(); 
    }



@Bean
public PhOrderGoodsRepository phordergoodsrepository(){

return  new PhOrderGoodsRepositoryImpl(); 
    }



@Bean
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public QiniuProperties qiniuproperties(){

return  new QiniuPropertiesImpl(); 
    }



}