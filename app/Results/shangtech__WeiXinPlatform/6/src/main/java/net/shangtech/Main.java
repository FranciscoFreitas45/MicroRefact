package net.shangtech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import net.shangtech.Interface.SiteTemplateDao;
import net.shangtech.Interface.SiteTemplateDaoImpl;
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
public SiteTemplateDao sitetemplatedao(){

return  new SiteTemplateDaoImpl(); 
    }



}