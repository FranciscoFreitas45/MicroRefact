package cn.offway.athena;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.offway.athena.Interface.PhOrderInfoService;
import cn.offway.athena.Interface.PhOrderInfoServiceImpl;
import cn.offway.athena.Interface.PhCodeService;
import cn.offway.athena.Interface.PhCodeServiceImpl;
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
public PhOrderInfoService phorderinfoservice(){

return  new PhOrderInfoServiceImpl(); 
    }



@Bean
public PhCodeService phcodeservice(){

return  new PhCodeServiceImpl(); 
    }



}