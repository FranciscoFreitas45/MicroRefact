package com.cocay.sicecd;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.GrupoRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.InscripcionRep;
import com.cocay.sicecd.Interface.InscripcionRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
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
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public GrupoRep gruporep(){

return  new GrupoRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public InscripcionRep inscripcionrep(){

return  new InscripcionRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



}