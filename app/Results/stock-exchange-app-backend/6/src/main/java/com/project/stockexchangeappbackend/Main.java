package com.project.stockexchangeappbackend;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.project.stockexchangeappbackend.Interface.StockIndexTimeProperties;
import com.project.stockexchangeappbackend.Interface.StockIndexTimePropertiesImpl;
import  oshi.hardware.platform.linux.LinuxGlobalMemory;
import oshi.hardware.GlobalMemory;
import org.modelmapper.ModelMapper;
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
public StockIndexTimeProperties stockindextimeproperties(){

return  new StockIndexTimePropertiesImpl(); 
    }

@Bean 
public GlobalMemory globalMemory(){
  return new  LinuxGlobalMemory();
}

@Bean 
public ModelMapper modelMapper(){
  return new ModelMapper();
}

}