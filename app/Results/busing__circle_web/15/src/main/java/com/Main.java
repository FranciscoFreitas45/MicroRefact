package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.JavaIdentifierTransformer;
import com.Interface.JavaIdentifierTransformerImpl;
import com.Interface.JavaIdentifierTransformer;
import com.Interface.JavaIdentifierTransformerImpl;
import com.Interface.PropertyFilter;
import com.Interface.PropertyFilterImpl;
import com.Interface.PropertyFilter;
import com.Interface.PropertyFilterImpl;
import com.Interface.WebHijackPreventionStrategy;
import com.Interface.WebHijackPreventionStrategyImpl;
import com.Interface.WebHijackPreventionStrategy;
import com.Interface.WebHijackPreventionStrategyImpl;
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
public JavaIdentifierTransformer javaidentifiertransformer(){

return  new JavaIdentifierTransformerImpl(); 
    }



@Bean
public JavaIdentifierTransformer javaidentifiertransformer(){

return  new JavaIdentifierTransformerImpl(); 
    }



@Bean
public PropertyFilter propertyfilter(){

return  new PropertyFilterImpl(); 
    }



@Bean
public PropertyFilter propertyfilter(){

return  new PropertyFilterImpl(); 
    }



@Bean
public WebHijackPreventionStrategy webhijackpreventionstrategy(){

return  new WebHijackPreventionStrategyImpl(); 
    }



@Bean
public WebHijackPreventionStrategy webhijackpreventionstrategy(){

return  new WebHijackPreventionStrategyImpl(); 
    }



}