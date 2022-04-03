package br.com.fatecmogidascruzes;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import br.com.fatecmogidascruzes.Interface.File;
import br.com.fatecmogidascruzes.Interface.FileImpl;
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
public File file(){

return  new FileImpl(); 
    }



}