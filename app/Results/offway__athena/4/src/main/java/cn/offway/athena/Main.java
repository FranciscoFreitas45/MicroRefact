package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.QiniuProperties;
import cn.offway.athena.Interface.QiniuPropertiesImpl;
import cn.offway.athena.Interface.PhOrderInfoService;
import cn.offway.athena.Interface.PhOrderInfoServiceImpl;
import cn.offway.athena.Interface.PhGoodsService;
import cn.offway.athena.Interface.PhGoodsServiceImpl;
import cn.offway.athena.Interface.PhGoodsImageService;
import cn.offway.athena.Interface.PhGoodsImageServiceImpl;
import cn.offway.athena.Interface.PhGoodsStockService;
import cn.offway.athena.Interface.PhGoodsStockServiceImpl;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
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
public QiniuProperties qiniuproperties(){

return  new QiniuPropertiesImpl(); 
    }



@Bean
public PhOrderInfoService phorderinfoservice(){

return  new PhOrderInfoServiceImpl(); 
    }



@Bean
public PhGoodsService phgoodsservice(){

return  new PhGoodsServiceImpl(); 
    }



@Bean
public PhGoodsImageService phgoodsimageservice(){

return  new PhGoodsImageServiceImpl(); 
    }



@Bean
public PhGoodsStockService phgoodsstockservice(){

return  new PhGoodsStockServiceImpl(); 
    }



@Bean
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



}