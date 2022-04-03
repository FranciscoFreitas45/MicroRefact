package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.StorageRepoInfo;
import com.zis.Interface.StorageRepoInfoImpl;
import com.zis.Interface.ShopService;
import com.zis.Interface.ShopServiceImpl;
import com.zis.Interface.StorageRepoInfoDao;
import com.zis.Interface.StorageRepoInfoDaoImpl;

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
public ShopService shopservice(){

return  new ShopServiceImpl(); 
    }




@Bean
public StorageRepoInfoDao storagerepoinfodao(){

return  new StorageRepoInfoDaoImpl(); 
    }



}