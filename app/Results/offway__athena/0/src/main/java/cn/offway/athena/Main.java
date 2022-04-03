package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.PhAddressService;
import cn.offway.athena.Interface.PhAddressServiceImpl;
import cn.offway.athena.Interface.PhAddressBrandService;
import cn.offway.athena.Interface.PhAddressBrandServiceImpl;
import cn.offway.athena.Interface.PhGoodsStockService;
import cn.offway.athena.Interface.PhGoodsStockServiceImpl;
import cn.offway.athena.Interface.PhBrandService;
import cn.offway.athena.Interface.PhBrandServiceImpl;
import cn.offway.athena.Interface.PhOrderRemarkService;
import cn.offway.athena.Interface.PhOrderRemarkServiceImpl;
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
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public PhAddressService phaddressservice(){

return  new PhAddressServiceImpl(); 
    }



@Bean
public PhAddressBrandService phaddressbrandservice(){

return  new PhAddressBrandServiceImpl(); 
    }



@Bean
public PhGoodsStockService phgoodsstockservice(){

return  new PhGoodsStockServiceImpl(); 
    }



@Bean
public PhBrandService phbrandservice(){

return  new PhBrandServiceImpl(); 
    }



@Bean
public PhOrderRemarkService phorderremarkservice(){

return  new PhOrderRemarkServiceImpl(); 
    }



}