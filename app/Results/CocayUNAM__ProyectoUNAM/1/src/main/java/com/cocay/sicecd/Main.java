package com.cocay.sicecd;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.SendMailService;
import com.cocay.sicecd.Interface.SendMailServiceImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.CertificadoRep;
import com.cocay.sicecd.Interface.CertificadoRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.GeneroRep;
import com.cocay.sicecd.Interface.GeneroRepImpl;
import com.cocay.sicecd.Interface.EstadoRep;
import com.cocay.sicecd.Interface.EstadoRepImpl;
import com.cocay.sicecd.Interface.Grado_profesorRep;
import com.cocay.sicecd.Interface.Grado_profesorRepImpl;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.Tipo_cursoRepImpl;
import com.cocay.sicecd.Interface.TurnoRep;
import com.cocay.sicecd.Interface.TurnoRepImpl;
import com.cocay.sicecd.Interface.Logging;
import com.cocay.sicecd.Interface.LoggingImpl;
import com.cocay.sicecd.Interface.ProfesorRep;
import com.cocay.sicecd.Interface.ProfesorRepImpl;
import com.cocay.sicecd.Interface.CursoRep;
import com.cocay.sicecd.Interface.CursoRepImpl;
import com.cocay.sicecd.Interface.GeneroRep;
import com.cocay.sicecd.Interface.GeneroRepImpl;
import com.cocay.sicecd.Interface.EstadoRep;
import com.cocay.sicecd.Interface.EstadoRepImpl;
import com.cocay.sicecd.Interface.Grado_profesorRep;
import com.cocay.sicecd.Interface.Grado_profesorRepImpl;
import com.cocay.sicecd.Interface.Tipo_cursoRep;
import com.cocay.sicecd.Interface.Tipo_cursoRepImpl;
import com.cocay.sicecd.Interface.TurnoRep;
import com.cocay.sicecd.Interface.TurnoRepImpl;
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
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public SendMailService sendmailservice(){

return  new SendMailServiceImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public CertificadoRep certificadorep(){

return  new CertificadoRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public GeneroRep generorep(){

return  new GeneroRepImpl(); 
    }



@Bean
public EstadoRep estadorep(){

return  new EstadoRepImpl(); 
    }



@Bean
public Grado_profesorRep grado_profesorrep(){

return  new Grado_profesorRepImpl(); 
    }



@Bean
public Tipo_cursoRep tipo_cursorep(){

return  new Tipo_cursoRepImpl(); 
    }



@Bean
public TurnoRep turnorep(){

return  new TurnoRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



@Bean
public ProfesorRep profesorrep(){

return  new ProfesorRepImpl(); 
    }



@Bean
public CursoRep cursorep(){

return  new CursoRepImpl(); 
    }



@Bean
public GeneroRep generorep(){

return  new GeneroRepImpl(); 
    }



@Bean
public EstadoRep estadorep(){

return  new EstadoRepImpl(); 
    }



@Bean
public Grado_profesorRep grado_profesorrep(){

return  new Grado_profesorRepImpl(); 
    }



@Bean
public Tipo_cursoRep tipo_cursorep(){

return  new Tipo_cursoRepImpl(); 
    }



@Bean
public TurnoRep turnorep(){

return  new TurnoRepImpl(); 
    }



@Bean
public Logging logging(){

return  new LoggingImpl(); 
    }



}