package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.QiniuProperties;
import cn.offway.athena.Interface.QiniuPropertiesImpl;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.PhGoodsService;
import cn.offway.athena.Interface.PhGoodsServiceImpl;
import cn.offway.athena.Interface.PhRoleadminService;
import cn.offway.athena.Interface.PhRoleadminServiceImpl;
import cn.offway.athena.Interface.PhBrandadminService;
import cn.offway.athena.Interface.PhBrandadminServiceImpl;
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
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public PhGoodsService phgoodsservice(){

return  new PhGoodsServiceImpl(); 
    }



@Bean
public PhRoleadminService phroleadminservice(){

return  new PhRoleadminServiceImpl(); 
    }



@Bean
public PhBrandadminService phbrandadminservice(){

return  new PhBrandadminServiceImpl(); 
    }



}