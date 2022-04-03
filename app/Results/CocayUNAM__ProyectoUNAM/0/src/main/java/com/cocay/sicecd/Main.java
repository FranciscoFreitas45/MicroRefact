package com.cocay.sicecd;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.GrupoRepImpl;
import com.cocay.sicecd.Interface.CertificadoRep;
import com.cocay.sicecd.Interface.CertificadoRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
import com.cocay.sicecd.Interface.InscripcionRep;
import com.cocay.sicecd.Interface.InscripcionRepImpl;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.GrupoRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.Tipo_cursoRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
import com.cocay.sicecd.Interface.InscripcionRep;
import com.cocay.sicecd.Interface.InscripcionRepImpl;
import com.cocay.sicecd.Interface.GrupoRep;
import com.cocay.sicecd.Interface.GrupoRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.Tipo_cursoRepImpl;
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
public GrupoRep gruporep(){

return  new GrupoRepImpl(); 
    }



@Bean
public CertificadoRep certificadorep(){

return  new CertificadoRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



@Bean
public InscripcionRep inscripcionrep(){

return  new InscripcionRepImpl(); 
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
public Tipo_cursoRep tipo_cursorep(){

return  new Tipo_cursoRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



@Bean
public InscripcionRep inscripcionrep(){

return  new InscripcionRepImpl(); 
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
public Tipo_cursoRep tipo_cursorep(){

return  new Tipo_cursoRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



}