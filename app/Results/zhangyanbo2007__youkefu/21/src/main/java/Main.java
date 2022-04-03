package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.Functor1;
import Interface.Functor1Impl;
import Interface.SqlNullSafeComparator;
import Interface.SqlNullSafeComparatorImpl;
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
public Functor1 functor1(){

return  new Functor1Impl(); 
    }



@Bean
public SqlNullSafeComparator sqlnullsafecomparator(){

return  new SqlNullSafeComparatorImpl(); 
    }



}