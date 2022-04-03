package com.cocay.sicecd;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cocay.sicecd.Interface.ProcessorGrupo;
import com.cocay.sicecd.Interface.ProcessorGrupoImpl;
import com.cocay.sicecd.Interface.ProcessorCurso;
import com.cocay.sicecd.Interface.ProcessorCursoImpl;
import com.cocay.sicecd.Interface.ProcessorInscripcion;
import com.cocay.sicecd.Interface.ProcessorInscripcionImpl;
import com.cocay.sicecd.Interface.ProcessorProfesor;
import com.cocay.sicecd.Interface.ProcessorProfesorImpl;
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
public ProcessorGrupo processorgrupo(){

return  new ProcessorGrupoImpl(); 
    }



@Bean
public ProcessorCurso processorcurso(){

return  new ProcessorCursoImpl(); 
    }



@Bean
public ProcessorInscripcion processorinscripcion(){

return  new ProcessorInscripcionImpl(); 
    }



@Bean
public ProcessorProfesor processorprofesor(){

return  new ProcessorProfesorImpl(); 
    }



}