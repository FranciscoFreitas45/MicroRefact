package es.gva.dgti.gvgeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GestorCatalogoService;
import es.gva.dgti.gvgeoportal.Interface.GestorCatalogoServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaServicioWebServiceImpl;
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
public GeoPortalService geoportalservice(){

return  new GeoPortalServiceImpl(); 
    }



@Bean
public GestorCatalogoService gestorcatalogoservice(){

return  new GestorCatalogoServiceImpl(); 
    }



@Bean
public AgrupadorCapaServicioWebService agrupadorcapaserviciowebservice(){

return  new AgrupadorCapaServicioWebServiceImpl(); 
    }



}