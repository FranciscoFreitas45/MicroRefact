package es.gva.dgti.gvgeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GeoPortal;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GeoPortal;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalImpl;
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
public ServicioWebService serviciowebservice(){

return  new ServicioWebServiceImpl(); 
    }



@Bean
public GeoPortalService geoportalservice(){

return  new GeoPortalServiceImpl(); 
    }



@Bean
public ServicioWebService serviciowebservice(){

return  new ServicioWebServiceImpl(); 
    }



@Bean
public GeoPortalService geoportalservice(){

return  new GeoPortalServiceImpl(); 
    }



@Bean
public GeoPortal geoportal(){

return  new GeoPortalImpl(); 
    }



@Bean
public ServicioWeb servicioweb(){

return  new ServicioWebImpl(); 
    }



@Bean
public ServicioWebService serviciowebservice(){

return  new ServicioWebServiceImpl(); 
    }



@Bean
public GeoPortal geoportal(){

return  new GeoPortalImpl(); 
    }



}