package com.jepescados.vendasapi;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.jepescados.vendasapi.Interface.UsuarioDto;
import com.jepescados.vendasapi.Interface.UsuarioDtoImpl;
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
public UsuarioDto usuariodto(){

return  new UsuarioDtoImpl(); 
    }



}